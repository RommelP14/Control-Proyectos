$(function () {
    $("#spin_carga").hide();
    $("#pageLoader").hide();
});
$(document).ready(function () {
    $('#inpUsuario').keypress(function (event) {
        if (event.which === 13) {
            validarDatosSesion();
        }
    });
    $('#inpPass').keypress(function (event) {
        if (event.which === 13) {
            validarDatosSesion();
        }
    });

    $("#btnInicioSesionIngresar").click(function (event) {
        validarDatosSesion();
    });
});

function validarDatosSesion() {
    $("#pageLoader").show();
    let inpUsuario = $("#inpUsuario").val();
    let inpPass = $("#inpPass").val();

    if (inpUsuario.toString().trim() === "") {
        $("#pageLoader").hide();
        mensajeError("Error", "El campo requerido de Usuario es obligatorio.");
        return;
    }
    if (inpPass.toString().trim() === "") {
        $("#pageLoader").hide();
        mensajeError("Error", "El campo requerido de la Contraseña es obligatorio.");
        return;
    }

    $.ajax({
        type: "POST",
        url: "app/autenticacion/IniciarSesion.do",
        data: $("#form-registro").serialize(),
        success: function (response) {
            $("#pageLoader").hide();
            if (response.status === 0) {
                console.log("Se hizo submbit");
                $("#form-registro").submit();
            } else if (response.status === -403) {
                mensajeInformacion("Error", "Ocurrió un error: <br/> " +
                    "<b>Estatus: </b>" + '403' + "<br/>" +
                    "<b>Error: </b>" + 'Usuario NO autorizado' + "<br/>" +
                    "Si hay algún problema, o el problema persiste, consulte a su administrador. <br/>");
            } else {
                mensajeInformacion("Error en el inicio de sesión", response.mensaje);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#pageLoader").hide();
            mensajeInformacion("Aviso", "Ocurrió un error: <br/> " +
                "<b>Estatus:</b> " + textStatus + "<br/>" +
                "<b>Error: </b> " + errorThrown + "<br/>" +
                "Por favor, notifique a su administrador de este error. <br/>");
        }
    });
}