
$("#pageLoader").hide();
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#form_registroTituResi_aprobado").addEventListener('submit', function (event)
    {
        event.preventDefault();
        const clickedButton = event.submitter; // Obtiene el botón que disparó el evento
        let botonPresionado = clickedButton.id === "denegar_proyecto" ? "proyecto_Denegado": "proyecto_Aceptado";

        const datos = obtenerDatosFormularioAceptacionProyecto();
        enviarDatosAlServlet(datos, botonPresionado);
    });
});

/**
 * Obtiene los datos del formulario.
 * @returns {Object} - Objeto con los datos del formulario.
 */
function obtenerDatosFormularioAceptacionProyecto()
{
    return {
        noFolio: $("#idNoFolio").val()
    };
}

/**
 * Envía los datos del proyecto al servlet para comparar.
 * @param {Object} datos - Información del proyecto a comparar.
 */
function enviarDatosAlServlet(datos, botonPresionado)
{
    $.ajax({
        type: 'POST',
        url: "../../app/registro/Registro_TR_SRV.do",
        data: {
            noFolio: datos.noFolio,
            btnPresionado: botonPresionado
        },
        dataType: 'JSON',
        beforeSend: function ()
        {
            $("#pageLoader").show(); // Mostrar cargador
        },
        complete: function ()
        {
            $("#pageLoader").hide(); // Ocultar cargador
        },
        success: function (response)
        {
            if (response.status === 0)
            {
                MensajeRedirect(iconoCorrecto, response.responseObject, 'Acción realizada de manera correcta', '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            } else if (response.status === -100)
            {
                MensajeRedirect(iconoError, 'Error', 'No se acepto el proyecto', '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown)
        {
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

