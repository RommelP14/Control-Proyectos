document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#formulario_proyecto").addEventListener('submit', function (event) {
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

/**
 * Maneja la respuesta del servlet después de la comparación.
 * @param {Object} response - Respuesta JSON del servidor.
 */
function manejarRespuestaComparacion(response) {
    const estatus = response.estatus;
    const folio = response.folio;
    //console.log(estatus);
    //console.log(folio);
    if (estatus === "Para aprobación") {
        MensajeRedirect(
            iconoCorrecto,
            'Proyecto Aprobado Preliminarmente.',
            `El proyecto con folio <strong>${folio}</strong> cumple con los criterios y puede ser registrado.`,
            '/ControlProyecto/views/Paginas/RegistroDuenio.jsp'
        );
    } else if (estatus === "Requiere revisión") {
        MensajeRedirect(
            iconoInfo,
            'Proyecto Requiere Revisión.',
            `El proyecto con folio <strong>${folio}</strong> presenta similitudes con otros existentes. Necesita revisión manual.`,
            '/ControlProyecto/views/Paginas/RegistroDuenio.jsp'
        );
    } else if (estatus === "Denegar") {
        MensajeRedirect(
            iconoInfo,
            'Proyecto Denegado.',
            `El proyecto con folio <strong>${folio}</strong> es demasiado similar a otros existentes, consulta con tu administrador.`,
            '/ControlProyecto/views/Paginas/RegistroDuenio.jsp'
        );
    }
 
}
