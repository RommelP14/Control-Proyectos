$(document).ready(function () {
    $('#pageLoader').hide();
    var noFolio;
    $('#denegar_proyecto').hide();
    $('#aceptar_proyecto').hide();
    $('#comentarios').hide();
    $('#add_team').hide();
      

    $("#guardarProposito").show();


    $('#justificacion').on('input', function () {
        var longitud = $(this).val().length;
        if (longitud > 1000) {
            $(this).val($(this).val().substring(0, 1000));
        }
    });
    $('#alcances').on('input', function () {
        var longitud = $(this).val().length;
        if (longitud > 1000) {
            $(this).val($(this).val().substring(0, 1000));
        }
    });
    $('#resumen').on('input', function () {
        var longitud = $(this).val().length;
        if (longitud > 1000) {
            $(this).val($(this).val().substring(0, 1000));
        }
    });


    //Retomar
    var lista = []; // Lista para almacenar los objetos

    $('#agregarTeam').click(function () {
        var noControl = $('#noControlR').val();
        var nombre = $('#nombre').val(); // Obtener el valor del input nombre
        var apellido__primer = $('#apellido_primer').val(); // Obtener el valor del input apellido
        var apellido__segundo = $('#apellido_segundo').val();
        var folio = $('#noFolioRetomar').val();
        $.ajax({
            type: 'POST',
            url: 'Team_SRV',
            data: {folio: folio, noControl: noControl, nombre: nombre, primerApellido: apellido__primer, segundoApellido: apellido__segundo},
            dataType: 'html',
            success: function (respuesta) {
            }
        });
        $('#noControlR').val('');
        $('#nombre').val(''); // Limpiar el input nombre
        $('#apellido_primer').val(''); // Limpiar el input apellido
        $('#apellido_segundo').val('');
    });



    $("#guardarProposito").on('click', function (e) {
        var proposito = $('#purpose').val();
        $.ajax({
            type: 'Get',
            url: 'Team_SRV',
            data: {proposito: proposito},
            success: function (respuesta) {
                $('#add_team').show();
                $("#guardarProposito").hide();
                $('#purpose').prop('readonly', true);
            }
        });

    });



    $(document).on('click', '#proyectos tr td', function () {
        var dat = $("#proyectos").DataTable().row(this).data();
        noFolio = dat[1];
        console.log(noFolio);
        $.ajax({
            type: 'Get',
            url: 'ProectDetails_SRV',
            data: {noFolio: noFolio},
            dataType: 'html',
            success: function (respuesta) {
                $('#cont').html("");
                $('#cont').html(respuesta);
            }
        });
    });
    $(document).on('click', '#proyectosD tr td', function () {
        var dat = $("#proyectosD").DataTable().row(this).data();
        noFolio = dat[1];
        console.log(noFolio);
        $.ajax({
            type: 'Post',
            url: 'ProectDetails_SRV',
            data: {noFolio: noFolio},
            dataType: 'html',
            success: function (respuesta) {
                $('#cont').html("");
                $('#cont').html(respuesta);
            }
        });
    });


//registro proyecto    
    $('#guardarP').click(function () {
        let registroproyecto = $("#registroproyecto").serializeArray();
        var camposValidos = validarCamposVacios();

        console.log(camposValidos);
        if (camposValidos) {
            $.ajax({
                type: 'post',
                url: 'Primitivas_SRV',
                data: registroproyecto,
                contetnType: "html; charset=utf-8",
                dataType: 'json',
                success: function (respuesta) {
                    $.ajax({
                        type: 'get',
                        url: 'Primitivas_SRV',
                        data: respuesta,
                        contetnType: "html; charset=utf-8",
                        success: function (vista) {
                            $("#cont").html("");
                            $('#cont').html(vista);
                            $('#guardar_dueño').click(function () {
                                $('#pageLoader').show();
                                var folio = getFolio();
                                var aprobacion;
                                if (respuesta.responseObject[0].folio == 'A') {
                                    aprobacion = 'Para aprobación'
                                } else if (respuesta.responseObject[0].folio == 'R') {
                                    aprobacion = 'Para revisión'
                                } else if (respuesta.responseObject[0].folio == 'D') {
                                    aprobacion = 'Existe un proyecto igual de acuerdo al porcentaje'
                                }
                                var name = $('#nombre').val() + ' ' + $('#primer_apellido').val() + ' ' + $('#segundo_apellido').val()
                                var titulo = respuesta.responseObject[0].titulo.toUpperCase();
                                var numeros = respuesta.responseObject[0].numeros
                                var similitudes = numeros.join(',');
                                $.ajax({
                                    type: 'get',
                                    url: 'Owner_SRV',
                                    data: {duenio: name, primjust: respuesta.responseObject[0].primjust, justificacion: respuesta.responseObject[0].justificacion
                                        , primres: respuesta.responseObject[0].primres, resumen: respuesta.responseObject[0].resumen,
                                        primalc: respuesta.responseObject[0].primalc, alcances: respuesta.responseObject[0].alcances,
                                        titulo: titulo, primtit: respuesta.responseObject[0].primtit,
                                        tipo: respuesta.responseObject[0].tipo, folio: folio, estado: aprobacion, numeros: similitudes},
                                    success: function (response) {
                                        $('#pageLoader').hide();
                                        var tit;
                                        var men;
                                        tit = "Registro exitoso";
                                        men = "El proyecto se registro correctamente";
                                        mensaje(tit, men);

                                    }
                                });
                            });
                        }
                    });
                }
            })
} else {
            var tit;
            var men;
            tit = "Error";
            men = "Complete todos los campos";
            mensaje(tit, men);
        }
    });


//Modal ver datos
    $('#modal_ver_datos').on("click", function (e) {
        var noFolio = $('#noFolioDatos').val();
        document.getElementById("proyectoFrame").innerHTML = "";
        $.ajax({
            type: 'Get',
            url: 'PDF_SRV',
            data: {noFolioR: noFolio},
            success: function (respuesta) {
                var iframe = document.createElement('iframe');
                var html = respuesta;
                iframe.srcdoc = html;
                iframe.width = '100%';
                iframe.height = '400px';
                document.getElementById("proyectoFrame").appendChild(iframe);
            }

        });
    });


    //Petición actualizar progreso

    $('#actualizar_guardar').on("click", function (e) {
        var porcentaje = $('#porcentaje').val();
        var url = $('#url').val();
        var noFolio = $('#noFolioA').val();
        var noEquipo = $('#noEquipo1').val();
        if (porcentaje <= 100 && porcentaje >= 1) {
            $.ajax({
                type: 'Get',
                url: 'UpdateProyect_SRV',
                data: {porcentaje: porcentaje, noFolio: noFolio, url: url, noEquipo: noEquipo},
                success: function (respuesta) {
                    var tit;
                    var men;
                    tit = "Registro exitoso";
                    men = "El porcentaje de progreso se registro correctamente";
                    mensaje(tit, men);
                }

            });
        } else {
            var tit;
            var men;
            tit = "Error";
            men = "Ingrese un porcentaje vàlido";
            mensaje(tit, men);
        }

    });
    //Desvincular equipo
    $('#desvincular_equipo').on("click", function (e) {
        var noEquipo = this.getAttribute('data-nocontrol');
        var folio = $('#noFolioP').val();
        ;
        console.log(folio)
        $.ajax({
            type: 'Post',
            url: 'UpdateProyect_SRV',
            data: {noEquipo: noEquipo, folio: folio},
            success: function (respuesta) {
                var tit;
                var men;
                tit = "Registro exitoso";
                men = "El equipo se desvinculó";
                mensaje(tit, men);
            }

        });
    });
//Ceder Proyecto

    $('#btnSearchCorreo').on("click", function (e) {
        console.log("search")
        var correo = $('#correo').val();
        console.log(noFolio)
        if (correo == "") {
            var tit;
            var men;
            tit = "Error";
            men = "Seleccione un correo";
            mensaje(tit, men);
        } else {
            $.ajax({
                type: 'Post',
                url: 'Ceder_SRV',
                data: {correo: correo},
                success: function (respuesta) {
                    $('#dataTableCeder tbody').empty();
                    console.log(respuesta.responseObject[0])
                    var arr = respuesta.responseObject;
                    // Iterar sobre los datos recibidos y agregar filas a la tabla
                    $.each(arr, function (index, data) {
                        console.log(data)
                        var row = '<tr>' +
                                '<td>' + data.nombre + '</td>' +
                                '<td>' + data.primer_apellido + '</td>' +
                                '<td>' + data.segundo__apellido + '</td>' +
                                '<td>' + data.correo + '</td>' +
                                '</tr>';
                        $('#dataTableCeder tbody').append(row);
                    });

                }
            });
        }

    });


    $('#aceptar_ceder').on("click", function (e) {
        var correo = $('#correo').val();
        var noFolio = $('#noFolioC').val();
        console.log(correo + "**")
        if (correo == "") {
            var tit;
            var men;
            tit = "Error";
            men = "Ingrese un correo";
            mensaje(tit, men);
        } else {
            $.ajax({
                type: 'Get',
                url: 'Ceder_SRV',
                data: {correo: correo, noFolio: noFolio},
                success: function (respuesta) {
                    var tit;
                    var men;
                    tit = "Registro exitoso";
                    men = "Se cedió el proyecto";
                    mensaje(tit, men);
                }});
        }
    });

    $('#aceptar_retomar').on("click", function (e) {
        location.reload();
    });


    //Guardar porcentaje

    $('#guardar_porcentaje').on("click", function (e) {
        var porcentaje_max = $('#porcentaje_max').val();
        var porcentaje_min = $('#porcentaje_min').val();
        console.log(porcentaje_min);
        if (porcentaje_min < porcentaje_max) {
            $.ajax({
                type: 'Post',
                url: 'Porcentaje_SRV',
                data: {porcentaje_max: porcentaje_max, porcentaje_min: porcentaje_min},
                success: function (respuesta) {
                    var tit;
                    var men;
                    tit = "Registro exitoso";
                    men = "El porcentaje de revisión se registro correctamente";
                    mensaje(tit, men);
                }

            });
        } else {
            var tit;
            var men;
            tit = "Error";
            men = "El porcentaje mínimo no puede ser mayor al máximo";
            mensaje(tit, men);
        }
    });
}

);
function mensaje(titulo, mensaje) {
    bootbox.alert({
        title: titulo,
        message: mensaje,
        callback: function () {
            location.reload();
        },
        onEscape: function () {
            location.reload();
        }
    });
}
function getFolio() {
    var now = new Date();
    var year = now.getFullYear().toString().slice(-2);
    var month = ('0' + (now.getMonth() + 1)).slice(-2); // Adding 1 because months are zero-based
    var day = ('0' + now.getDate()).slice(-2);
    var hour = ('0' + now.getHours()).slice(-2);
    var minutes = ('0' + now.getMinutes()).slice(-2);
    var date = year + month + day + hour + minutes;
    return date;
}

function validarCamposVacios() {
    var camposVacios = $('input, textarea').filter(function () {
        return $(this).val().trim() === '';
    });

    if (camposVacios.length > 0) {

        return false;
    }

    return true;
} 