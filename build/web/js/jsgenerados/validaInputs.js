function validarDuenio(input) {
    var inputId = input.attr('id');
    var inputValue = input.val().trim();
    var mensaje = "";
    var isValid = true;
    var minLength, maxLength;

    switch (inputId) {
        case "nombreE":
        case "primerApellidoE":
        case "segundoApellidoE":
        case "carrera":
            var nombreExp = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\s]+$/;
            if (!inputValue.match(nombreExp) || inputValue.length > 43 || inputValue.length < 3) {
                isValid = false;
            }
            break;
        case "nombreEmpresa":
        case "nombreCompleto":
            var apellidoExp = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\s]+$/;
            if (!inputValue.match(apellidoExp) || inputValue.length > 98 || inputValue.length < 3) {
                isValid = false;
            }
            break;
        case "telefono":
            var telefonoExp = /^(\+)?(\d{1,4})?[\s-]?(\(?\d{1,4}\)?[\s-]?)*\d{1,4}([\s-]?\d{1,4}){1,4}$/;
            if (!inputValue.match(telefonoExp) || inputValue.length > 28 || inputValue.length < 10) {
                isValid = false;
            }
            break;
        case "emailEmpresa":
        case "correoP":
            var correoExp = /^[\w.%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            if (!inputValue.match(correoExp) || inputValue.length > 48 || inputValue.length < 7) {
                isValid = false;
            }
            break;
        case "titulo":
            var nombreProyecto = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\s]+$/;
            if (!inputValue.match(nombreProyecto) || inputValue.length > 148 || inputValue.length < 7) {
                isValid = false;
            }
            break;
        case "planteamiento":
        case "justificacion":
        case "alcances":
            var planteamientoExpr = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\s]+$/;
            if (!inputValue.match(planteamientoExpr) || inputValue.length > 998 || inputValue.length < 7) {
                isValid = false;
            }
            break;
        case "tipo":
            if (inputValue === "0") {
                isValid = false;
            }
            break;
        case "noControl":
            var controlExp = /^[a-zA-Z0-9]+([0-9]*)?$/i;
            minLength = 5;
            maxLength = 11;
            if (!inputValue.match(controlExp) || inputValue.length < minLength || inputValue.length > maxLength) {
                isValid = false;
            }
            break;
        case "porcentaje":
            var porcentajeExp = /^(0[1-9]|[1-9]\d|100)(\.\d{1,2})?$/;
            var minLength = 4; // "01.0" es el mínimo con 4 caracteres
            var maxLength = 6; // "100.00" es el máximo con 6 caracteres

            if (!inputValue.match(porcentajeExp) || parseFloat(inputValue) < 1 || parseFloat(inputValue) > 100.00 || inputValue.length < minLength || inputValue.length > maxLength) {
                isValid = false;
            }
            break;
        default:
            break;
    }

    if (isValid) {
        input.parent().removeClass('has-error');
        input.next('.error-message').remove();
    } else {
        input.parent().addClass('has-error');
        if (!input.next('.error-message').length) {
            input.after('<p class="error-message" style="color:red;">' + mensaje + '</p>');
        } else {
            input.next('.error-message').html(mensaje);
        }
    }
    return isValid;
}

let lastValue = ""; // Variable para prevenir validaciones innecesarias
function validarInputs(inputElement) {
    if (!inputElement || inputElement.readOnly || inputElement.offsetParent === null) // Verifica si es readonly o no visible
        return;

    var valor = inputElement.value;

    var valoresValidos;
    var valoresRemplazo;

    // Evita validaciones innecesarias si no ha cambiado el valor
    if (valor === lastValue)
        return;
    lastValue = valor; // Actualiza el último valor

    switch (inputElement.id) {
        case "nombreE":
        case "primerApellidoE":
        case "segundoApellidoE":
        case "nombreEmpresa":
        case "nombreCompleto":
        case "titulo":
        case "planteamiento":
        case "justificacion":
        case "alcances":
        case "carrera":
            valoresValidos = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\s]+$/;
            valoresRemplazo = /[^\sa-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+/g;
            validarDuenio($(inputElement));
            break;
        case "telefono":
            valoresValidos = /^(\+)?(\d{1,4})?[\s-]?(\(?\d{1,4}\)?[\s-]?)*\d{1,4}([\s-]?\d{1,4}){1,4}$/;
            valoresRemplazo = /[^0-9()+-]/g;
            validarDuenio($(inputElement));
            break;
        case "correoP":
        case "emailEmpresa":
            valoresValidos = /^[\w.%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            valoresRemplazo = /[^a-zA-Z0-9.@_-]/g;
            validarDuenio($(inputElement));
            break;
        case "tipo":
            validarDuenio($(inputElement));
            break;
        case "noControl":
            valoresValidos = /^[a-zA-Z0-9]+([0-9]*)?$/i;
            valoresRemplazo = /[^a-zA-Z0-9]/ig;
            validarDuenio($(inputElement));
            break;
        case "porcentaje":
            valoresValidos = /^(0[1-9]|[1-9]\d|100)(\.\d{1,2})?$/;
            valoresRemplazo = /[^0-9.]/g;

            validarDuenio($(inputElement));
            break;
        default:
            return;
    }

    // Realiza el reemplazo de valores no válidos
    if (!valoresValidos.test(valor)) {
        inputElement.value = valor.replace(valoresRemplazo, '');
    }
}