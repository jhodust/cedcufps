function solicitarCambioEmail(){
		limpiarErrores();
		var documento = $('#documentoRecovery').val();
		var email = $('#emailRecovery').val();
		
		var valid1=validateInputTextRequerido('documentoRecovery','errorDocumentoRecovery');
		var valid2=validateInputTextRequerido('emailRecovery','errorEmailRecovery');
		
		if(!valid1 ||  !valid2 ){
			toastr
				.error(
						'Diligencie el formulario correctamente, los campos son requeridos',
						'Error!');
						return;
		
		}
		showSpinnerModal("btnEmailRecovery","btnSpinnerRecuperacionEmail");
		$.ajax({
			headers: {"X-CSRF-TOKEN": token},
			type: "GET",
			contentType: "application/json; charset=utf-8",
			data: {'documento':documento, 'email':email},
			url: "/change-email",
			cache: false,
			success: function(result) {
				toastr.success('Revise su bandeja de correo para actualizar su información', 'Excelente!');
				//window.setTimeout(function(){location.reload()},1000);
				$('#modalRecuperacionEmail').modal('hide');
				hideSpinnerModal("btnEmailRecovery","btnSpinnerRecuperacionEmail");
			},
			error: function(err) {
				hideSpinnerModal("btnEmailRecovery","btnSpinnerRecuperacionEmail");
				  console.log(err);
				toastr.error(err.responseJSON.message, 'Error!');
				
			}
		});
		
		
	}


function limpiarErrores(){
	document.getElementById('documentoRecovery').classList.remove("is-invalid");
	document.getElementById('errorDocumentoRecovery').innerText="";
	
	document.getElementById('emailRecovery').classList.remove("is-invalid");
	document.getElementById('errorEmailRecovery').innerText="";
	
}