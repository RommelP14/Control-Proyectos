
$("#pageLoader").hide();
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#formulario_asigna_calificacion_resi").addEventListener('submit', function (event) {
        event.preventDefault();
        if (!validarFormularioCalificacion()) {
            return;
        }
        const datos = obtenerDatosFormularioCalificacion();
        console.log(datos);
        mostrarConfirmacion(datos);
    });
});

/**
 * Obtiene los datos del formulario.
 * @returns {Object} - Objeto con los datos del formulario.
 */
function obtenerDatosFormularioCalificacion() {
    return {
        idFolio: $("#idNoFolio").val(),
        porcentaje: $("#porcentaje").val()
    };
}

/**
 * Valida todos los campos del formulario del dueño.
 * @returns {boolean} - Retorna true si todos los campos son válidos.
 */
function validarFormularioCalificacion() {
    const campos = [
        '#porcentaje'
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
            "Asignar calificación.",
            `Confirme la calificación a asignar:<br><br>
            <b>Calificación:</b> ${datos.porcentaje}<br>`,
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
        url: "../../app/registro/Residencia_SRV.do",
        data: {
            accion: 'registroOK',
            idFolio: datos.idFolio,
            porcentaje: datos.porcentaje
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
                MensajeRedirect(iconoCorrecto, 'Calificación Agregada', response.mensaje, '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
                $("#modal_editar_calificacion_resi").modal("hide");
            } else if (response.status === -701 || response.status === -999) {
                MensajeRedirect(iconoError, 'Error al agregar Calificación', response.mensaje, '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
                $("#modal_editar_calificacion_resi").modal("hide");
            }
        }
        ,
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