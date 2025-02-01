<%--
    Document   : project_registration
    Created on : 13/03/2023, 07:47:37 PM
    Author     : Carolina
--%>

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
        <link rel="icon" href="/CDN-ITT/img/logo.png">
    </head>
    <body>
        <%@include file="/views/templates/header.jsp" %>
        <div class="container">
            <%@include file="/views/templates/navbarJefeDepto.jsp" %>
            <div id="cont">
                <div id="registroproyectoP" name="panel" class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Registro proyecto</h3>
                    </div>
                    <div class="panel-body">
                        <form id="formulario_proyecto" name="formulario_proyecto">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="small">Título</label>
                                    <div>
                                        <span></span>
                                        <input id="titulo" 
                                               name="titulo" 
                                               class="form-control input-sm" 
                                               type="text"
                                               placeholder="Ingrese el título 3-150 caracteres" 
                                               maxlength="150"
                                               minlength="3"
                                               autocomplete="off"
                                               required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="small">Tipo de proyecto</label>
                                    <div>
                                        <span></span>
                                        <select id="tipo" name="tipo" class="form-control input-sm">
                                            <option value="0">Seleccione una opción</option>
                                            <option value="residencia">Residencia</option>
                                            <option value="titulacion">Titulación</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="small">Planteamiento del problema</label>
                                    <div>
                                        <span><i></i></span>
                                        <textarea class="form-control input-t" 
                                                  type="textarea"
                                                  name="planteamiento" 
                                                  id="planteamiento" 
                                                  placeholder="Ingrese el planteamiento del problema 3-1000 caracteres" 
                                                  maxlength="1000"
                                                  minlength="3"
                                                  autocomplete="off"
                                                  required></textarea>
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <label class="small">Justificación</label>
                                    <div>
                                        <span></span>
                                        <textarea class="form-control input-t" 
                                                  type="textarea"
                                                  name="justificacion" 
                                                  id="justificacion"  
                                                  placeholder="Ingrese la justificación 3-1000 caracteres" 
                                                  maxlength="1000"
                                                  minlength="3"
                                                  autocomplete="off"
                                                  required
                                                  ></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="small">Alcances</label>
                                    <div>
                                        <span></span>
                                        <textarea class="form-control input-t" 
                                                  type="textarea"
                                                  name="alcances" 
                                                  id="alcances"  
                                                  placeholder="Ingrese el alcance 3-1000 caracteres" 
                                                  maxlength="1000"
                                                  minlength="3"
                                                  autocomplete="off"
                                                  required
                                                  ></textarea>
                                    </div>
                                </div>              
                            </div>
                            <div align="center">
                                <button id="guardarP" 
                                        class="btn btn-primary"
                                        type="button ">
                                    <i class="fa fa-check-circle"></i>
                                    Verificar</button>
                            </div>    
                        </form>
                    </div>
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
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/validaInputs.js"></script> 
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/funciones.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jsgenerados/registro_proyecto.js" type="text/javascript"></script>

    </body>
</html>
