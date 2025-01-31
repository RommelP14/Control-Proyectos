
$("#pageLoader").hide();
document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#formulario_avances").addEventListener('submit', function (event) {
        event.preventDefault();
        const datos = obtenerDatosFormularioProyecto();
        console.log(datos);
        mostrarConfirmacion(datos);
    });
});

/**
 * Obtiene los datos del formulario.
 * @returns {Object} - Objeto con los datos del formulario.
 */
function obtenerDatosFormularioProyecto() {
    return {
        titulo: $("#titulo").val(),
        tipo: $("#tipo").val(),
        planteamiento: $("#planteamiento").val(),
        justificacion: $("#justificacion").val(),
        alcances: $("#alcances").val()
    };
}

/**
 * Muestra un cuadro de confirmación con los datos del proyecto.
 * @param {Object} datos - Datos del formulario.
 */
function mostrarConfirmacion(datos) {
    bootBoxConfirm(
            iconoInfo,
            "Agregar Proyecto.",
            `Confirme algunos datos del Proyecto:<br><br>
        <b>Título del Proyecto:</b> ${datos.titulo}<br>
        <b>Tipo de Proyecto:</b> ${datos.tipo}<br>`,
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
        url: "../../app/registro/Proyecto_SRV.do",
        data: {
            accion: 'comparar',
            titulo: datos.titulo,
            tipo: datos.tipo,
            planteamiento: datos.planteamiento,
            justificacion: datos.justificacion,
            alcances: datos.alcances
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
                manejarRespuestaComparacion(response); // Llama a la función para manejar el resultado
            } else {
                bootBoxAlert(
                    iconoError,
                    'Error al comparar',
                    'No se pudo realizar la comparación. Intente más tarde.'
                );
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