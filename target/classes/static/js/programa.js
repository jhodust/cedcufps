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
		idPrograma=null;
	});
	
	$('#select_facultad_filtro_programa').on("change", function (e) { 
		e.preventDefault();
		var facultad= $(this).find('option:selected').text();
		var id=$(this).find('option:selected').val();
		if(id == 0){
			window.location="/programas-academicos";
		}else{
			window.location="/programas-academicos/filter/"+facultad;
		}
		
	});
});


function guardarPrograma(){
	var codigo = $('#codigo').val();
	var programa = $('#programa').val();
	var id_facultad = document.getElementById("select_facultad_programa").value;
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({'id':idPrograma,'codigo': codigo,'nombrePrograma':programa,'facultad':{'id':id_facultad}}),
		url: "http://localhost:8080/programa/save",
		cache: false,
		success: function(result) {
			$("#msg").html( "<span style='color: green'>Company added successfully</span>" );
			toastr.success('Se ha guardado la información', 'Excelente!')
			window.setTimeout(function(){location.reload()},1000);
			idPrograma=null;
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
	
	
}
function editarPrograma(elemento){
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "http://localhost:8080/programa/search/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			$('#modalRegistroPrograma').modal();
			$('#codigo').val(result.codigo);
			$('#programa').val(result.nombrePrograma);
			$('#select_facultad_programa').val(result.facultad.id);
			 $('#select_facultad_programa').select2().trigger('change');
			idPrograma=elemento.dataset.id;
			 
			
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
	
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