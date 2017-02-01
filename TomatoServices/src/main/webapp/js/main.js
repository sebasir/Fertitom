var idMess = 0;
var thisUser = '';
var validUser = false;
var validPass = false;
var validName = false;
var gallery = document.location.href.indexOf('gallery') < 0 ? 'gallery/' : '';
var urls = {'autorServlet': gallery + 'autorServlet',
    'galleryServlet': gallery + 'galleryServlet',
    'imagenServlet': gallery + 'galleryServlet',
    'loginServlet': document.location.href.indexOf('gallery') > 0 ? '../' : '' + 'loginServlet'
};
var autorData = null;
var $yesIcon = $('<span class="success iconCheck glyphicon glyphicon-ok" aria-hidden="true"></span>');
var $notIcon = $('<span class="failed iconCheck glyphicon glyphicon-remove" aria-hidden="true"></span>');
var $waiting = $('<span class="iconCheck glyphicon glyphicon-refresh glyphicon-refresh-animate" aria-hidden="true"></span>');
var $chevron = $('<span class="glyphicon glyphicon-chevron-right"></span>');
var imagenAutorBase = gallery + 'imagenServlet?idAutor=';
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
        $(this).find('.summaryField').each(function () {
            $(this).val('');
        });
        
        if (priorIndex < currentIndex) {
            $('#progressbar').css('width', (priorIndex * 20 + 40) + '%');
        } else {
            $('#progressbar').css('width', (priorIndex * 20) + '%');
        }
        if (currentIndex === 3) {
            $('#locSummary').val($('#loc').val());
            $('#file1Summary').val($('#file1').val());
            $('#file2Summary').val($('#file2').val());
            $('#tipoSummary').val($('#cul').val());
            $('#transSummary').val($('#trgh').val());
            $('#plm2Summary').val($('#plm2').val());
            $('#dmleafSummary').val($('#dmleaf').val());
            $('#totleafSummary').val($('#totleaf').val());
            $('#dmstemSummary').val($('#dmstem').val());
            $('#dmfruitSummary').val($('#dmfruit').val());
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
        var $check = $('#autorCheck');
        $check.attr('checked', true);
        form[0].reset();
        form.steps('reset');
        form.find("label.error").remove();
        form.find(".error").removeClass("error");
        var $section = $check.parents('section');
        $section.find('.autorExist').each(function () {
            $(this).addClass('oculto');
            $(this).find('.input-block-level').removeClass('required').val('');
        });
        $('#autorExist').removeClass('oculto').find('select').addClass('required');
        restartDetails();
        autorData = null;
        hideNew($('#myModal'));
    }
});

$('.fecha').datepicker(
        $.datepicker.regional['es']
        );

$('.fecha').datepicker('option', {
    maxDate: '0',
    dateFormat: 'yy-mm-dd'
});

function del(mess, url, data, $location) {
    if (confirm(mess)) {
        $location.find('.wait').removeClass('oculto');
        $location.find('.delicon').addClass('oculto');
        $.ajax({
            data: data,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded;charset=ISO-8859-1",
            type: "GET",
            url: url,
            success: function (data) {
                $('#msgalert').removeClass('oculto');
                $('#msgalert').append(data.mess);
                if ($.trim(data.status) === 'ok') {
                    $('#msgalert').addClass('alert-success');
                    if ($location.data('obra'))
                        location.reload();
                    else
                        $location.remove();
                } else {
                    $('#msgalert').addClass('alert-danger');
                    $location.find('.wait').addClass('oculto');
                    $location.find('.delicon').removeClass('oculto');
                }
                var $alert = $('#msgalert').clone();
                $alert.insertBefore($location);
            },
            error: function (data) {
                console.log(data);
            }
        });
    }
}

function editObra($idPro) {
    var $inputGroup = $idPro.find('.input-group');
    var $priceTag = $idPro.find('.price');
    var $input = $idPro.find('.input-price');
    $input.val($inputGroup.data('price'));
    $priceTag.toggleClass('oculto');
    $inputGroup.toggleClass('oculto');
}

function showNew($modal) {
    $modal.modal({
        backdrop: 'static',
        keyboard: false
    });
}

function hideNew($modal) {
    $modal.modal('toggle');
}

function setUsername(username) {
    thisUser = username;
}

