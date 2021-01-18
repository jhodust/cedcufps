var token = $("meta[name='_csrf']").attr("content");
$(document).ready(function ()
		{

$("#fechaInicioReporte").flatpickr({
	dateFormat: "d/m/Y",
	maxDate: new Date(),
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
       	    maxDate: new Date()
       	});
	}
});

		})
function generarReporteSnies(){
	limpiarErrores();
	var fechaInicio =$('#fechaInicioReporte').val();
	console.log(fechaInicio);
	var fechaFin =$('#fechaFinReporte').val();
	console.log(fechaFin);
	var descripcion =$('#descripcionReporte').val();
	//var semestre = $('#select_semestre').val();
	if(fechaInicio.trim() == "" || fechaFin.trim() == "" || descripcion.trim() == "" ){
		validateFechaInicioReporte();
		validateFechaFinReporte();
		validateDescripcionReporte();
		toastr
		.error(
				'Debes ingresar todos los campos requeridos',
				'Error!');
				return;
	}
	var JSONdata={};
	JSONdata.fechaInicio=fechaInicio.trim();
	JSONdata.fechaFin=fechaFin.trim();
	JSONdata.descripcion=descripcion.trim();
		$.ajax({
			headers: {"X-CSRF-TOKEN": token},
			
			type: "POST",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(JSONdata),
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
				document.getElementById('divMsgGeneralSnies').style.display='block';
				document.getElementById('msgGeneralSnies').innerText=err.responseJSON.message;
				
				
			}
		});
	
	
	
	
	
	
}

function limpiarErrores(){
	document.getElementById('divMsgGeneralSnies').style.display='none';
	document.getElementById('msgGeneralSnies').innerText='';
}