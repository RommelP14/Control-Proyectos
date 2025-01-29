function validarDuenio(input) {
    var inputId = input.attr('id');
    var inputValue = input.val().trim();
    var mensaje = "";
    var isValid = true;

    switch (inputId) {
        case "nombreE":
            var nombreExp = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\s]+$/;
            if (!inputValue.match(nombreExp) || inputValue.length > 25 || inputValue.length < 3) {
                isValid = false;
            }
            break;
        case "nombreEmpresa":
        case "nombreCompleto":
        case "primerApellidoE":
        case "segundoApellidoE":
            var apellidoExp = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\s]+$/;
            if (!inputValue.match(apellidoExp) || inputValue.length > 100 || inputValue.length < 3) {
                isValid = false;
            }
            break;
        case "telefono":
            var telefonoExp = /^(\+)?(\d{1,4})?[\s-]?(\(?\d{1,4}\)?[\s-]?)*\d{1,4}([\s-]?\d{1,4}){1,4}$/;
            if (!inputValue.match(telefonoExp) || inputValue.length > 30 || inputValue.length < 10) {
                isValid = false;
            }
            break;
        case "emailEmpresa":
        case "correoP":
            var correoExp = /^[\w.%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            if (!inputValue.match(correoExp) || inputValue.length > 50 || inputValue.length < 7) {
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
        default:
            return;
    }

    // Realiza el reemplazo de valores no válidos
    if (!valoresValidos.test(valor)) {
        inputElement.value = valor.replace(valoresRemplazo, '');
    }
}