var token = $("meta[name='_csrf']").attr("content");
var idDepartamento;
$(document).ready(function ()
		{
				
			/*$.getJSON('http://localhost:8080/programas-academicos/listar', function(json) {
				console.log(json)
			});*/
			
	$('#modalRegistroDepartamento').on('show.bs.modal', function (event) {
		$('#departamento').val("");
		$('#select_facultad_departamento').val(0).trigger('change');
		idDepartamento=0;
		limpiarErrores();
	});
	
	$('#select_facultad_filtro_departamento').on("change", function (e) { 
		e.preventDefault();
		var facultad= $(this).find('option:selected').text();
		var id=$(this).find('option:selected').val();
		
		if(id == 0){
			paginadorLoadAjax("/departamentos-academicos/reload");
		}else{
			var base="/departamentos-academicos/reload?facultad=";
			var regex = / /gi;
			var url=base.concat(facultad.replace(regex,'%20'));
			console.log(url);
			paginadorLoadAjax(url);
		}
		
	});
	
	$('#select_facultad_departamento').on("change", function (e) { 
		e.preventDefault();
		validateSelect('select_facultad_departamento','errorSelectFacultad');
	});
});


function guardarDepartamento(){
	limpiarErrores();
	var departamento = $('#departamento').val();
	var id_facultad = document.getElementById("select_facultad_departamento").value;
	var JSONdepartamento={};
	JSONdepartamento.id=idDepartamento;
	JSONdepartamento.departamento=departamento;
	if(id_facultad != "0"){
		JSONdepartamento.facultad={};
		JSONdepartamento.facultad.id=id_facultad;
	}
	validateInputTextRequerido('departamento','errorDepartamento');
	validateSelect('select_facultad_departamento','errorSelectFacultad');
	if(departamento.trim() == "" || id_facultad==0){
		toastr
			.error(
					'Diligencie el formulario correctamente, los campos son requeridos',
					'Error!');
					return;
	
	}
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(JSONdepartamento),
		url: "/departamentos-academicos/save",
		cache: false,
		success: function(result) {
			toastr.success('Se ha guardado la información', 'Excelente!');
			//window.setTimeout(function(){location.reload()},1000);
			idFacultad=null;
			paginadorLoadAjax('/departamentos-academicos/reload');
			$('#modalRegistroDepartamento').modal('hide');
		},
		error: function(err) {
			  console.log(err);
			if(err.responseJSON.length >0){
				toastr.error('No se pudo procesar la solicitud...', 'Error!');
				err.responseJSON.forEach(function(error){
					if(error.field=="departamento"){
						var inputDepto=document.getElementById('departamento');
						var errorDepto=document.getElementById('errorDepartamento');
						errorDepto.innerText=error.defaultMessage;
						inputDepto.classList.add("is-invalid");
					}
					if(error.field=="facultad"){
						var selectFacultad=document.getElementById('select_facultad_departamento');
						var errorSelectFacultad=document.getElementById('errorSelectFacultad');
						errorSelectFacultad.innerText=error.defaultMessage;
						selectFacultad.classList.add("is-invalid");
					}
				  });
			  
			}else{
				toastr.error(err.responseJSON.message, 'Error!');
				
			}
		}
	});
	
	
}
function editarDepartamento(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/departamentos-academicos/search/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			$('#modalRegistroDepartamento').modal();
			$('#departamento').val(result.departamento);
			 $('#select_facultad_departamento').val(result.facultad.id);
			 $('#select_facultad_departamento').select2().trigger('change');
			idDepartamento=result.id;
			
		},
		error: function(err) {
			toastr.error(err.responseJSON.message, 'Error!');
		}
	});
		
}

function limpiarErrores(){
	var selectFacultad=document.getElementById('select_facultad_departamento');
	var errorSelectFacultad=document.getElementById('errorSelectFacultad');
	errorSelectFacultad.innerText="";
	selectFacultad.classList.remove("is-invalid");
	var inputDepto=document.getElementById('departamento');
	var errorDepto=document.getElementById('errorDepartamento');
	errorDepto.innerText="";
	inputDepto.classList.remove("is-invalid");
}