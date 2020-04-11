var token = $("meta[name='_csrf']").attr("content");




function generarReporteSnies(){
	var anio = $('#anio_reporte').val();
	//var semestre = $('#select_semestre').val();
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
			/*window.setTimeout(function(){location.reload()},1000);*/
			
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
	
	
}