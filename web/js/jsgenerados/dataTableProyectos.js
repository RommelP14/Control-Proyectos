
$("#pageLoader").hide();
$('[data-toggle="tooltip"]').tooltip();
var tablaProyectos = $('#tablaProyectos').DataTable({
    order: [[0, 'asc']],
    "language": {
        "search": "Buscar:",
        "lengthMenu": "Mostrando _MENU_ registros por página",
        "zeroRecords": "No se ha encontrado ningún registro.",
        "info": "Mostrando página _PAGE_ de _PAGES_",
        emptyTable: "No se ha encontrado ningún registro.",
        "infoEmpty": "Sin registros",
        "oPaginate": {
            "sNext": "Siguiente",
            "sPrevious": "Anterior"
        }
    }
});
$("#btnIrBuscaProyecto").hide();
$("#btnBorrarProyecto").hide();
$("#btnRegistroColaboradores").hide();
$("#btnRegistroAvances").hide();
$("#modal_ver_datos").hide();
$("#btnCalificacionResidencia").hide();

let SeEstaModificandoProyecto = false;
let idProyecto, nombreProyecto, estadoProyecto, tipo_proyecto, aprobacionProyecto, progresoProyecto;

/**
 * Select Row
 */
$("#containerPrincipalContacto").on('click', '#tablaProyectos tbody tr', function () {
    if (!SeEstaModificandoProyecto) {
        let filaSeleccionada = $(this);
        // Si ya estaba seleccionada, la deseleccionamos
        if (filaSeleccionada.hasClass("selected")) {
            ocultarBotones();
            filaSeleccionada.removeClass("selected");
            return;
        }

        datosProyecto = tablaProyectos.row(filaSeleccionada).data();
        idProyecto = filaSeleccionada.attr("id");
        nombreProyecto = filaSeleccionada.attr("nombre-proyecto");
        estadoProyecto = filaSeleccionada.attr("estado-proyecto");
        tipo_proyecto = filaSeleccionada.attr("tipo_proyecto");
        aprobacionProyecto = filaSeleccionada.attr("situacion-proyecto");
        progresoProyecto = filaSeleccionada.attr("progreso-proyecto");

        seleccionarFilaTabla(idProyecto);
        mostrarBotones(idProyecto);
    }
});



/**
 * Seleccionar fila en la tabla
 */
function seleccionarFilaTabla(idProyecto) {
    $("#tablaProyectos tbody tr").removeClass("selected"); // Quitamos selección de otras filas
    $("#tablaProyectos tbody tr[id='" + idProyecto + "']").addClass("selected"); // Seleccionamos la fila actual
}

/**
 * Mostrar botones según la aprobación del proyecto
 */
function mostrarBotones(idProyecto) {
    $("#btnBorrarProyecto").show();
    $("#modal_ver_datos").show();

    if (aprobacionProyecto === 'Por aprobar') {
        $("#btnIrBuscaProyecto").show();
    } else {
        $("#btnIrBuscaProyecto").hide();
    }
    
    if (aprobacionProyecto === 'proyecto aceptado' && tipo_proyecto === 'residencia') {
        $("#btnCalificacionResidencia").show();
    } else {
        $("#btnCalificacionResidencia").hide();
    }

    consultarColaboradores(idProyecto).then(function (numeroEncontrados) {
        if (aprobacionProyecto === 'proyecto aceptado' && numeroEncontrados < 5 && tipo_proyecto === 'proyecto')
        {
            $("#btnRegistroColaboradores").show();
        } else {
            if (aprobacionProyecto === 'proyecto aceptado' && (tipo_proyecto === 'residencia' || tipo_proyecto === 'titulacion') && numeroEncontrados === 0)
            {
                $("#btnRegistroColaboradores").show();
            } else
            {
                $("#btnRegistroColaboradores").hide();
            }
        }

        if (numeroEncontrados >= 1 && progresoProyecto < 100) {
            $("#btnRegistroAvances").show();
        } else {
            $("#btnRegistroAvances").hide();
        }
    });
}

/**
 * Oculta todos los botones
 */
function ocultarBotones() {
    $("#btnIrBuscaProyecto").hide();
    $("#btnBorrarProyecto").hide();
    $("#btnRegistroColaboradores").hide();
    $("#btnRegistroAvances").hide();
    $("#modal_ver_datos").hide();
}

/**
 * Realiza una consulta AJAX para obtener el estado del proyecto
 */
