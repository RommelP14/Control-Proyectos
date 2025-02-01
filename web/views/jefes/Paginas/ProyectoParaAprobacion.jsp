<%@page import="manage.bean.proyectos.Proyecto_MB"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Proyecto_MB proyecto = (Proyecto_MB) request.getAttribute("proyecto_Mb");
    if (proyecto == null)
    {
        System.out.println("Error: proyecto_Mb es null en ProyectoParaAprobacion.jsp");
        response.sendRedirect(Utils.constantes.Constantes.LOGIN_MASTER);
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Proyecto Para Aprobación</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-modal.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/jquery-ui.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/bootstrap-datepicker.estandarITT.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
    </head>
    <body>
        <%@include file="/views/templates/header.jsp"%>
        <div class="container">
            <div id="pageLoader">
                <div id="pageSpinner">
                    <%@include file="/views/templates/spinner.estandarITT.jsp" %>
                </div>
            </div>
            <%@include file="/views/templates/navbarJefeDepto.jsp"%>
            <div id="containerPrincipalContacto">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Proyecto: <b id="folio"><c:out value="${proyecto_Mb.noFolio}"/></b></h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-12">
                            <h4>Estado: <b style="color: #55B77A"><c:out value="${proyecto_Mb.estado}"/></b></h4>
                        </div>
                        <div class="col-md-12">
                            <div id="proyectoFrame"></div>
                        </div>
                        <div class="row text-center" style="margin: 1rem">
                            <form id="form_registroTituResi_aprobado">
                                <input type="hidden" id="idNoFolio" name="idNoFolio" value="${proyecto_Mb.noFolio}"/>
                                <button class="btn btn-borrar btn-sm" id="denegar_proyecto">Denegar proyecto</button>
                                <button class="btn btn-agregar btn-sm d-none" id="aceptar_proyecto">Aceptar proyecto</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/templates/footer.jsp"%>
        <script src="/CDN-ITT/js/jquery.estandarITT.js"></script>
        <script src="/CDN-ITT/js/jquery-ui.estandarITT.js"></script>
        <script src="/CDN-ITT/js/base.estandarITT.js"></script>
        <script src="/CDN-ITT/js/spinner.estandarITT.js"></script>
        <script src="/CDN-ITT/js/jquery.dataTables.estandarITT.js"></script>
        <script src="/CDN-ITT/js/tablas.estandarITT.js"></script>
        <script src="/CDN-ITT/js/catalogos-modal.estandarITT.js"></script>
        <script src="/CDN-ITT/js/catalogos-tablas.estandarITT.js"></script>
        <script src="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js"></script>
        <script src="/CDN-ITT/js/bootstrap-datepicker.es.estandarITT.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/bootbox.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/bootbox.estandarITT.js"></script>   
        <script src="${pageContext.request.contextPath}/js/lib/bootboxActions.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/bootstrap-toggle.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.13.3/js/standalone/selectize.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jsgenerados/dataTableProyectos.js"></script>
        <script src="${pageContext.request.contextPath}/js/jsgenerados/constantes.js"></script>  
        <script src="${pageContext.request.contextPath}/js/jsgenerados/funciones.js"></script>
        <script src="${pageContext.request.contextPath}/js/jsgenerados/registro_tablas_T_R.js"></script>

        <script>
            $(document).ready(function () {
                let noFolio = $("#folio").text().trim(); // Obtiene el folio del proyecto
                if (noFolio) {
                    getPDF(noFolio);
                } else {
                    console.error("No se encontró el número de folio.");
                }
            });
        </script>

    </body>
</html>

