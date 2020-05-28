var token = $("meta[name='_csrf']").attr("content");




function generarReporteSnies(){
	limpiarErrores();
	var anio = $('#anio_reporte').val();
	//var semestre = $('#select_semestre').val();
	var f = new Date();
	if(anio>f.getFullYear()){
		var inputAnio=document.getElementById('anio_reporte');
		var errorAnio=document.getElementById('errorAnio');
		errorAnio.innerText="El año ingresado es mayor al año actual";
		inputAnio.classList.add("is-invalid");
	}else{
		$.ajax({
			headers: {"X-CSRF-TOKEN": token},
			type: "POST",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify({'anio':anio}),
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
	
	
	
	
	
}

function limpiarErrores(){
	var inputAnio=document.getElementById('anio_reporte');
	var errorAnio=document.getElementById('errorAnio');
	errorAnio.innerText="";
	inputAnio.classList.remove("is-invalid");
}