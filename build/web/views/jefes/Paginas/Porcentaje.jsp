<%-- 
    Document   : Porcentaje
    Created on : 13/03/2023, 11:40:45 AM
    Author     : Carolina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Porcentaje</title>
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-modal.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/bootstrap-datepicker.estandarITT.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/cssgenerados/general.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/views/templates/header.jsp" %>
        <div id="cont" class="container">
            <%@include file="/views/templates/navbarJefeDepto.jsp" %>
            <div id="panel" name="panel" class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Porcentajes de revisión</h3>
                </div>
                <div class="panel-body">
                    <c:if test="${not empty departamento_mb}">
                        <form id="formulario_porcentaje" name="formulario_porcentaje">
                            <input type="hidden" name="id_departamento_sam" value="${departamento_mb.id_departamento_sam}" />

                            <div class="col-md-6 col-md-offset-3">
                                <div class="form-group">
                                    <h5 align="left">Ingrese el porcentaje de similitud mínimo</h5>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-percent"></i></div>
                                        <input type="number"
                                               class="form-control" 
                                               id="porcentaje_min" 
                                               name="porcentaje_min" 
                                               placeholder="Porcentaje mínimo"
                                               value="${departamento_mb.porcentaje_min}" 
                                               required>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6 col-md-offset-3">
                                <div class="form-group">
                                    <h5 align="left">Ingrese el porcentaje de similitud máximo</h5>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-percent"></i></div>
                                        <input type="number" 
                                               class="form-control" 
                                               id="porcentaje_max" 
                                               name="porcentaje_max" 
                                               placeholder="Porcentaje máximo"
                                               value="${departamento_mb.porcentaje_max}" 
                                               required>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6 col-md-offset-3" align="center">
                                <button id="guardar_porcentaje" 
                                        name="guardar_porcentaje"
                                        class="btn btn-primary"
                                        type="submit">
                                    <i class="fa fa-save"></i> Guardar
                                </button>
                            </div> 
                        </form>
                    </c:if>

                </div>
            </div> 

            <%@include file="/views/templates/footer.jsp" %>
        </div>
        <script type="text/javascript" src="/CDN-ITT/js/jquery.estandarITT.js"></script>
        <script type="text/javascript" src="/CDN-ITT/js/jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src="/CDN-ITT/js/base.estandarITT.js"></script>
        <script type="text/javascript" src="/CDN-ITT/js/spinner.estandarITT.js"></script>
        <script type="text/javascript" src="/CDN-ITT/js/jquery.dataTables.estandarITT.js"></script>
        <script type="text/javascript" src="/CDN-ITT/js/tablas.estandarITT.js"></script>
        <script type="text/javascript" src="/CDN-ITT/js/catalogos-modal.estandarITT.js"></script>
        <script type="text/javascript" src="/CDN-ITT/js/catalogos-tablas.estandarITT.js"></script>
        <script type="text/javascript" src="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js"></script>
        <script type="text/javascript" src="/CDN-ITT/js/bootstrap-datepicker.es.estandarITT.js"></script>
        
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/funciones.js"></script> 
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/lib/bootboxActions.js"></script>
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/constantes.js"></script>
        
        <script src="${pageContext.request.contextPath}/js/lib/bootbox.all.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/lib/chart.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jsgenerados/registro_porcentaje.js" type="text/javascript"></script>
    </body>
</html>
