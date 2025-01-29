<%--
    Document   : compararProyectos
    Created on : 13/03/2023, 07:47:37 PM
    Author     : Carolina
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comparar proyecto</title>
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
        <link href="css/generados/general.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/views/templates/header.jsp" %>
        <c:forEach var="proyecto" items="${respuestaDetallesProyectos.responseObject}">
            <div class="container">
                <div class="col-md-12" align="center">
                    <h5 id="titulo" name="titulo"><b><c:out value="${proyecto.getTitulo()}"></c:out></b></h5>
                    </div>
                    <div class="col-md-12">
                        <label>No. Folio: </label>
                        <p id="noFolio" name="noFolio"><c:out value="${proyecto.getNoFolio()}"></c:out></p>
                    <label>Planteamiento del problema: </label>
                    <p id="avance" name="avance"><c:out value="${proyecto.getResumen()}"></c:out></p>
                    <label>Justificacion: </label>
                    <p id="estado" name="estado"><c:out value="${proyecto.getJustificacion()}"></c:out></p> 
                    <label>Alcances: </label>
                    <p id="aprobacion" name="aprobacion"><c:out value="${proyecto.getAlcances()}"></c:out></p>
                    </div>
                </div>
        </c:forEach>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\base.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\spinner.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery.dataTables.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\tablas.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\catalogos-modal.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\catalogos-tablas.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\bootstrap-datepicker.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\bootstrap-datepicker.es.estandarITT.js"></script>
        <script src="js/lib/Chart.min.js" type="text/javascript"></script>
        <script src="js/generados/Tabla.js" type="text/javascript"></script>
        <script src="js/generados/Project.js" type="text/javascript"></script>
    </body>
</html>