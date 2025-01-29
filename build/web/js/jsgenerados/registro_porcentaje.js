document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#formulario_porcentaje").addEventListener('submit', function (event) {
        event.preventDefault();
        const datos = obtenerDatosFormularioPorcentaje();
        console.log(datos);
        mostrarConfirmacion(datos);
    });
});

/**
 * Obtiene los datos del formulario.
 * @returns {Object} - Objeto con los datos del formulario.
 */
function obtenerDatosFormularioPorcentaje() {
    return {
        porcentaje_min: $("#porcentaje_min").val(),
        porcentaje_max: $("#porcentaje_max").val()
    };
}

/**
 * Muestra un cuadro de confirmación con los datos del proyecto.
 * @param {Object} datos - Datos del formulario.
 */
function mostrarConfirmacion(datos) {
    bootBoxConfirm(
            iconoInfo,
            "Cambio de porcentaje.",
            `Confirme los porcentajes asignados:<br><br>
        <b>Porcentaje mínimo:</b> ${datos.porcentaje_min}<br>
        <b>Porcentaje máximo:</b> ${datos.porcentaje_max}<br>`,
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
        url: "../../app/registro/Porcentaje_SRV.do",
        data: {
            porcentaje_min: datos.porcentaje_min,
            porcentaje_max: datos.porcentaje_max
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
                MensajeRedirect(iconoCorrecto, 'Porcentaje Actualizado.', 'Se actualizo el porcentaje correctamente', '/ControlProyecto/views/jefes/Paginas/Porcentaje.jsp');
            } else if (response.status === -100) {
                MensajeRedirect(iconoError, 'Error al insertar', 'No se agrego el Dueño', '/ControlProyecto/views/jefes/Paginas/Porcentaje.jsp');
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