function consultarColaboradores(idProyecto) {
    return $.ajax({
        type: 'GET',
        url: "../../app/ver/Ver_Proyectos_View_SRV.do",
        data: {accion: 'consultaColaborador', idProyecto: idProyecto},
        dataType: 'JSON'
    }).then(function (response) {
        if (response.status === 0) {
            console.log("Número de colaboradores encontrados: " + response.responseObject);
            return response.responseObject; // Devuelve el número de colaboradores encontrados
        } else {
            console.error("Error en la respuesta del servidor");
            return 0; // En caso de error, asumimos que no hay colaboradores
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.error("Error en la petición AJAX:", textStatus, errorThrown);
        return 0; // En caso de error, asumimos que no hay colaboradores
    });
}

/**
 * Eliminar Proyecto
 */
$("#containerPrincipalContacto").on("click", '#btnBorrarProyecto', function () {
    let nombreP = datosProyecto[1];
    let folio = datosProyecto[0];

    bootBoxConfirm(iconoInfo, "Eliminar Proyecto.",
            "¿Desea Eliminar este Proyecto? <br><br>" +
            "Nombre: " + nombreP + "<r><br>" +
            "Folio: " + folio,
            function (result) {
                if (result) {
                    eliminarProyecto(idProyecto);
                }
            }
    );
});

function eliminarProyecto(idProyecto) {
    $.ajax({
        type: 'POST',
        url: "../../app/ver/Ver_Proyectos_View_SRV.do",
        data: {accion: 'eliminar', idProyecto: idProyecto},
        dataType: 'JSON',
        beforeSend: function () {
            $("#pageLoader").show();
        },
        complete: function () {
            $("#pageLoader").hide();
        },
        success: function (response) {
            if (response.status === 0) {
                MensajeRedirect(iconoCorrecto, 'Proyecto eliminado.', 'Se eliminó el Proyecto correctamente', '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            } else if (response.status === -200) {
                MensajeRedirect(iconoError, 'Error al eliminar el Proyecto', 'No se pudo eliminar el Proyecto' + "<br><br> Contacte con el administrador del sistema.", '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            } else {
                MensajeRedirect(iconoError, 'Error al eliminar el Proyecto', 'No se pudo eliminar el Proyecto' + "<br><br> Por favor, notifique a su administrador de este error.", '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
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

/**
 * Consultar Proyecto
 */
$('#btnIrBuscaProyecto').click(function () {
    $.ajax({
        type: 'GET',
        url: "../../app/ver/Ver_Proyectos_View_SRV.do",
        data: {accion: 'verificaEstado', idProyecto: idProyecto, estadoProyecto: estadoProyecto},
        dataType: 'JSON',
        beforeSend: function () {
            $("#pageLoader").show();
        },
        complete: function () {
            $("#pageLoader").hide();
        },
        success: function (response) {
            if (response.status === -1000) {
                armaUrlAprobacion(response.responseObject);
            } else if (response.status === -2000)
            {
                armaUrlParaRevision(response.responseObject);
            } else if (response.status === -200) {
                MensajeRedirect(iconoError, 'Error al consultar el Proyecto', 'No se pudo buscar el Proyecto' + "<br><br> Contacte con el administrador del sistema.", '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            } else {
                MensajeRedirect(iconoError, 'Error al consultar el Proyecto', 'No se pudo buscar el Proyecto' + "<br><br> Por favor, notifique a su administrador de este error.", '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            bootBoxAlert(iconoError, 'Ocurrió un error.', 'Ocurrió un error al realizar la petición:<br/>' +
                    'Estatus: ' + '<strong>' + textStatus + ' </strong><br/>' +
                    'Error: ' + '<strong>' + errorThrown + '</strong><br/>' +
                    'Por favor, notifique a su administrador de este error<br/>.');
        }
    });
});

/**
 * Registrar Colaboradores
 */
$('#btnRegistroColaboradores').click(function () {
    $.ajax({
        type: 'GET',
        url: "../../app/ver/Ver_Registro_Colaboradores_View_SRV.do",
        data: {idProyecto: idProyecto},
        dataType: 'JSON',
        beforeSend: function () {
            $("#pageLoader").show();
        },
        complete: function () {
            $("#pageLoader").hide();
        },
        success: function (response) {
            if (response.status === 0) {
                armaUrlParaRegistroColaborador(response.responseObject);
            } else {
                MensajeRedirect(iconoError, 'Error al intentar registrar un colaborador', 'No se pudo registrar colaborador' + "<br><br> Por favor, notifique a su administrador de este error.", '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            bootBoxAlert(iconoError, 'Ocurrió un error.', 'Ocurrió un error al realizar la petición:<br/>' +
                    'Estatus: ' + '<strong>' + textStatus + ' </strong><br/>' +
                    'Error: ' + '<strong>' + errorThrown + '</strong><br/>' +
                    'Por favor, notifique a su administrador de este error<br/>.');
        }
    });
});


/**
 * Registrar Avances
 */
$('#btnRegistroAvances').click(function () {
    $.ajax({
        type: 'GET',
        url: "../../app/ver/Ver_Registro_Avances_View_SRV.do",
        data: {idProyecto: idProyecto},
        dataType: 'JSON',
        beforeSend: function () {
            $("#pageLoader").show();
        },
        complete: function () {
            $("#pageLoader").hide();
        },
        success: function (response) {
            if (response.status === 0) {
                armaUrlParaRegistroAvances(response.responseObject);
            } else {
                MensajeRedirect(iconoError, 'Error al intentar registrar un Avance', 'No se pudo registrar Avance' + "<br><br> Por favor, notifique a su administrador de este error.", '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            bootBoxAlert(iconoError, 'Ocurrió un error.', 'Ocurrió un error al realizar la petición:<br/>' +
                    'Estatus: ' + '<strong>' + textStatus + ' </strong><br/>' +
                    'Error: ' + '<strong>' + errorThrown + '</strong><br/>' +
                    'Por favor, notifique a su administrador de este error<br/>.');
        }
    });
});

//Modal ver datos
$('#modal_ver_datos').on("click", function (e) {
    console.log("foliooooooo: " + idProyecto);
    document.getElementById("proyectoFrame").innerHTML = "";
    $.ajax({
        type: 'Get',
        url: '../../app/ver/Pdf_SRV.do',
        data: {folio: idProyecto},
        success: function (respuesta) {
            var iframe = document.createElement('iframe');
            var html = respuesta;
            iframe.srcdoc = html;
            iframe.width = '100%';
            iframe.height = '400px';
            document.getElementById("proyectoFrame").appendChild(iframe);
        }
    });
});


/**
 * Asignar calificación
 */
$('#btnCalificacionResidencia').click(function () {
    $.ajax({
        type: 'POST',
        url: "../../app/registro/Residencia_SRV.do",
        data: {accion: 'agregar', idProyecto: idProyecto},
        dataType: 'HTML',
        beforeSend: function () {
            $("#pageLoader").show();
        },
        complete: function () {
            $("#pageLoader").hide();
        },
        success: function (response) {
            $("#modal_editar_calificacion_resi .modal-body").html(response);

            $("#modal_editar_calificacion_resi").modal("show");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            bootBoxAlert('<i class=\"fa fa-info-circle fa-lg iconoError\"></i> ', 'Ocurrió un error.', 'Ocurrió un error al realizar la petición:<br/>' +
                    'Estatus: ' + '<strong>' + textStatus + ' </strong><br/>' +
                    'Error: ' + '<strong>' + errorThrown + '</strong><br/>' +
                    'Por favor, notifique a su administrador de este error<br/>.');
        }
    });
});

function armaUrlParaRegistroAvances(idNoFolio) {
    // Convertir el objeto a una cadena JSON y codificarlo
    var idNoFolioStr = encodeURIComponent(JSON.stringify(idNoFolio));
    var url = `/ControlProyecto/app/ver/RedireccionaVistasAvances_View_SRV.do?idFolio=${idNoFolioStr}`;
    window.location.href = url;
}

function armaUrlParaRegistroColaborador(idNoFolio) {
    // Convertir el objeto a una cadena JSON y codificarlo
    var idNoFolioStr = encodeURIComponent(JSON.stringify(idNoFolio));
    var url = `/ControlProyecto/app/ver/RedireccionaVistasColaboradores_View_SRV.do?idFolio=${idNoFolioStr}`;
    window.location.href = url;
}

function armaUrlAprobacion(idNoFolio) {
    // Convertir el objeto a una cadena JSON y codificarlo
    var idNoFolioStr = encodeURIComponent(JSON.stringify(idNoFolio));
    var url = `/ControlProyecto/app/ver/RedireccionaVistas_View_SRV.do?accion=aprobacion&idFolio=${idNoFolioStr}`;
    window.location.href = url;
}

function armaUrlParaRevision(idNoFolio) {
    // Convertir el objeto a una cadena JSON y codificarlo
    var idNoFolioStr = encodeURIComponent(JSON.stringify(idNoFolio));
    var url = `/ControlProyecto/app/ver/RedireccionaVistas_View_SRV.do?accion=denegado&idFolio=${idNoFolioStr}`;
    window.location.href = url;
}

function getPDF(folio) {
    document.getElementById("proyectoFrame").innerHTML = "";
    $.ajax({
        type: 'Get',
        url: '../../app/ver/Pdf_SRV.do',
        data: {folio: folio},
        success: function (respuesta) {
            var iframe = document.createElement('iframe');
            var html = respuesta;
            iframe.srcdoc = html;
            iframe.width = '100%';
            iframe.height = '400px';
            document.getElementById("proyectoFrame").appendChild(iframe);
        }
    });
}

function getPDF2(folio) {
    document.getElementById("pdfFolioA").innerHTML = "";
    $.ajax({
        type: 'GET',
        url: '../../app/ver/Pdf_SRV.do',
        data: {folio: folio},
        success: function (respuesta) {
            var iframe = document.createElement('iframe');
            iframe.srcdoc = respuesta;
            iframe.width = '100%';
            iframe.height = '400px';
            document.getElementById("pdfFolioA").appendChild(iframe);
        },
        error: function () {
            document.getElementById("pdfFolioA").innerHTML = "<p class='text-danger'>Selecciona un Folio</p>";
        }
    });
}

// cancelar 
function redirectProyectos() {
    window.location.href = '/ControlProyecto/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos';
}
