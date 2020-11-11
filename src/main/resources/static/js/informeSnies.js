var token = $("meta[name='_csrf']").attr("content");
$(document).ready(function ()
		{

$("#fechaInicioReporte").flatpickr({
	dateFormat: "d/m/Y",
	onChange: function(selectedDates, dateStr, instance) {
	    	console.log(selectedDates);
	    	console.log(selectedDates[0].toDateString());
	    	console.log(dateStr);
	    	console.log(selectedDates[0].getDate());
	    	console.log("toString:" + selectedDates[0].toString());
	    	console.log("dia: " + selectedDates[0].getDay());
	    	console.log("mes: " + selectedDates[0].getMonth());
	    	console.log("UTCMONTH " + selectedDates[0].getUTCMonth());
	    	console.log("fullYear: " + selectedDates[0].getFullYear());
	    	console.log("años: " + selectedDates[0].getYear());
	    	console.log("horas: "+ selectedDates[0].getHours());
	    	console.log("toLocaleString: "+selectedDates[0].toLocaleString());
	    	console.log("toLocaleDateString: "+selectedDates[0].toLocaleDateString());
	    	
	    	
	    	console.log("minDate: "+selectedDates[0].toDateString());
           
	    	$("#fechaFinReporte").flatpickr({
       		dateFormat: "d/m/Y",
       	    minDate: selectedDates[0].toLocaleDateString(),
       	});
	}
});

		})
function generarReporteSnies(){
	var fechaInicio =$('#fechaInicioReporte').val();
	console.log(fechaInicio);
	var fechaFin =$('#fechaFinReporte').val();
	console.log(fechaFin);
	//var semestre = $('#select_semestre').val();
	if(fechaInicio == "" || fechaFin == ""){
		validateFechaInicioReporte();
		validateFechaFinReporte();
		toastr
		.error(
				'Debes ingresar todos los campos requeridos',
				'Error!');
				return;
	}
		$.ajax({
			headers: {"X-CSRF-TOKEN": token},
			type: "GET",
			contentType: "application/json; charset=utf-8",
			data: {'fechaInicio': fechaInicio, 'fechaFin':fechaFin},
			url: "/reportes-SNIES/generar",
			cache: false,
			success: function(result) {
				console.log(result);
				toastr.success('Se ha generado el reporte', 'Excelente!')
				window.setTimeout(function(){location.reload()},2000);
				
			},
			error: function(err) {
				toastr.error('No se pudo procesar la solicitud...', 'Error!');
				console.log(err);
				err.responseJSON.forEach(function(error){
					if(error.field=="anio"){
						var inputAnio=document.getElementById('anio_reporte');
						var errorAnio=document.getElementById('errorAnio');
						errorAnio.innerText=error.defaultMessage;
						inputAnio.classList.add("is-invalid");
					}
				});
			}
		});
	
	
	
	
	
	
}

function limpiarErrores(){
	document.getElementById('anio_reporte').classList.remove("is-invalid");
	var errorAnio=document.getElementById('errorAnio').innerText="";
	
}