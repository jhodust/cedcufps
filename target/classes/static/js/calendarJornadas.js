var options = {
	"closeButton" : false,
	"debug" : false,
	"newestOnTop" : false,
	"progressBar" : false,
	"positionClass" : "toast-top-right",
	"preventDuplicates" : false,
	"onclick" : null,
	"showDuration" : "300",
	"hideDuration" : "1000",
	"timeOut" : 0,
	"extendedTimeOut" : 0,
	"showEasing" : "swing",
	"hideEasing" : "linear",
	"showMethod" : "fadeIn",
	"hideMethod" : "fadeOut",
	"tapToDismiss" : false
}

var eventoActualizar;
$(document).ready(function ()
{
	var calendario = document.getElementById('calendar');

	var calendar = new FullCalendar.Calendar(
			calendario,
			{
				plugins : [ 'dayGrid', 'timeGrid',
						'interaction' ],
				defaultView : 'timeGridWeek',
				selectable : true,
				editable : true,
				select : function(info) { // seleccionar rango
											// horas y agregar
					var dateInicio = new Date(info.start);
					var dateFin = new Date(info.end);
					$('#modalJornada').modal();
					$('#horaInicioJornada').val(
							formatDate(dateInicio));
					$('#horaFinJornada').val(
							formatDate(dateFin));

				},
				eventResize : function(info) {// cambiar hora
												// terminación
					console.log(info);
					var buttons = '<button type="button" class="btn btn-sm btn-info btn-icon-only" onclick="actualizarJornada()"><span class="btn-inner--icon"><i class="fas fa-check"></i></span></button><button type="button" class="btn btn-sm btn-info btn-icon-only" onclick="cancelarActualizarJornada()"><span class="btn-inner--icon"><i class="fas fa-times"></i></span></button>';
					toastr
							.info(
									buttons,
									'Desea actualizar los tiempos de la jornada?',
									options);
					eventoActualizar = info;
				},
				eventDrop : function(info) {// mover jornada
					eventoActualizar = info;
					var buttons = '<button type="button" class="btn btn-sm btn-info btn-icon-only" onclick="actualizarJornada()"><span class="btn-inner--icon"><i class="fas fa-check"></i></span></button><button type="button" class="btn btn-sm btn-info btn-icon-only" onclick="cancelarActualizarJornada()"><span class="btn-inner--icon"><i class="fas fa-times"></i></span></button>';
					toastr
							.info(
									buttons,
									'Desea actualizar los tiempos de la jornada?',
									options);
				},
				events : {// listar jornadas registradas
					url : '/educacion-continua/'
							+ idEducacionContinua
							+ '/search/jornadas',
					method : 'GET',
					color : 'yellow', // a non-ajax option
					textColor : 'black' // a non-ajax option
				}

			});

	calendar.render();
});


function actualizarJornada() {
	toastr.remove();
	var jornadaJSON = {};
	jornadaJSON.id = eventoActualizar.event.id;
	jornadaJSON.start = formatDate(eventoActualizar.event.start);
	jornadaJSON.end = formatDate(eventoActualizar.event.end);
	jornadaJSON.educacionContinua = {};
	jornadaJSON.educacionContinua.id = idEducacionContinua;
	ajaxGuardarJornada(jornadaJSON);
}
function cancelarActualizarJornada() {
	console.log(eventoActualizar);
	eventoActualizar.revert();
	toastr.remove();
}

function formatDate(date) {
	var minutos = 0;
	var horas = 0;
	var mes = 0;
	if ((date.getMonth() + 1) < 10) {
		mes = mes + '' + (date.getMonth() + 1);
	} else {
		mes = (date.getMonth() + 1);
	}
	if (date.getMinutes() < 10) {
		minutos = minutos + '' + date.getMinutes();
	} else {
		minutos = date.getMinutes()
	}
	if (date.getHours() < 10) {
		horas = horas + '' + date.getHours();
	} else {
		horas = date.getHours();
	}
	return date.getFullYear() + '-' + mes + '-' + date.getDate() + ' ' + horas
			+ ':' + minutos;
}