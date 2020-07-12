var token = $("meta[name='_csrf']").attr("content");
var idFacultad;
$(document).ready(function ()
		{
				
			/*$.getJSON('http://localhost:8080/programas-academicos/listar', function(json) {
				console.log(json)
			});*/
			
	$('#modalRegistroFacultad').on('show.bs.modal', function (event) {
		$('#facultad').val("");
		idFacultad=0;
		limpiarErrores();
	});
});


function guardarFacultad(){
	var facultad = $('#facultad').val();
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({'id':idFacultad,'facultad': facultad}),
		url: "/facultad/save",
		cache: false,
		success: function(result) {
			console.log(result);
			toastr.success('Se ha guardado la información', 'Excelente!');
			window.setTimeout(function(){location.reload()},1000);
			idFacultad=0;
		},
		error: function(err) {
			console.log(err);
			if(err.responseJSON.length >0){
				toastr.error('No se pudo procesar la solicitud...', 'Error!');
				err.responseJSON.forEach(function(error){
					if(error.field=="facultad"){
						var inputFacultad=document.getElementById('facultad');
						var errorFacultad=document.getElementById('errorFacultad');
						errorFacultad.innerText=error.defaultMessage;
						inputFacultad.classList.add("is-invalid");
					}
				  });
			  
			}else{
				toastr.error(err.responseJSON.message, 'Error!');
				
			}
		}
	});
	
	
}
function editarFacultad(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/facultad/search/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			console.log(result.facultad);
			$('#modalRegistroFacultad').modal();
			$('#facultad').val(result.facultad);
			idFacultad=result.id;
			
		},
		error: function(err) {
			console.log(err);
			toastr.error(err.responseJSON.message, 'Error!');
		}
	});
		
}

function limpiarErrores(){
	var inputFacultad=document.getElementById('facultad');
	var errorFacultad=document.getElementById('errorFacultad');
	errorFacultad.innerText="";
	inputFacultad.classList.remove("is-invalid");
}