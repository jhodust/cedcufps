var token = $("meta[name='_csrf']").attr("content");


var idEducacionContinua=eduContinua.id;
var idJornada;
var horaInicioPick;
var horaFinPick;
$(document).ready(function ()
{
	document.getElementById("horaFin").disabled = true;
	horaInicioPick=$("#horaInicio").flatpickr({
		enableTime: true,
	    dateFormat: "d/m/Y H:i",
	    onChange: function(selectedDates, dateStr, instance) {
	    	document.getElementById("horaFin").disabled = false;
           horaFinPick=$("#horaFin").flatpickr({
       		enableTime: true,
       	    dateFormat: "d/m/Y H:i",
       	    minDate: selectedDates[0].toLocaleString(),
       	    minTime: selectedDates[0].toTimeString(),
       	    maxDate: fechaFEvento,
       	    defaultDate: selectedDates[0].toLocaleString(),
       	});
        },
        minDate:fechaIEvento,
        maxDate:fechaFEvento,
        defaultDate: fechaIEvento,
	    
	});
	
	
			/*$.getJSON('http://localhost:8080/programas-academicos/listar', function(json) {
				console.log(json)
			});*/
			
	$('#modalRegistroJornada').on('show.bs.modal', function (event) {
		$('#horaInicio').val("");
		$('#horaFin').val("");
		//horaInicioPick.clear();
		idJornada=null;
		document.getElementById("horaFin").disabled = true;
	});
	
	
});


function guardarJornada(){
	var horaInicio = $('#horaInicio').val();
	var horaFin = $('#horaFin').val();
	limpiarErrores();
	 var jornadaJSON={};
	  jornadaJSON.id=idJornada;
	  jornadaJSON.horaInicio=horaInicio;
	  jornadaJSON.horaFin=horaFin;
	  jornadaJSON.educacionContinua={};
	  jornadaJSON.educacionContinua.id=idEducacionContinua;
	  ajaxGuardarJornada(jornadaJSON);
	
}

function ajaxGuardarJornada(jsonJornada){
	showSpinnerModal("btnSaveJornada","btnSpinnerJornada");
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(jsonJornada),//variable idEducacionContinua la recibo de un script al final del index de jornadas
		url: "/educacion-continua/jornadas/save",
		cache: false,
		success: function(result) {
			toastr.success('Se ha guardado la información', 'Excelente!')
			//window.setTimeout(function(){location.reload()},1000);
			idJornada=null;
			$('#modalRegistroJornada').modal('hide');
			console.log("va a urlReload");
			reloadListJornadas();


	        console.log("despues de reload");
	        hideSpinnerModal("btnSaveJornada","btnSpinnerJornada");
		},
		error: function(err) {
			hideSpinnerModal("btnSaveJornada","btnSpinnerJornada")
			toastr.error('No se pudo procesar la solicitud...', 'Error!');
			console.log(err);
			err.responseJSON.forEach(function(error){
				if(error.field=="horaInicio"){
					var inputHoraInicio=document.getElementById('horaInicio');
					var errorHoraInicio=document.getElementById('errorHoraInicio');
					errorHoraInicio.innerText=error.defaultMessage;
					inputHoraInicio.classList.add("is-invalid");
				}
				if(error.field=="horaFin"){
					var inputHoraFin=document.getElementById('horaFin');
					var errorHoraFin=document.getElementById('errorHoraFin');
					errorHoraFin.innerText=error.defaultMessage;
					inputHoraFin.classList.add("is-invalid");
				}
			  });
		}
	});
}
function editarJornada(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/educacion-continua/jornadas/search/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			console.log(result);
			$('#modalRegistroJornada').modal();
			$('#horaInicio').val(result.fechaJornadaString + " " + result.horaInicioString);
			$('#horaFin').val(result.fechaJornadaString + " " + result.horaFinString);
			idJornada=result.id;
			document.getElementById("horaFin").disabled = true;
		},
		error: function(err) {
			
		}
	});
		
}

function eliminarJornada(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		data: {id:elemento.dataset.id},
		url: "/educacion-continua/jornadas/delete",
		cache: false,
		success: function(result) {
			 console.log(result);
			 toastr.success(result, 'Excelente!');
			 reloadListJornadas();
			 //window.setTimeout(function(){location.reload()},1000);
		},
		error: function(err) {
			toastr.error(err.responseJSON.message, 'Error!');
		}
	});
		
}

function limpiarErrores(){
	var inputHoraInicio=document.getElementById('horaInicio');
	var errorHoraInicio=document.getElementById('errorHoraInicio');
	errorHoraInicio.innerText="";
	inputHoraInicio.classList.remove("is-invalid");
	var inputHoraFin=document.getElementById('horaFin');
	var errorHoraFin=document.getElementById('errorHoraFin');
	errorHoraFin.innerText="";
	inputHoraFin.classList.remove("is-invalid");
}

function reloadListJornadas(){
	alert(eduContinua.idAcceso);
	var urlReload = '/educacion-continua/jornadas/'+idEducacionContinua;
	$('#div_list_jornadas').load(urlReload);
	var urlReloadDetalles = '/educacion-continua/jornadas/detalles/'+idEducacionContinua;
	$('#div_list_detalles_jornadas').load(urlReloadDetalles);
	
	var urlListado = '/educacion-continua/detalles/asistentes/'+eduContinua.idAcceso;
	$('#div_table_asistentes').load(urlListado);
	
	
}