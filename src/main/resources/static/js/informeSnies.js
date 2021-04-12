var token = $("meta[name='_csrf']").attr("content");
$(document).ready(function ()
		{

$("#fechaInicioReporte").flatpickr({
	dateFormat: "d/m/Y",
	maxDate: new Date(),
	onChange: function(selectedDates, dateStr, instance) {
	    	
           
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
	var fechaFin =$('#fechaFinReporte').val();
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
	showSpinnerModal("btnGenerarReportesSnies","btnSpinnerSNIES");
		$.ajax({
			headers: {"X-CSRF-TOKEN": token},
			
			type: "POST",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(JSONdata),
			url: "/reportes-SNIES/generar",
			cache: false,
			success: function(result) {
				toastr.success('Se ha generado el reporte', 'Excelente!')
				window.setTimeout(function(){location.reload()},2000);
				
			},
			error: function(err) {
				hideSpinnerModal("btnGenerarReportesSnies","btnSpinnerSNIES");
				toastr.error('No se pudo procesar la solicitud...', 'Error!');
				document.getElementById('divMsgGeneralSnies').style.display='block';
				document.getElementById('msgGeneralSnies').innerText=err.responseJSON.message;
				
				
			}
		});
	
	
	
	
	
	
}

function limpiarErrores(){
	document.getElementById('divMsgGeneralSnies').style.display='none';
	document.getElementById('msgGeneralSnies').innerText='';
}