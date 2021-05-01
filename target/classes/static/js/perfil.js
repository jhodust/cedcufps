$(document).ready(function ()
{
	
	
	
	
});

function searchNewSuperAdmin(){
	var doc=$('#documentoSuperAdmin').val();
	var url = '/perfil/search-new-superadmin/'+doc;
	$('#divDatosNewAdmin').load(url);
	mostrar('divSuperAdmin','inline');
	
}

function searchNewDirPrograma(){
	var doc=$('#documentoDirPrograma').val();
	var url = '/perfil/search-new-dirprograma/'+doc;
	$('#divDatosNewDirPrograma').load(url);
	mostrar('divDirPrograma','inline');
}

function updateNewSuperAdmin(element){
	swal({
		  title: "¿Está seguro de ceder el control del super admin?",
		  text: "Una vez confirmado, se cerrará la sesión para actualizar los respectivos permisos",
		  icon: "warning",
		  buttons: ["Cancelar", "Aceptar"],
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  ajaxUpdateSuperAdmin(element.dataset.documento);
		  }
		});
	
	
}

function ajaxUpdateSuperAdmin(document){
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		data: {documento:document},
		url: "/perfil/updateSuperAdmin",
		cache: false,
		success: function(result) {
			toastr.success('Se ha guardado la información', 'Excelente!')
			logout();
		},
		error: function(err) {
			
		}
	});
}
function updateNewDirPrograma(element){
	swal({
		  title: "¿Está seguro de ceder el control de director del programa?",
		  text: "Una vez confirmado, se cerrará la sesión para actualizar los respectivos permisos",
		  icon: "warning",
		  buttons: ["Cancelar", "Aceptar"],
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  ajaxUpdateDirPrograma(element.dataset.documento);
		  }
		});
}

function ajaxUpdateDirPrograma(document){
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		data: {documento:document},
		url: "/perfil/updateDirPrograma",
		cache: false,
		success: function(result) {
			toastr.success('Se ha guardado la información', 'Excelente!')
			logout();
		},
		error: function(err) {
			
		}
	});
}