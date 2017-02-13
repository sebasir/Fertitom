var SIMUL_STEP = 2;
var form = $("#simulForm").show();

form.steps({
    headerTag: 'h3',
    bodyTag: 'section',
    transitionEffect: 'slideLeft',
    stepsOrientation: 'vertical',
    enableCancelButton: true,
    autoFocus: true,
    labels: {
        cancel: 'Cancelar',
        current: 'Paso Actual:',
        pagination: 'Paginación',
        finish: 'Finalizar',
        next: 'Siguiente',
        previous: 'Anterior',
        loading: 'Cargando ...'
    },
    onStepChanging: function (event, currentIndex, newIndex) {
        if (currentIndex > newIndex)
            return true;
        if (currentIndex < newIndex) {
            form.find(".body:eq(" + newIndex + ") label.error").remove();
            form.find(".body:eq(" + newIndex + ") .error").removeClass("error");
        }
        form.validate().settings.ignore = ":disabled,:hidden";
        return form.valid();
    },
    onStepChanged: function (event, currentIndex, priorIndex) {
        $(".actions a:eq(1)").text("Siguiente");
        if (currentIndex === SIMUL_STEP) {
            $(".actions a:eq(1)").text("Simular");
        }
        
        if (currentIndex === (SIMUL_STEP + 1)) {
            launchSimulation();
        } else if (currentIndex === SIMUL_STEP) {
            updateSummary();
        }
        
        if (priorIndex < currentIndex) {
            $('#progressbar').css('width', (priorIndex * 20 + 40) + '%');
        } else {
            $('#progressbar').css('width', (priorIndex * 20) + '%');
        }
    },
    onFinishing: function (event, currentIndex) {
        form.validate().settings.ignore = ":disabled";
        return form.valid();
    },
    onFinished: function (event, currentIndex) {
        form[0].submit();
    },
    onCanceled: function (event) {
    }
});

$('.fecha').datepicker(
    $.datepicker.regional['es']
);

$('.fecha').datepicker('option', {
    dateFormat: 'yy-mm-dd'
});

$(document).ready(function () {
    google.maps.event.addDomListener(window, 'load', initialize);
});

function initialize() {
    var mapCanvas = document.getElementById('map');
    var mapOptions = {
        center: {lat: 4.583333, lng: -74.066667},
        zoom: 5
    };
    var map = new google.maps.Map(mapCanvas, mapOptions);
}