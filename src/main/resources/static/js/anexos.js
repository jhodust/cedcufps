 console.log(eduContinua);
  var myDropzone = new Dropzone("#myDropzone", { 
	  url: "/educacion-continua/anexos/save",
	  params: {'id':eduContinua.id},
	  success: function (response){
		  toastr.success('Se ha agregado el anexo exitosamente', 'Excelente!',{"positionClass": "toast-top-right"});
		  refreshTableAnexos();
	  }
	  
  });
  
  function refreshTableAnexos(){
		var urlListado = '/educacion-continua/detalles/anexos/'+eduContinua.idAcceso;
		$('#div_table_anexos').load(urlListado);
		
	}
  
  function deleteAnexo(element){
	  swal({
		  title: "Alerta!",
		  text: "¿Está seguro(a) de eliminar el anexo?",
		  icon: "warning",
		  buttons: ["Cancelar", "Aceptar"],
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
		   deleteAnexoAjax(element.dataset.id);
		  }
		});  
  }
  
  function deleteAnexoAjax(idAnexo){
	  var formData = new FormData();
	  formData.append('id',idAnexo);
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		enctype: 'multipart/form-data',
	       
		data: formData,
		url: "/educacion-continua/anexos/delete",
		processData: false,
	       contentType: false,
	       cache: false,
		success: function(result) {//retornar el diploma
			toastr.success('Se ha eliminado el anexo exitosamente', 'Excelente!',{"positionClass": "toast-bottom-right","preventDuplicates": true});
			refreshTableAnexos();
		},
		error: function(err) {
			
		}
	});
  }