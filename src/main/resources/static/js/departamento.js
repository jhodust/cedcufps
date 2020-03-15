var token = $("meta[name='_csrf']").attr("content");
var idDepartamento;
$(document).ready(function ()
		{
				
			$.getJSON('http://localhost:8080/programas-academicos/listar', function(json) {
				console.log(json)
			});
			
	$('#modalRegistroDepartamento').on('show.bs.modal', function (event) {
		$('#departamento').val("");
		$('#select_facultad').val(0).trigger('change');
		idDepartamento=null;
	});
});


function guardarDepartamento(){
	var departamento = $('#departamento').val();
	var id_facultad = document.getElementById("select_facultad").value;
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({'id':idDepartamento,'departamento': departamento, 'facultad':{'id':id_facultad}}),
		url: "/departamento/save",
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
function editarDepartamento(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/departamento/search/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			$('#modalRegistroDepartamento').modal();
			$('#departamento').val(result.departamento);
			 $('#select_facultad').val(result.facultad.id);
			 $('#select_facultad').select2().trigger('change');
			idDepartamento=result.id;
			
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
		
}