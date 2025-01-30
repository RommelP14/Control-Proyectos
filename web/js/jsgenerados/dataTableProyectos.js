
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

let SeEstaModificandoContacto = false;
let idContacto, institutoName, datosProyecto, paisName, estadoName;

let idProyecto, nombreProyecto, progresoProyecto, estadoProyecto, aprobacionProyecto;

/**
 * Select Row
 */
$("#containerPrincipalContacto").on('click', '#tablaProyectos tbody tr td', function () {
    if (!SeEstaModificandoContacto) {
        datosProyecto = tablaProyectos.row($(this)).data();
        idProyecto = $(this).parent('tr').attr("id");
        console.log("idProyecto: " + idProyecto)
        nombreProyecto = $(this).parent('tr').attr("nombre-proyecto");
        progresoProyecto = $(this).parent('tr').attr("progreso-proyecto");
        estadoProyecto = $(this).parent('tr').attr("estado-proyecto");
        aprobacionProyecto = $(this).parent('tr').attr("aprobacion-proyecto");
        if (!$(this).parent('tr').hasClass("selected")) {
            $("#btnIrBuscaProyecto").show();
            $("#btnBorrarProyecto").show();
        } else {
            $("#btnIrBuscaProyecto").hide();
            $("#btnBorrarProyecto").hide();
        }
        selectLib("tablaProyectos", $(this).parent().attr('id'));
    }
});

/**
 * Eliminar Contacto
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
            }else if(response.status === -2000)
            {
                armaUrlParaRevision(response.responseObject);
            }else if (response.status === -200) {
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
    console.log("folio: ", folio);
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
