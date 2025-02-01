
<%@page import="manageBean.empleado.Empleado_MB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar proyecto</title>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="${pageContext.request.contextPath}/css/cssgenerados/general.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/cssgenerados/styleRegistroDuenio.css" rel="stylesheet">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
    </head>
    <body>
        <%@include file="/views/templates/header.jsp" %>
        <div id="cont" class="container">
            <%@include file="/views/templates/navbarJefeDepto.jsp" %>
            <div id="panel" name="panel" class="panel panel-primary">
                <div class="panel-heading">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <span>Registro dueño</span>
                        <div>
                            | <a href="#" id="linkRegistroEmpresa" class="switch-links">Registro Empresa</a> |
                            <a href="#" id="linkMiProyecto" class="switch-links">Mi Proyecto</a>
                        </div>
                    </div>
                </div>
                <div class="panel-body" align="center"> 
                    <form id="formulario_duenio" name="formulario_duenio">
                        <div id="inputsMiProyecto">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <h5 align="left">Nombre(s)</h5>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-user"></i></div>
                                            <input type="text" 
                                                   class="form-control" 
                                                   id="nombreE" 
                                                   name="nombreE" 
                                                   placeholder="Nombre"
                                                   maxlength="45"
                                                   minlength="3"
                                                   autocomplete="off"
                                                   readonly
                                                   value="${empleado.nombre}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <h5 align="left">Primer Apellido</h5>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-user"></i></div>
                                            <input type="text" 
                                                   class="form-control" 
                                                   id="primerApellidoE" 
                                                   name="primerApellidoE" 
                                                   placeholder="Primer Apellido" 
                                                   maxlength="45"
                                                   minlength="3"
                                                   autocomplete="off"
                                                   readonly
                                                   value="${empleado.apellidoPa}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <h5 align="left">Segundo Apellido</h5>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-user"></i></div>
                                            <input type="text" 
                                                   class="form-control" 
                                                   id="segundoApellidoE" 
                                                   name="segundoApellidoE" 
                                                   placeholder="segundo Apellido" 
                                                   maxlength="45"
                                                   minlength="3"
                                                   autocomplete="off"
                                                   readonly
                                                   value="${empleado.apellidoMa}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <h5 align="left">Correo electrónico</h5>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-at"></i></div>
                                            <input type="text" 
                                                   id="correoP" 
                                                   name="correoP" 
                                                   class="form-control" 
                                                   maxlength="50" 
                                                   minlength="7"
                                                   placeholder="Correo personal" 
                                                   autocomplete="off"
                                                   readonly
                                                   value="${empleado.correoEmpleado}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="inputsRegistroEmpresa" class="hidden">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <h5 align="left">Nombre Completo</h5>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-user"></i></div>
                                            <input type="text" 
                                                   class="form-control" 
                                                   id="nombreCompleto" 
                                                   name="nombreCompleto" 
                                                   placeholder="Nombre Completo" 
                                                   maxlength="100"
                                                   minlength="3"
                                                   autocomplete="off"
                                                   readonly
                                                   oninput="validarInputs(nombreCompleto)">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <h5 align="left">Nombre Empresa</h5>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-building"></i></div>
                                            <input type="text" 
                                                   class="form-control" 
                                                   id="nombreEmpresa" 
                                                   name="nombreEmpresa" 
                                                   placeholder="Nombre Empresa" 
                                                   maxlength="100"
                                                   minlength="3"
                                                   autocomplete="off"
                                                   readonly
                                                   oninput="validarInputs(nombreEmpresa)">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <h5 align="left">Teléfono</h5>
                                        <div class="input-group">
                                            <div class="input-group-addon" id="iconTelefono">
                                                <i class="fa fa-phone"></i>
                                            </div>
                                            <input type="text" 
                                                   class="form-control" 
                                                   id="telefono" 
                                                   name="telefono" 
                                                   placeholder="Teléfono" 
                                                   maxlength="30"
                                                   minlength="10"
                                                   readonly
                                                   autocomplete="off"
                                                   oninput="validarInputs(this)">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <h5 align="left">Correo electrónico</h5>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-at"></i></div>
                                            <input type="email" 
                                                   class="form-control" 
                                                   id="emailEmpresa" 
                                                   name="emailEmpresa" 
                                                   placeholder="Correo electrónico" 
                                                   maxlength="50" 
                                                   minlength="7"
                                                   readonly
                                                   autocomplete="off"
                                                   oninput="validarInputs(emailEmpresa)">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div align="center">
                            <button id="btn_guardar_duenio" class="btn btn-primary" type="submit">
                                <i class="fa fa-arrow-right" aria-hidden="true"></i> Siguiente
                            </button>
                        </div> 
                    </form>
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
        <script src="${pageContext.request.contextPath}/js/lib/bootbox.all.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/lib/chart.min.js" type="text/javascript"></script>
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/lib/bootboxActions.js"></script>
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/constantes.js"></script> 
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/registro_duenio.js"></script> 
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/validaInputs.js"></script> 
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/funciones.js"></script> 
    </body>
</html>
