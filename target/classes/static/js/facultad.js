var token = $("meta[name='_csrf']").attr("content");
var idFacultad;
$(document).ready(function ()
		{
				
			/*$.getJSON('http://localhost:8080/programas-academicos/listar', function(json) {
				console.log(json)
			});*/
			
	$('#modalRegistroFacultad').on('show.bs.modal', function (event) {
		$('#facultad').val("");
		idFacultad=null;
	});
});


function guardarFacultad(){
	var facultad = $('#facultad').val();
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({'id':idFacultad,'facultad': facultad}),
		url: "http://localhost:8080/facultad/save",
		cache: false,
		success: function(result) {
			toastr.success('Se ha guardado la información', 'Excelente!')
			window.setTimeout(function(){location.reload()},1000);
			idFacultad=null;
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
	
	
}
function editarFacultad(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "http://localhost:8080/facultad/search/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			console.log(result.facultad);
			$('#modalRegistroFacultad').modal();
			$('#facultad').val(result.facultad);
			idFacultad=result.id;
			
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
		
}