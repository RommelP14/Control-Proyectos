
$("#pageLoader").hide();
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#formulario_avances").addEventListener('submit', function (event) {
        event.preventDefault();
        if (!validarFormularioAvance()) {
            return;
        }
        const datos = obtenerDatosFormularioAvances();
        console.log(datos);
        mostrarConfirmacion(datos);
    });
});

/**
 * Obtiene los datos del formulario.
 * @returns {Object} - Objeto con los datos del formulario.
 */
function obtenerDatosFormularioAvances() {
    return {
        idFolio: $("#idFolio").val(),
        porcentaje: $("#porcentaje").val(),
        evidencia: $("#nombreEmpresa").val(),
        descripcion: $("#nombreCompleto").val()
    };
}

/**
 * Valida todos los campos del formulario del dueño.
 * @returns {boolean} - Retorna true si todos los campos son válidos.
 */
function validarFormularioAvance() {
    const campos = [
        '#porcentaje',
        '#nombreEmpresa',
        '#nombreCompleto'
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
            "Agregar Avance.",
            `Confirme algunos datos del Avance:<br><br>
            <b>Nuevo porcentaje (Actualización):</b> ${datos.porcentaje}<br>
            <b>Evidencia del avance:</b> ${datos.evidencia}<br>`,
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
        url: "../../app/registro/Registro_Avance_SRV.do",
        data: {
            accion: 'registro',
            idFolio: datos.idFolio,
            porcentaje: datos.porcentaje,
            evidencia: datos.evidencia,
            descripcion: datos.descripcion
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
                MensajeRedirect(iconoCorrecto, 'Avance Insertado.', 'Se inserto el Avance correctamente', '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            } else if (response.status === -900)
            {
                MensajeRedirect(iconoError, 'Porcentaje Incorrecto', response.mensaje, '/ControlProyecto/views/Paginas/RegistroAvances.jsp');
            }
            if (response.status === -100) {
                MensajeRedirect(iconoError, 'Error al insertar', 'No se agrego el Avance', '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
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