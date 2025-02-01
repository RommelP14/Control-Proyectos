document.addEventListener("DOMContentLoaded", () => {

    const linkRegistroEmpresa = document.getElementById("linkRegistroEmpresa");
    const linkMiProyecto = document.getElementById("linkMiProyecto");

    const inputsMiProyecto = document.getElementById("inputsMiProyecto");
    const inputsRegistroEmpresa = document.getElementById("inputsRegistroEmpresa");
    var tipo = "";

    linkRegistroEmpresa.addEventListener("click", (e) => {
        tipo = "registroEmpresa";
        limpiarInputs(inputsMiProyecto);
        hacerReadonly(inputsMiProyecto); // Hacer `readonly` los inputs de Mi Proyecto
        inputsMiProyecto.classList.add("hidden"); // Oculta la sección de Mi Proyecto
        inputsRegistroEmpresa.classList.remove("hidden"); // Muestra la sección de Registro Empresa
        hacerEditable(inputsRegistroEmpresa); // Hacer editables los inputs de Registro Empresa

        // Cambiar ícono del teléfono al ícono de empresa
        actualizarIcono("#inputsRegistroEmpresa .input-group-addon i", "fa-phone", "fa-building");

        // Cambiar clase activa
        actualizarClaseActiva(linkRegistroEmpresa, linkMiProyecto);
    });

    linkMiProyecto.addEventListener("click", (e) => {
        tipo = "miProyecto";
        limpiarInputs(inputsRegistroEmpresa);
        hacerReadonly(inputsRegistroEmpresa); // Hacer `readonly` los inputs de Registro Empresa
        inputsRegistroEmpresa.classList.add("hidden"); // Oculta la sección de Registro Empresa
        inputsMiProyecto.classList.remove("hidden"); // Muestra la sección de Mi Proyecto
        //hacerEditable(inputsMiProyecto); // Hacer editables los inputs de Mi Proyecto

        // Cambiar ícono de empresa al ícono de persona
        actualizarIcono("#inputsMiProyecto .input-group-addon i", "fa-building", "fa-user");

        // Cambiar clase activa
        actualizarClaseActiva(linkMiProyecto, linkRegistroEmpresa);

        cargarDatosFormulario('../../app/registro/Duenio_SRV.do', 'formulario_duenio');
    });

    document.querySelector("#formulario_duenio").addEventListener('submit', function (event) {
        event.preventDefault(); // Detiene el envío del formulario
        if (!validarFormularioDuenio(tipo)) {
            return;
        }
        const datos = obtenerDatosFormulario();
        console.log(datos);
        mostrarConfirmacion(datos);
    });
});

/**
 * Valida los campos del formulario dependiendo de la sección activa.
 * @param {string} tipo - Puede ser 'miProyecto' o 'registroEmpresa'.
 * @returns {boolean} - Retorna true si todos los campos son válidos.
 */
function validarFormularioDuenio(tipo) {
    let campos = [];

    if (tipo === "miProyecto") {
        campos = ['#nombreE', '#primerApellidoE', '#segundoApellidoE', '#correoP'];
    } else if (tipo === "registroEmpresa") {
        campos = ['#nombreCompleto', '#nombreEmpresa', '#telefono', '#emailEmpresa'];
    }
    return campos.every(campo => validarDuenio($(campo)));
}

/**
 * Cambia el ícono de un elemento.
 * @param {string} selector - Selector del elemento.
 * @param {string} claseAntigua - Clase antigua del ícono.
 * @param {string} claseNueva - Clase nueva del ícono.
 */
function actualizarIcono(selector, claseAntigua, claseNueva) {
    const iconElement = document.querySelector(selector);
    if (iconElement) {
        iconElement.classList.replace(claseAntigua, claseNueva);
    } else {
        console.warn(`Elemento no encontrado: ${selector}`);
    }
}

/**
 * Actualiza las clases activas entre dos elementos.
 * @param {HTMLElement} activar - Elemento al que se le asignará la clase activa.
 * @param {HTMLElement} desactivar - Elemento al que se le removerá la clase activa.
 */
function actualizarClaseActiva(activar, desactivar) {
    activar.classList.add("active");
    desactivar.classList.remove("active");
}

/**
 * Carga los datos en el formulario a través de una llamada AJAX.
 * @param {string} url - URL del servidor para obtener datos.
 * @param {string} formularioId - ID del formulario que debe llenarse.
 */
function cargarDatosFormulario(url, formularioId) {
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'json',
        success: function (data) {
            if (data.status === 0 && data.responseObject) {
                const duenio = data.responseObject;

                // Llena los campos del formulario con los datos del dueño
                document.getElementById(formularioId).querySelector("#nombreE").value = duenio.nombreE;
                document.getElementById(formularioId).querySelector("#primerApellidoE").value = duenio.primerApellidoE;
                document.getElementById(formularioId).querySelector("#segundoApellidoE").value = duenio.segundoApellidoT;
                document.getElementById(formularioId).querySelector("#correoP").value = duenio.correo;
            } else {
                console.error("Error: No se recibieron datos válidos.");
            }
        },
        error: function (xhr, status, error) {
            console.error("Error cargando datos: ", error);
        }
    });
}