$('.register').change(function () {
    var validForm = true;
    var $field = $(this).attr('id');
    var $val = $(this).val();
    $('#register').prop('disabled', true);
    if ($field === 'username') {
        $('.userIconCheck').remove();
        var $userYesIcon = $yesIcon.addClass('userIconCheck');
        var $userNotIcon = $notIcon.addClass('userIconCheck');
        $waiting.addClass('userIconCheck');
        $waiting.insertAfter('#' + $field);
        $.ajax({
            data: {
                newUser: $(this).val()
            },
            type: "GET",
            url: urls.userServlet,
            success: function (data) {
                if ($.trim(data.status) === 'ok') {
                    $userYesIcon.insertAfter('#' + $field);
                    validUser = true;
                } else {
                    $userNotIcon.insertAfter('#' + $field);
                    validUser = false;
                }
                $waiting.remove();
            },
            error: function (data) {
                console.log(data);
            }
        });
    } else if ($field === 'pass') {
        $('.passIconCheck').remove();
        var $passYesIcon = $yesIcon.addClass('passIconCheck');
        var $passNotIcon = $notIcon.addClass('passIconCheck');
        if ($val === '') {
            $passNotIcon.insertAfter('#' + $field);
            validPass = false;
        } else {
            $passYesIcon.insertAfter('#' + $field);
            validPass = true;
        }
    } else if ($field === 'name') {
        $('.nameIconCheck').remove();
        var $nameYesIcon = $yesIcon.addClass('nameIconCheck');
        var $nameNotIcon = $notIcon.addClass('nameIconCheck');
        if ($val === '') {
            $nameNotIcon.insertAfter('#' + $field);
            validName = false;
        } else {
            $nameYesIcon.insertAfter('#' + $field);
            validName = true;
        }
    }
    if (validUser && validPass && validName)
        $('#register').prop('disabled', false);
});

$('.newpass').change(function () {
    var validForm = true;
    var $field = $(this).attr('id');
    var $val = $(this).val();

    $('#changepass').prop('disabled', true);
    if ($field === 'newpass') {
        $('.passIconCheck').remove();
        var $passYesIcon = $yesIcon.addClass('passIconCheck');
        var $passNotIcon = $notIcon.addClass('passIconCheck');
        if ($val === '') {
            $passNotIcon.insertAfter('#' + $field);
            validPass = false;
        } else {
            $passYesIcon.insertAfter('#' + $field);
            validPass = true;
        }
    } else if ($field === 'newpassconfirm') {
        $('.confIconCheck').remove();
        var $confYesIcon = $yesIcon.addClass('confIconCheck');
        var $confNotIcon = $notIcon.addClass('confIconCheck');
        if ($val === '') {
            $confNotIcon.insertAfter('#' + $field);
            validName = false;
        } else {
            if ($('#newpass').val() === $val) {
                $confYesIcon.insertAfter('#' + $field);
                validName = true;
            } else {
                $confNotIcon.insertAfter('#' + $field);
                validName = false;
            }
        }
    }
    if (validPass && validName)
        $('#changepass').prop('disabled', false);
});

