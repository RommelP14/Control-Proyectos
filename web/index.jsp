<%-- 
    Document   : index
    Created on : 13/08/2024, 11:40:51 AM
    Author     : Alex Antonio Suárez Sánchez
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control Proyectos</title>
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        
        <link href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/lib/font-awesome.min.css" rel="stylesheet">
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/catalogos-modal.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/jquery-ui.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/catalogos-tablas.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/bootstrap-datepicker.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/cssgenerados/tables.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/lib/jquery-ui.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/lib/jquery-ui.theme.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/lib/jquery-ui.structure.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/cssgenerados/generadoPosgrado.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/cssgenerados/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/cssgenerados/listaScroll.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/cssgenerados/general.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/cssgenerados/login.css" rel="stylesheet">
        <script language="Javascript">
            document.oncontextmenu = function () {
                return false;
            };
        </script>
    </head>
    <body>
        <%@include file="views/templates/header.jsp" %>
        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="views/templates/spinner.estandarITT.jsp"%>
            </div>
        </div>
        <%
            //HttpSession session2 = request.getSession(false);

            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Expires", "0");
            response.setDateHeader("Expires", -1);

        %>
        <div class="container">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button"
                                class="navbar-toggle collapsed"
                                data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Control Proyectos</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <!-- <li><a href="http://sismaster.toluca.tecnm.mx:8090/SAM/">Login Master</a></li>-->
                            <li  class="active"><a href="index.jsp">Iniciar sesión</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </div>
        <div class="container">
            <div class="text-left" >
                <c:if test="${not empty respuesta}">
                    <div class="alert alert-danger alert-dismissible">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${respuesta}</strong> 
                    </div>
                </c:if>
                <form 
                    action="${pageContext.request.contextPath}/app/ingreso/BienvenidaControlProyectos_SRV.do" id="form-registro" name="form-registro"  method="post"
                    role="form"  class="row g-3 needs-validation form-signin">
                    <div class="form-group">
                        <label for="inpUsuario">Usuario</label>
                        <input id="inpUsuario"
                               name="inpUsuario"
                               type="text"
                               class="form-control"
                               placeholder="Usuario"
                               required
                               autofocus
                               autocomplete="off"
                               >
                    </div>
                    <div class="form-group">
                        <label for="inpPass">Contraseña</label>
                        <input id="inpPass"
                               name="inpPass"
                               type="password"
                               class="form-control"
                               placeholder="Contraseña"
                               required
                               >
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="button" id="btnInicioSesionIngresar" name="btnInicioSesionIngresar">
                        Ingresar
                        <i id="spin_carga" class="fa fa-spinner fa-spin"></i>
                    </button>
                </form>
            </div>
        </div>
        <%@include file="views/templates/footer.jsp"%>
        <script src="/CDN-ITT/js/jquery.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/jquery-ui.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/jquery.dataTables.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/base.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/tablas.estandarITT.js" type="text/javascript"></script>
        <script src ="/CDN-ITT/js/catalogos-modal.estandarITT.js" type="text/javascript"></script>
        
        <script src="/CDN-ITT/js/jquery.dataTables.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js" type="text/javascript"></script>
        <script src ="/CDN-ITT/js/catalogos-tablas.estandarITT.js" type="text/javascript"></script>
        <script src="/CDN-ITT/js/spinner.estandarITT.js" type="text/javascript"></script>
        <script src ="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js" type="text/javascript"></script>
        <script src ="/CDN-ITT/js/bootstrap-datepicker.es.estandarITT.js" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/js/lib/bootbox.all.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/bootstrap-submenu.js"></script>
        <script src="${pageContext.request.contextPath}/js/jsgenerados/activar_submenu.js"></script>
        <script  src="${pageContext.request.contextPath}/js/jsgenerados/mensajes.js"></script>
        <script src="${pageContext.request.contextPath}/js/jsgenerados/validaciones.js"></script>
        <script  src="${pageContext.request.contextPath}/js/jsgenerados/loginVal_Index.js"></script>
    </body>
</html>
