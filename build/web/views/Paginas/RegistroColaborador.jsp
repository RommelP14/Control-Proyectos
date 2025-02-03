<%-- 
    Document   : Ver_datos_proyecto_modal_proyecto_colab
    Created on : 2 feb 2025, 21:57:10
    Author     : romme
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Colaboradores</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Third party style sheets -->      
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
                        <div class="panel-title">
                            <div class="form-inline">
                                <b>Informacion del Colaborador</b>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-body" style="overflow-x: auto;">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">
                                    <div class="form-inline">
                                        <b>Nuevo Colaborador</b>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-body">
                                <form id="formulario_colaboradores">
                                    <input type="hidden" id="idFolio" name="idFolio" value="${idFolio}">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Nombre(s)</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                    <input type="text" 
                                                           class="form-control input-sm" 
                                                           id="nombreE" 
                                                           name="nombreE" 
                                                           placeholder="Nombre(s)"
                                                           maxlength="49"
                                                           minlength="3"
                                                           required
                                                           autocomplete="off"
                                                           oninput="validarInputs(nombreE)">
                                                </div>
                                            </div>                                  
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Primer apellido</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                    <input type="text" 
                                                           class="form-control input-sm" 
                                                           id="primerApellidoE" 
                                                           name="primerApellidoE" 
                                                           placeholder="Primer apellido"
                                                           maxlength="49"
                                                           minlength="3"
                                                           required
                                                           autocomplete="off"
                                                           oninput="validarInputs(primerApellidoE)">
                                                </div>
                                            </div>                                  
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Segundo apellido</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                    <input type="text" 
                                                           id="segundoApellidoE" 
                                                           name="segundoApellidoE"
                                                           class="form-control input-sm" 
                                                           placeholder="Segundo apellido"
                                                           maxlength="49"
                                                           minlength="3"
                                                           required
                                                           autocomplete="off"
                                                           oninput="validarInputs(segundoApellidoE)">
                                                </div>
                                            </div>                                  
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Carrera</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fas fa-cogs"></i></span>
                                                    <input type="text" 
                                                           id="carrera"
                                                           name="carrera"
                                                           class="form-control input-sm" 
                                                           placeholder="Carrera" 
                                                           maxlength="38"  
                                                           minlength="3"
                                                           required
                                                           autocomplete="off"
                                                           oninput="validarInputs(carrera)">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Correo electrónico</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-at"></i></span>
                                                    <input type="text" 
                                                           id="correoP" 
                                                           name="correoP" 
                                                           class="form-control input-sm" 
                                                           maxlength="38"
                                                           minlength="7"
                                                           required
                                                           placeholder="Correo electrónico"
                                                           autocomplete="off"
                                                           oninput="validarInputs(correoP)">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="small">Número de Control</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                                    <input type="text" 
                                                           class="form-control input-sm" 
                                                           id="noControl" 
                                                           name="noControl" 
                                                           placeholder="Número de Control" 
                                                           maxlength="11"
                                                           minlength="3"
                                                           autocomplete="off"
                                                           required
                                                           oninput="validarInputs(noControl)">
                                                </div>
                                            </div>                                  
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12" align="right">
                                            <button type="button" class="btn btn-default" onclick="redirectProyectos()">
                                                <i class="fa fa-minus-circle"></i> Regresar
                                            </button>
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fas fa-save" aria-hidden="true"></i>  Guardar
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/templates/footer.jsp"%>
        <script type="text/javascript" src ="/CDN-ITT/js/jquery.estandarITT.js"></script>
        <script type="text/javascript" src ="/CDN-ITT/js/jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src ="/CDN-ITT/js/base.estandarITT.js"></script>
        <script type="text/javascript" src ="/CDN-ITT/js/spinner.estandarITT.js"></script>
        <script type="text/javascript" src ="/CDN-ITT/js/jquery.dataTables.estandarITT.js"></script>
        <script type="text/javascript" src ="/CDN-ITT/js/tablas.estandarITT.js"></script>
        <script type="text/javascript" src ="/CDN-ITT/js/catalogos-modal.estandarITT.js"></script>
        <script type="text/javascript" src ="/CDN-ITT/js/catalogos-tablas.estandarITT.js"></script>
        <script type="text/javascript" src ="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js"></script>
        <script type="text/javascript" src ="/CDN-ITT/js/bootstrap-datepicker.es.estandarITT.js"></script>
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/lib/bootbox.min.js"></script>
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/lib/bootbox.estandarITT.js"></script>   
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/lib/bootboxActions.js"></script>
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/lib/bootstrap-toggle.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.13.3/js/standalone/selectize.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jsgenerados/dataTableProyectos.js"></script>
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/constantes.js"></script>  
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/funciones.js"></script> 
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/validaInputs.js"></script> 
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/registro_colaboradores.js"></script> 
    </body>
</html>
