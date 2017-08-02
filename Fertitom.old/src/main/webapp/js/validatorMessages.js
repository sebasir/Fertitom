$.extend($.validator.messages, {
    required: 'Este campo es obligatorio.',
    number: 'Ingresa un numero valido. (Entre 1 y 365)',
    email: 'Ingresa un correo valido.',
    max: $.validator.format("Por favor ingresa un valor menor a {0}."),
    min: $.validator.format("Por favor ingresa un valor mayor a {0}.")
});

var $valid = $("#simulForm").validate({
    errorPlacement: function errorPlacement(error, element) {
        element.after(error);
    },
    rules: {
        rango: {
            validRange: true
        }
    }
});

$.validator.addMethod('validRange', function (value, element) {
    var min = $(element).parent().parent().find('.paramMin').html();
    var max = $(element).parent().parent().find('.paramMax').html();
    var val = $(element).val();
    return parseFloat(min) < parseFloat(val) && parseFloat(max) > parseFloat(val);
}, "No esta en el rango");