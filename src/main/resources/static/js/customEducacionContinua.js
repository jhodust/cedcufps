$(document).ready(function ()
{
	
});


function deleteEduContinua(element){
	
	
	
	swal({
		  title: "¿Está seguro de eliminar la educación continua?",
		  text: "Una vez eliminada no se podrá gestionar ni será tenida en cuenta en los reportes SNIES",
		  icon: "warning",
		  buttons: ["Cancelar", "Aceptar"],
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
		   deleteEduContinuaAjax(element.dataset.id);
		  }
		});
};


function deleteEduContinuaAjax(idAcceso){
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
			reloadEducacionesContinuas();
		},
		error: function(err) {
			
		}
	});
}

function reloadEducacionesContinuas(){
	var urlListado = '/educacion-continua/reload';
	$('#div_table_educaciones_continuas').load(urlListado);
}