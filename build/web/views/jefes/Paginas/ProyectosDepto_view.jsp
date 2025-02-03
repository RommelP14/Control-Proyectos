<%-- 
    Document   : tablaMisProyectos
    Created on : 28 ene 2025, 13:18:33
    Author     : romme
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Proyectos Departamento</title>
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
                                <b>Proyectos del Departamento</b>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-body" style="overflow-x: auto;">
                        <%@include file="/views/jefes/Paginas/tablaMisProyectos.jsp" %>
                        <%@include file="/views/jefes/Paginas/botonesVerProyectos.jsp" %>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="ver_datos" tabindex="-1" role="dialog" aria-labelledby="ittCatalogoLabel" data-backdrop="false">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="ittCatalogoTitle">
                            <i class="fa fa-info-circle fa-lg iconoInfo"></i>
                            Ver datos
                        </h4>
                    </div>
                    <div class="modal-body">
                        <div id="ittCatalogoContainer" class="row">
                            <div class="col-md-12">
                                <div id="proyectoFrame">
                                </div>
                            </div>
                        </div>

                        <div id="ittCatalogoError" class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-10 text-justify catalogo-error">


                            </div>
                            <div class="col-md-1"></div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modal_editar_calificacion_resi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Asignar calificación de Residencia</h4>
                    </div>
                    <form id="formulario_asigna_calificacion_resi">
                        <div class="modal-body">
                            <p>Contenido del modal</p>
                        </div> 
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class= "fa fa-minus-circle"></i> Cancelar</button>
                            <button type="submit" class="btn btn-primary" ><i class= "fa fa-floppy-o"></i> Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modal_datos_proyecto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Datos del proyecto</h4>
                    </div>
                    <div class="modal-body">
                        <p>Contenido del modal</p>
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
        <script type="text/javascript" src ="${pageContext.request.contextPath}/js/jsgenerados/registro_calificacion_residencia.js"></script> 
    </body>
</html>

