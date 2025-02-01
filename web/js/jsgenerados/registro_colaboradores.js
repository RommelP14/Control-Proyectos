
$("#pageLoader").hide();
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#formulario_colaboradores").addEventListener('submit', function (event) {
        event.preventDefault();
        if (!validarFormularioColaborador()) {
            return; 
        }
        const datos = obtenerDatosFormularioColaborador();
        console.log(datos);
        mostrarConfirmacion(datos);
    });
});

/**
 * Obtiene los datos del formulario.
 * @returns {Object} - Objeto con los datos del formulario.
 */
function obtenerDatosFormularioColaborador() {
    return {
        idFolio: $("#idFolio").val() ,
        nombreE: $("#nombreE").val(),
        primerApellidoE: $("#primerApellidoE").val(),
        segundoApellidoE: $("#segundoApellidoE").val(),
        carrera: $("#carrera").val(),
        correoP: $("#correoP").val(),
        noControl: $("#noControl").val()
    };
}

/**
 * Valida todos los campos del formulario del dueño.
 * @returns {boolean} - Retorna true si todos los campos son válidos.
 */
function validarFormularioColaborador() {
    const campos = [
        '#nombreE',
        '#primerApellidoE',
        '#segundoApellidoE',
        '#carrera',
        '#correoP',
        '#noControl'
    ];
    return campos.every(campo => validarDuenio($(campo)));
}

/**
 * Muestra un cuadro de confirmación con los datos del proyecto.
 * @param {Object} datos - Datos del formulario.
 */
function mostrarConfirmacion(datos) {
    bootBoxConfirm(
            iconoInfo,
            "Agregar Colaborador.",
            `Confirme algunos datos del Colaborador:<br><br>
        <b>Nombre del colaborador:</b> ${datos.nombreE}<br>
        <b>Primer apellido:</b> ${datos.primerApellidoE}<br>
        <b>Segundo apellido:</b> ${datos.segundoApellidoE}<br>
        <b>Carrera:</b> ${datos.carrera}<br>
        <b>Correo electrónico:</b> ${datos.correoP}<br>
        <b>N0.Control:</b> ${datos.noControl}<br>`,
            function (result) {
                if (result) {
                    enviarDatosAlServlet(datos);
                }
            }
    );
}

/**
 * Envía los datos del proyecto al servlet para comparar.
 * @param {Object} datos - Información del proyecto a comparar.
 */
function enviarDatosAlServlet(datos) {
    $.ajax({
        type: 'POST',
        url: "../../app/registro/Registro_Colaborador_SRV.do",
        data: {
            accion: 'registro',
            idFolio: datos.idFolio,
            nombreE: datos.nombreE,
            primerApellidoE: datos.primerApellidoE,
            segundoApellidoE: datos.segundoApellidoE,
            carrera: datos.carrera,
            correoP: datos.correoP,
            noControl: datos.noControl
        },
        dataType: 'JSON',
        beforeSend: function () {
            $("#pageLoader").show(); // Mostrar cargador
        },
        complete: function () {
            $("#pageLoader").hide(); // Ocultar cargador
        },
        success: function (response) {
            if (response.status === 0) {
                MensajeRedirect(iconoCorrecto, 'Colaborador Insertado.', 'Se inserto el Colaborador correctamente', '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            } else if (response.status === -100) {
                MensajeRedirect(iconoError, 'Error al insertar', 'No se agrego el Colaborador', '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            bootBoxAlert(
                iconoError,
                'Ocurrió un error.',
                `Error al realizar la petición:<br/>
                 Estatus: <strong>${textStatus}</strong><br/>
                 Error: <strong>${errorThrown}</strong><br/>
                 Por favor, notifique a su administrador.`
            );
        }
    });
}