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
				
			$.getJSON('http://localhost:8080/programas-academicos/listar', function(json) {
				console.log(json)
			});
			
	$('#modalRegistroPrograma').on('show.bs.modal', function (event) {
		$('#codigo').val("");
		$('#programa').val("");
		idPrograma=null;
	});
});


function guardarPrograma(){
	var codigo = $('#codigo').val();
	var programa = $('#programa').val();
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({'id':idPrograma,'codigo': codigo,'nombrePrograma':programa}),
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
	$('#modalRegistroPrograma').modal();
	$('#codigo').val(elemento.dataset.codigo);
	$('#programa').val(elemento.dataset.programa);
	idPrograma=elemento.dataset.id;
	
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