$('#changepass').click(function () {
    var $user = $('#user').val();
    var $newpass = $('#newpass').val();
    var $confpass = $('#newpassconfirm').val();
    $('#msgalertpass').removeClass('alert-success');
    $('#msgalertpass').removeClass('alert-danger');
    $('#msgalertpass').removeClass('alert-warning');
    if ($confpass === $newpass) {
        $('#loadinguser').removeClass('oculto');
        $.ajax({
            data: {
                changeUser: $user,
                changePass: $newpass
            },
            dataType: 'json',
            type: "POST",
            url: urls.userServlet,
            contentType: "application/x-www-form-urlencoded;charset=ISO-8859-1",
            success: function (data) {
                $('#msgalertpass')
                        .removeClass('oculto');
                $('#msgalertpass').html(data.mess);
                if ($.trim(data.status) === 'ok') {
                    $('#msgalertpass').addClass(
                            'alert-success');
                    setTimeout(function () {
                        window.location.href = data.send;
                    }, 1500);
                } else {
                    $('#msgalertpass').addClass(
                            'alert-danger');
                    $('#loadingpass')
                            .addClass('oculto');
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    } else {
        $('#msgalertpass').removeClass('oculto');
        $('#msgalertpass').html('Las contraseÃ±as no son las mismas. No se puede continuar<br>Intenta nuevamente');
        $('#changepass').prop('disabled', true);
    }
});

$('#register').click(function () {
    var $user = $('#username').val();
    var $pass = $('#pass').val();
    var $name = $('#name').val();
    $('#loadinguser').removeClass('oculto');
    $.ajax({
        data: {
            username: $user,
            password: $pass,
            name: $name
        },
        dataType: 'json',
        type: "POST",
        url: urls.userServlet,
        contentType: "application/x-www-form-urlencoded;charset=ISO-8859-1",
        success: function (data) {
            $('#msgalertuser').removeClass('oculto');
            $('#msgalertuser').html(data.mess);
            if ($.trim(data.status) === 'ok') {
                $('#msgalertuser').addClass('alert-success');
                setTimeout(function () {
                    window.location.href = data.send;
                }, 1500);
            } else {
                $('#msgalertuser').addClass('alert-danger');
                $('#loadinguser').addClass('oculto');
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
});

$('.reset').click(function () {
    $('.iconCheck').remove();
    validUser = false;
    validPass = false;
    validName = false;
    if ($(this).hasClass('reload'))
        location.reload();
});

$('#men').keypress(function (e) {
    var key = e.which;
    if (key === 13) {
        $('#enviar').click();
        return false;
    }
});

$('#btnLogin').click(function () {
    var $user = $('#user').val();
    var $pass = $('#pwd').val();
    $('#loading').removeClass('oculto');
    $('#msgalert').removeClass('alert-success');
    $('#msgalert').removeClass('alert-warning');
    $('#msgalert').removeClass('alert-danger');
    $.ajax({
        data: {
            username: $user,
            password: $pass
        },
        dataType: 'json',
        type: "POST",
        url: urls.loginServlet,
        contentType: "application/x-www-form-urlencoded;charset=ISO-8859-1",
        success: function (data) {
            $('#msgalert').removeClass('oculto');
            $('#msgalert').html(data.mess);
            if ($.trim(data.status) === 'ok') {
                $('#msgalert').addClass('alert-success');
                setTimeout(function () {
                    window.location.href = data.send;
                }, 1500);
            } else if ($.trim(data.status) === 'full') {
                $('#msgalert').addClass('alert-warning');
                setTimeout(function () {
                    window.location.href = data.send;
                }, 4500);
            } else if ($.trim(data.status) === 'restart') {
                $('#msgalert').addClass('alert-warning');
                setTimeout(function () {
                    showNew($('#myPassModal'));
                }, 2500);
            } else {
                $('#msgalert').addClass('alert-danger');
                $('#loading').addClass('oculto');
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
});

$('.mostrar').click(function () {
    if (!($("#myModal").data('bs.modal') || {}).isShown)
        showNew($('#myModal'));
    if (!($("#myPassModal").data('bs.modal') || {}).isShown)
        showNew($('#myPassModal'));
});

$('.ocultar').click(function () {
    if (($("#myModal").data('bs.modal') || {}).isShown)
        hideNew($('#myModal'));
    if (($("#myPassModal").data('bs.modal') || {}).isShown)
        hideNew($('#myPassModal'));
});

$('.editar').click(function () {
    if ($(this).parent().parent().attr('aria-label') === 'gallery') {
        if ($(this).hasClass('cancel'))
            $(this).html('Editar');
        else
            $(this).html('Cancelar');
        $(this).append($chevron);
        $(this).toggleClass('cancel');
        editObra($(this).parent().parent());
    }
});

$('.eliminar').click(function () {
    var data;
    var URL;
    var mess = '';
    var $location = $(this).parent().parent();
    if ($location.attr('aria-label') === 'gallery') {
        data = {
            id_obra: $location.data('obra'),
            del: true
        };
        mess = 'Desea eliminar este contenido?';
        URL = urls.galleryServlet;
    } else if ($location.attr('aria-label') === 'user') {
        data = {
            idAutor: $location.attr('id'),
            del: true
        };
        mess = 'Desea eliminar este autor?';
        URL = urls.autorServlet;
    }
    del(mess, URL, data, $location);
});

$('.desconectar').click(function () {
    var $disUser = $(this).parent().parent().attr('id');
    var $location = $(this);
    $location.find('.wait').removeClass('oculto');
    $location.find('.desicon').addClass('oculto');
    $.ajax({
        data: {
            disUser: $disUser
        },
        url: urls.userServlet,
        type: "GET",
        success: function (data) {
            $('#msgalert').removeClass('oculto');
            $('#msgalert').html(data.mess);
            if ($.trim(data.status) === 'ok') {
                $('#msgalert').addClass('alert-success');
                setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else {
                $('#msgalert').addClass('alert-danger');
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
});

$('.reiniciar').click(function () {
    var $resUser = $(this).parent().parent().attr('id');
    var $location = $(this);
    $location.find('.wait').removeClass('oculto');
    $location.find('.desicon').addClass('oculto');
    $.ajax({
        data: {
            resUser: $resUser
        },
        url: urls.userServlet,
        type: "GET",
        success: function (data) {
            $('#msgalert').removeClass('oculto');
            $('#msgalert').html(data.mess);
            if ($.trim(data.status) === 'ok') {
                $('#msgalert').addClass('alert-success');
                setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else {
                $('#msgalert').addClass('alert-danger');
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
});

function changeAutor($sel) {
    if ($sel.val() !== '')
        $.ajax({
            data: {
                idAutor: $sel.val()
            },
            url: urls.autorServlet,
            type: "GET",
            dataType: 'json',
            success: function (data) {
                if (data.msg === 'error') {
                    restartDetails();
                    alert('Error consultando informacion del Autor');
                } else {
                    autorData = data.msg;
                    $('#autorDetails').removeClass('oculto');
                    $('#fotoAutorDetail').attr('src', imagenAutorBase + data.msg.id_autor);
                    $('#idenAutorDetail').val(data.msg.identificacion);
                    $('#edadAutorDetail').val(data.msg.edad_autor);
                    $('#suenioAutorDetail').val(data.msg.suenio_autor);
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    else
        restartDetails();
}

function autorExist($check) {
    var $section = $check.parents('section');
    $section.find('.autorExist').each(function () {
        $(this).toggleClass('oculto');
        $(this).find('.input-block-level').toggleClass('required').val('');
    });
    restartDetails();
    $section.find('.autorDetails').each(function () {
        $(this).addClass('oculto');
    });
}

function restartDetails() {
    $('#autorDetails').addClass('oculto').find('.autorDetails').each(function () {
        if ($(this).prop('tagName') === 'IMG')
            $(this).attr('src', imagenAutorBase);
        else
            $(this).val('');
    });
}

function changeValues($in) {
    if ($in.val() !== '') {
        $('.userIconCheck').remove();
        $waiting.addClass('userIconCheck');
        $waiting.insertAfter($in);
        if ($in.attr('name') === 'identificacion') {
            $.ajax({
                data: {
                    idenAutor: $in.val(),
                    checkIdent: true
                },
                type: "GET",
                url: urls.autorServlet,
                dataType: 'json',
                success: function (data) {
                    if (data.status !== 'ok') {
                        errors = {identificacion: "Esta identificacion ya existe, prueba otra diferente."};
                        $valid.showErrors(errors);
                    }
                    $waiting.remove();
                },
                error: function (data) {
                    console.log(data);
                }
            });
        } else if ($in.attr('name') === 'nom_obra') {
            $.ajax({
                data: {
                    nomObraArte: $in.val(),
                    checkObra: true
                },
                type: "GET",
                url: urls.galleryServlet,
                dataType: 'json',
                success: function (data) {
                    if (data.status !== 'ok') {
                        errors = {nom_obra: "Esta obra ya existe, prueba un nombre diferente."};
                        $valid.showErrors(errors);
                    }
                    $waiting.remove();
                },
                error: function (data) {
                    console.log(data);
                }
            });

        }
    }
}

$('.savePrice').click(function () {
    var idObra = $(this).data('price');
    var $precio = $(this).parents('.input-group').find('.input-price');
    if ($precio.val() === '' || parseInt($precio.val()) < 0) {
        alert('Ingresa un valor valido!');
        return;
    }
    $.ajax({
        url: urls.galleryServlet,
        data: {precio: $precio.val(), id_obra: idObra, edit: true},
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.status === 'ok') {
                alert(data.mess);
                location.reload();
            } else
                alert(data.mess);
        },
        error: function (data) {
            console.log(data);
        }
    });
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