/**
 * Obtiene los datos del formulario.
 * @returns {Object} - Objeto con los datos del formulario.
 */
function obtenerDatosFormulario() {
    return {
        nombreE: $("#nombreE").val(),
        primerApellidoE: $("#primerApellidoE").val(),
        segundoApellidoE: $("#segundoApellidoE").val(),
        correoP: $("#correoP").val(),
        nombreCompleto: $("#nombreCompleto").val(),
        nombreEmpresa: $("#nombreEmpresa").val(),
        telefono: $("#telefono").val(),
        emailEmpresa: $("#emailEmpresa").val()
    };
}

/**
 * Muestra un cuadro de confirmación con los datos del dueño.
 * @param {Object} datos - Datos del formulario.
 */
function mostrarConfirmacion(datos) {
    bootBoxConfirm(
            iconoInfo,
            "Agregar Dueño.",
            `Confirme los datos del Dueño:<br><br>
        <b>Nombre Completo:</b> ${datos.nombreCompleto.trim() === '' ? datos.nombreE : datos.nombreCompleto}<br>
        <b>Nombre Empresa:</b> ${datos.nombreEmpresa.trim() === '' ? datos.primerApellidoE : datos.nombreEmpresa}<br>
        <b>Teléfono:</b> ${datos.telefono.trim() === '' ? datos.segundoApellidoE : datos.telefono}<br>
        <b>Correo Electrónico:</b> ${datos.emailEmpresa.trim() === '' ? datos.correoP : datos.emailEmpresa}<br>`,
            function (result) {
                if (result) {
                    insertDuenio(datos.nombreE, datos.primerApellidoE, datos.segundoApellidoE, datos.correoP, datos.nombreCompleto, datos.nombreEmpresa, datos.telefono, datos.emailEmpresa);
                }
            }
    );
}

/**
 * Limpia los valores de todos los inputs dentro del contenedor especificado.
 * 
 * @param {HTMLElement} contenedor - El contenedor que contiene los inputs a limpiar.
 * @returns {undefined}
 */
function limpiarInputs(contenedor) {
    // Selecciona todos los elementos de entrada (input) dentro del contenedor proporcionado
    const inputs = contenedor.querySelectorAll('input');

    // Recorre cada input y establece su valor a una cadena vacía
    inputs.forEach(input => {
        input.value = '';
    });
}


function hacerEditable(inputs) {
    inputs.querySelectorAll("input").forEach(input => {
        input.removeAttribute("readonly");
        input.setAttribute("required", "required"); // Si necesitas que sean obligatorios
    });
}

function hacerReadonly(inputs) {
    inputs.querySelectorAll("input").forEach(input => {
        input.setAttribute("readonly", "readonly");
        input.removeAttribute("required"); // Si necesitas quitar la obligatoriedad
    });
}

function insertDuenio(nombreE, primerApellidoE, segundoApellidoE, correoP, nombreCompleto, nombreEmpresa, telefono, emailEmpresa) {
    $.ajax({
        type: 'POST',
        url: "../../app/registro/Duenio_SRV.do",
        data: {
            //accion: 'agregarOK',
            nombreE: nombreE,
            primerApellidoE: primerApellidoE,
            segundoApellidoE: segundoApellidoE,
            correoP: correoP,
            nombreCompleto: nombreCompleto,
            nombreEmpresa: nombreEmpresa,
            telefono: telefono,
            emailEmpresa: emailEmpresa
        },
        dataType: 'JSON',
        beforeSend: function () {
            $("#pageLoader").show();
        },
        complete: function () {
            $("#pageLoader").hide();
        },
        success: function (response) {
            if (response.status === 0) {
                MensajeRedirect(iconoCorrecto, 'Dueño Insertado.', 'Se inserto el Dueño correctamente', '/ControlProyecto/views/Paginas/RegistroProyecto.jsp');
            } else if (response.status === -100) {
                MensajeRedirect(iconoError, 'Error al insertar', 'No se agrego el Dueño', '/ControlProyecto/views/Paginas/RegistroDuenio.jsp');
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            bootBoxAlert(iconoError, 'Ocurrió un error.', 'Ocurrió un error al realizar la petición:<br/>' +
                    'Estatus: ' + '<strong>' + textStatus + ' </strong><br/>' +
                    'Error: ' + '<strong>' + errorThrown + '</strong><br/>' +
                    'Por favor, notifique a su administrador de este error<br/>.');
        }
    });
}
