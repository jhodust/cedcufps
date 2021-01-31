$(document).ready(function ()
{
	$('.btn-delete-educacionContinua').on('click',function(e){
		console.log($(this));
		console.log(e);
		console.log(e.currentTarget.dataset.id);
		swal({
			  title: "¿Está seguro de eliminar la educación continua?",
			  text: "Una vez eliminada no se podrá gestionar ni será tenida en cuenta en los reportes SNIES",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
			   deleteEduContinua(e.currentTarget.dataset.id);
			  }
			});
	});
});


function deleteEduContinua(idAcceso){
	var formData = new FormData();
	  formData.append('idAcceso',idAcceso);
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		enctype: 'multipart/form-data',
	       
		data: formData,
		url: "/educacion-continua/delete",
		processData: false,
	       contentType: false,
	       cache: false,
		success: function(result) {//retornar el diploma
			toastr.success('Se ha eliminado la educación continua exitosamente', 'Excelente!',{"positionClass": "toast-bottom-right","preventDuplicates": true});
		},
		error: function(err) {
			
		}
	});
}