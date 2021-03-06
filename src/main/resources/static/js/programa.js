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
		idPrograma=null;
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
});


function guardarPrograma(){
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
	limpiarErrores();
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(JSONprograma),
		url: "http://localhost:8080/programa/save",
		cache: false,
		success: function(result) {
			toastr.success('Se ha guardado la información', 'Excelente!')
			window.setTimeout(function(){location.reload()},1000);
			idPrograma=null;
		},
		error: function(err) {
			toastr.error('No se pudo procesar la solicitud...', 'Error!');
			console.log(err);
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
		}
	});
	
	
}
function editarPrograma(elemento){
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/programa/search/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			console.log(result);
			$('#modalRegistroPrograma').modal();
			$('#codigo').val(result.codigo);
			$('#programa').val(result.programa);
			$('#select_facultad_programa').val(result.facultad.id);
			$('#select_facultad_programa').select2().trigger('change');
			$('#select_director_programa').val(result.directorPrograma.id);
			$('#select_director_programa').select2().trigger('change');
			idPrograma=elemento.dataset.id;
			 
			
		},
		error: function(err) {
			console.log(err);
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