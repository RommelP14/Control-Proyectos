
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edición de Formatos</title>
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
        <div class="container">
            <%@include file="/views/templates/navbarJefeDepto.jsp" %>
            <div id="containerPrincipal">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Detalle
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">
                                    <div class="form-inline">
                                        <b>Edición de formatos</b>
                                    </div>
                                </div>
                            </div>

                            <div class="panel-body">
                                <br>
                                <div class="tab-content">       
                                    <div id="Alumnos" class="tab-pane active">
                                        <div class="thumbnail">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <form id="myForm" action="${pageContext.request.contextPath}/Subir_imagenes_Srv" method="post" enctype="multipart/form-data">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                    <label class="small">fondo de formatos</label>
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon"><i class="fa fa-file-o"></i></span>
                                                                        <input type="file" name="encabezado" class="form-control"  required autocomplete="off"  style="width: 400px;">
                                                                    </div>

                                                                </div>
                                                                <div>
                                                                    <img id="preview" src="#" alt="" style="max-width: 500px; max-height: 500px;">
                                                                </div>
                                                            </div>
                                                        </div>


                                                        <div class="row">
                                                            <div class="col-md-12" align="right">
                                                                <button id="btn-guardar" type="button" title="Cambiar imagenes" class="btn btn-primary" value="Subir" onclick="confirmarGuardar()"><i class="fa fa-floppy-o"></i> Guardar</button>
                                                            </div>   
                                                        </div>  
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
                <center>
                    <a href="#" class="text-center" onclick="toggleLanguageDiv(); return false;">Cambiar a español</a>
                </center>
            </div>

            <%@include file="/views/templates/footer.jsp" %>
            <script>
                function confirmarGuardar() {
                    bootbox.confirm({
                        title: "Confirmación",
                        message: "¿Estás seguro de que deseas guardar?",
                        buttons: {
                            confirm: {
                                label: 'Sí',
                                className: 'btn-primary'
                            },
                            cancel: {
                                label: 'No',
                                className: 'btn-secondary'
                            }
                        },
                        callback: function (result) {
                            if (result) {
                                enviarFormulario();
                            }
                        }
                    });
                }

                function enviarFormulario() {
                    // Puedes agregar cualquier otra lógica que necesites antes o después del envío
                    document.getElementById("myForm").submit();
                }

            </script>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script>
                function previewImage(input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            $('#preview').attr('src', e.target.result);
                        }
                        reader.readAsDataURL(input.files[0]);
                    }
                }

                $(document).ready(function () {
                    $('input[name="encabezado"]').change(function () {
                        previewImage(this);
                    });
                });
            </script>

            <script type="text/javascript" src ="/CDN-ITT/js/jquery.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/base.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/jquery-ui.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/spinner.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/jquery.dataTables.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/tablas.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/catalogos-modal.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/catalogos-tablas.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/bootstrap-datepicker.estandarITT.js"></script>
            <script type="text/javascript" src ="/CDN-ITT/js/bootstrap-datepicker.es.estandarITT.js"></script>
            <script type="text/javascript" src ="../../js/lib/bootbox.min.js"></script>
            <script type="text/javascript" src ="../../js/lib/bootbox.estandarITT.js"></script>        
            <script type="text/javascript" src ="../../js/lib/bootboxActions.js"></script>
            <script type="text/javascript" src ="../../js/lib/bootstrap-toggle.js"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css">
            <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        </div>
    </body>
</html>