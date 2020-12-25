var token = $("meta[name='_csrf']").attr("content");
var idPrograma;
toastr.options = {
		  "closeButton": true,
		  "debug": false,
		  "newestOnTop": false,
		  "progressBar": false,
		  "positionClass": "toast-bottom-right",
		  "preventDuplicates": false,
		  "onclick": null,
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "5000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
		}
$(document).ready(function ()
		{
			
			/*$.getJSON('http://localhost:8080/programas-academicos/listar', function(json) {
				console.log(json)
			});*/
			
	$('#modalRegistroPrograma').on('show.bs.modal', function (event) {
		$('#codigo').val("");
		$('#programa').val("");
		$('#select_facultad_programa').val(0).trigger('change');
		$('#select_director_programa').val(0).trigger('change');
		idPrograma=0;
		limpiarErrores();
	});
	
	$('#select_facultad_filtro_programa').on("change", function (e) { 
		e.preventDefault();
		var facultad= $(this).find('option:selected').text();
		var id=$(this).find('option:selected').val();
		if(id == 0){
			window.location="/programas-academicos";
		}else{
			window.location="/programas-academicos?facultad="+facultad;
		}
		
	});
	
	$('#select_director_programa').on("change", function (e) { 
		e.preventDefault();
		var docente= $(this).find('option:selected').text();
		var id=$(this).find('option:selected').val();
		var docente = {
            "id" : id,
            "idP": idPrograma
		}
		toastr.clear();
        $.ajax({
			headers: {"X-CSRF-TOKEN": token},
            type: "GET",
            url: "/programa/search-director?" + $.param(docente),
            dataType : 'json',
            contentType: "application/json; charset=utf-8",
			cache: false,
			success: function(result) {
				console.log(result);
				var element=document.getElementById("alertDirector");
				if(result.length > 0){
					element.innerText="El/La docente ya es director del programa " + result[0].programa + " y sería desvinculado.";
					element.style.display = 'inline';
				}else{
					element.innerText="";
					element.style.display = 'none';
				}
				
			},
        });
		
	});
	
	$('#select_facultad_programa').on("change", function (e) { 
		e.preventDefault();
		validateSelect('select_facultad_programa','errorSelectFacultad');
	});
	
	$('#select_director_programa').on("change", function (e) { 
		e.preventDefault();
		validateSelect('select_director_programa','errorSelectDirectorPrograma');
	});
	
});


function guardarPrograma(){
	limpiarErrores();
	var codigo = $('#codigo').val();
	var programa = $('#programa').val();
	var id_facultad = document.getElementById("select_facultad_programa").value;
	var id_director = document.getElementById("select_director_programa").value;
	
	var JSONprograma={};
	JSONprograma.id=idPrograma;
	JSONprograma.codigo=codigo;
	JSONprograma.programa=programa;
	if(id_facultad != "0"){
		JSONprograma.facultad={};
		JSONprograma.facultad.id=id_facultad;
	}
	if(id_director != "0"){
		JSONprograma.directorPrograma={};
		JSONprograma.directorPrograma.id=id_director;
	}
	
	validateSelect('select_facultad_programa','errorSelectFacultad');
	validateSelect('select_director_programa','errorSelectDirectorPrograma');
	validateInputTextRequerido('programa','errorPrograma');
	validateInputTextRequerido('codigo','errorCodigo');
	if(codigo.trim() == "" || programa.trim() == "" || id_facultad==0 || id_director==0){
		toastr
			.error(
					'Diligencie el formulario correctamente, los campos son requeridos',
					'Error!');
					return;
	
	}
	console.log("json enviado: ");
	console.log(JSONprograma);
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(JSONprograma),
		url: "/programa/save",
		cache: false,
		success: function(result) {
			toastr.success('Se ha guardado la información', 'Excelente!')
			window.setTimeout(function(){location.reload()},1000);
			idPrograma=0;
		},
		error: function(err) {
			
			console.log(err);
			if(err.responseJSON.length >0){
				toastr.error('No se pudo procesar la solicitud...', 'Error!');
				err.responseJSON.forEach(function(error){
				if(error.field=="programa"){
					var inputPrograma=document.getElementById('programa');
					var errorPrograma=document.getElementById('errorPrograma');
					errorPrograma.innerText=error.defaultMessage;
					inputPrograma.classList.add("is-invalid");
				}
				if(error.field=="codigo"){
					var inputCodigo=document.getElementById('codigo');
					var errorCodigo=document.getElementById('errorCodigo');
					errorCodigo.innerText=error.defaultMessage;
					inputCodigo.classList.add("is-invalid");
				}
				if(error.field=="facultad"){
					var selectFacultad=document.getElementById('select_facultad_programa');
					var errorSelectFacultad=document.getElementById('errorSelectFacultad');
					errorSelectFacultad.innerText=error.defaultMessage;
					selectFacultad.classList.add("has-error");
				}
				if(error.field=="directorPrograma"){
					var selectDirector=document.getElementById('select_director_programa');
					var errorSelectDirector=document.getElementById('errorSelectDirectorPrograma');
					errorSelectDirector.innerText=error.defaultMessage;
					selectDirector.classList.add("has-error");
				}
			  });
			}else{
				toastr.error(err.responseJSON.message, 'Error!');
				
			}
			
		}
	});
	
	
}
function editarPrograma(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/search/programa/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			console.log(result);
			$('#modalRegistroPrograma').modal();
			idPrograma=result.id;
			$('#codigo').val(result.codigo);
			$('#programa').val(result.programa);
			$('#select_facultad_programa').val(result.idFacultad);
			$('#select_facultad_programa').select2().trigger('change');
			if(result.idDirector!=null){
				$('#select_director_programa').val(result.idDirector);	
			}
			$('#select_director_programa').select2().trigger('change');
			
			 
			
		},
		error: function(err) {
			console.log(err);
			toastr.error(err.responseJSON.message, 'Error!');
		}
	});
	
}

function limpiarErrores(){
	var selectFacultad=document.getElementById('select_facultad_programa');
	var errorSelectFacultad=document.getElementById('errorSelectFacultad');
	errorSelectFacultad.innerText="";
	selectFacultad.classList.remove("has-error");
	var selectDirector=document.getElementById('select_director_programa');
	var errorSelectDirector=document.getElementById('errorSelectDirectorPrograma');
	errorSelectDirector.innerText="";
	selectDirector.classList.remove("has-error");
	var inputCodigo=document.getElementById('codigo');
	var errorCodigo=document.getElementById('errorCodigo');
	errorCodigo.innerText="";
	inputCodigo.classList.remove("is-invalid");
	var inputPrograma=document.getElementById('programa');
	var errorPrograma=document.getElementById('errorPrograma');
	errorPrograma.innerText="";
	inputPrograma.classList.remove("is-invalid");
	
}

/*
 * if (confirm('Do you really want to delete record?')) {
				var id = $(this).attr('id');
				var parent = $(this).parent().parent();
				$.ajax({
					type: "DELETE",
					url: "http://localhost:8080/company/delete/" + id,
					cache: false,
					success: function() {
						parent.fadeOut('slow', function() {
							$(this).remove();
						});
						location.reload(true)
					},
					error: function() {
						$('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function() {
							$(this).remove();
						});
					}
				});
			}
	*/