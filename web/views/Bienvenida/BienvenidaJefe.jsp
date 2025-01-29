
<%@page import="manageBean.empleado.Empleado_MB"%>
<%@page import="Utils.constantes.Constantes"%>
<%@page import="manageBean.usuarios.Usuarios_MB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("id_usuario") == null) {
        response.sendRedirect("/ControlProyecto/Master_Srv?logout");
    }
%>

<%
    if (session.getAttribute("usuario") != null)
    {
        Empleado_MB empleado = (Empleado_MB) session.getAttribute("usuario");
        request.setAttribute("usuario", empleado);
%>
<!DOCTYPE html>
<html>
    <head>                      
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Control Proyectos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script language="Javascript">
            document.oncontextmenu = function () {
                return false;
            };
        </script>
    </head>
    <body>
        <%@include file="/views/templates/header.jsp"%>
        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="/views/templates/spinner.estandarITT.jsp" %>
            </div>
        </div>
        <div class="container">
            <%@include file="/views/templates/navbarJefeDepto.jsp" %>
            <%@include file="/views/templates/logosistema.jsp" %>
            <footer>
                <%@include file="/views/templates/footer.jsp"%>
            </footer>

            <script type="text/javascript" src ="/CDN-ITT/js/jquery.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/base.estandarITT.js"></script>
        </div>
    </body>
</html>
<%    } else
    {
        response.sendRedirect(Constantes.LOGIN_MASTER);
    }
%>
