<%-- 
    Document   : navbar
    Created on : 17/04/2023, 06:34:01 PM
    Author     : Ehider
--%>
<%
    if (session.getAttribute("usuario") != null)
    {
        Empleado_MB emp = (Empleado_MB) session.getAttribute("usuario");
        session.setAttribute("empleado", emp);
%>

<%@page import="manageBean.empleado.Empleado_MB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Navigation bar -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">

        <div class="navbar-header">
            <button type="button"
                    class="navbar-toggle collapsed"
                    data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="fa fa-bars" />
            </button>
            <strong><a class="navbar-brand" href="${pageContext.request.contextPath}/views/Bienvenida/BienvenidaJefe.jsp">Bienvenido</a></strong>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">

                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-user"></i> 
                        <%=emp.getNombre()%>
                        <b class="caret"></b>
                    </a>

                    <ul class="dropdown-menu dropdown-user">
                        <li>
                            <a href="${pageContext.request.contextPath}/views/Bienvenida/BienvenidaJefe.jsp" id="menu_inicio">
                                <i class="fa fa-home" aria-hidden="true"></i>
                                Inicio
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li class="dropdown-header">Proyectos</li>
                        <li>
                            <a href="${pageContext.request.contextPath}/app/registro/Registrar_View_Srv.do" id="" >
                                <i class="fa fa-file-text" aria-hidden="true"></i>
                                Registro Proyecto
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/app/ver/Ver_Proyectos_View_SRV" id="" >
                                <i class="fa fa-list" aria-hidden="true"></i>
                                Ver Proyectos
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/app/registro/Porcentaje_SRV.do" id="">
                                <i class="fa fa-percent"></i>
                                Porcentaje
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/app/ver/Ver_Proyectos_View_SRV.do?accion=listarMisProyectos" id="" >
                                <i class="fa fa-info-circle"></i>
                                Mis Proyectos
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li class="dropdown-header">Residencia</li>
                        <li>
                            <a href="/web_transporte/app/mantenimientosdrmys/crudunidad.do?accion=unidades" id="" >
                                <i class="fa fa-users" aria-hidden="true"></i>
                                Asesores
                            </a>
                        </li>
                        
                        <li class="divider"></li>
                        <li class="dropdown-header">Titulación</li>
                        <li>
                            <a href="/web_transporte/app/salidasdrmys/crudcalendar.do?accion=solpendrmys" id="" >
                                <i class="fa fa-users" aria-hidden="true"></i>
                                Revisores
                            </a>
                        </li>
                        
                        <li class="divider"></li>
                        <li class="dropdown-header">Documento</li>
                        <li>
                            <a href="${pageContext.request.contextPath}/views/EdicionFormatos/imagenes.jsp" >
                                <i class="fa fa-book"></i>
                                <m class="traductor">     Edición de Formatos</m>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/SAM">
                                <i class="fa fa-sign-out"></i>
                                Salir
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav><!--end navigation bar-->
<%    } else
    {
        response.sendRedirect(Utils.constantes.Constantes.LOGIN_MASTER);
    }
%>