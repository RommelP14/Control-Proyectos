<%-- 
    Document   : logosistema
    Created on : 25 ene 2025, 14:54:13
    Author     : romme
--%>
<%@page import="manageBean.empleado.Empleado_MB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="containerPrincipal">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <div class="panel-title">
                <div class="form-inline" style="font-weight: bold;">
                    <%= empleado != null ? empleado.getNombreDepartamento() : "Departamento no disponible" %>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <div class="jumbotron centerfy">
                <h1 align="center" style="border-radius:14px;background-color:#FF8D57">Bienvenido</h1>
                <img class="img-responsive" alt="Responsive image" 
                     src="${pageContext.request.contextPath}/img/LogoBienvenida.png" title="welcome-logo" 
                     style="margin: auto"
                     width="350" height="350">
            </div>
        </div>    
    </div>
</div>