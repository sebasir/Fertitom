var SIMUL_STEP = 2;
var form = $("#simulForm").show();
var map;

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
        if (currentIndex === SIMUL_STEP - 1) {
            // initialize();
            setMapCenter(4.583333, -74.066667, 'Colombia', 5);
            //alert("Pasamos por aquí");
        }
        else if (currentIndex === SIMUL_STEP) {
            $(".actions a:eq(1)").text("Simular");
        }
        else if (currentIndex === (SIMUL_STEP + 1)) {
            $('#resultOutput').empty();
            launchSimulation();
        }/* else if (currentIndex === SIMUL_STEP) {
            updateSummary();
        }*/

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
        restart();
    }
});

function handleMessage(message) {
    console.log(message);
    if(message.startsWith('simulationId')) {
        var simulationId = message.substring(13);
        var src;
        $('#resultPlant, #resultSoil').find('.res-img').each(function() {
            src = $(this).prop("src");
            src = src.split('simulationId').join(simulationId);
            $(this).prop("src", src);
        });
        $('#resultPlant, #resultSoil').find('.res-csv').each(function() {
            src = $(this).prop("href");
            src = src.split('simulationId').join(simulationId);
            $(this).prop("href", src);
        });
        $('#waitResultPlant').toggleClass('oculto');
        $('#waitResultSoil').toggleClass('oculto');
        $('#resultPlant').toggleClass('oculto');
        $('#resultSoil').toggleClass('oculto');
    }
}

function setFecha() {
    $('.fecha').datepicker($.datepicker.regional['es']);
    $('.fecha').each(function (index) {
        var datepicker_default_val = $(this).val();
        $(this).datepicker({numberOfMonths: 3, showButtonPanel: true});
        $(this).datepicker("option", "dateFormat", 'yy-mm-dd');
        $(this).datepicker("setDate", datepicker_default_val);
    });
    $('.fecha').datepicker('option', {
        dateFormat: 'yy-mm-dd'
    });
}

$(document).ready(function () {
    setFecha();
    google.maps.event.addDomListener(window, 'load', initialize);
});

function initialize() {
    var mapCanvas = document.getElementById('map');
    var mapOptions = {
        center: {lat: 4.583333, lng: -74.066667},
        zoom: 5
    };
    map = new google.maps.Map(mapCanvas, mapOptions);
}

function setMapCenter(paramLat, paramLon,paramName, zoomVal) {
        console.log(paramLat, paramLon);
    if (isNumeric(paramLat)&& isNumeric(paramLon))
    {
        var latitude=parseFloat(paramLat), longitude=parseFloat(paramLon);
        var theZoom=parseInt(zoomVal);
        var mapCanvas = document.getElementById('map');
        var mapOptions = {
            center: {lat: latitude, lng: longitude},
            zoom: theZoom
        };
        var marker = new google.maps.Marker({
            position: {lat: latitude, lng:longitude},
            title: paramName
        });
        map = new google.maps.Map(mapCanvas, mapOptions);
        marker.setMap(map);
        map.setCenter(latitude, longitude);
        //marker.setPosition(latitude, longitude);
    }
}

function isNumeric(n) 
{
  return !isNaN(parseFloat(n)) && isFinite(n);
}