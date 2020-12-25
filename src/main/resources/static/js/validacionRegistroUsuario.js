$(document).ready(function(){

	 const pattern = new RegExp('^[A-Z]+$', 'i');

  $("#numDoc").focusout(function(){
	  validateInputTextRequerido('numDoc','errorNumeroDocumento');
	  
  });
  
  $("#numDoc").bind('keypress', function(event) {
	 return numbers(event);
	});
  
  $("#fechaExp").focusout(function(){
		validateInputTextRequerido('fechaExp','errorFechaExp');
  });
  
  
  $("#primNom").focusout(function(){
	  validateInputTextRequerido('primNom','errorPrimerNombre');
	  validateLengthTxt('primNom','errorPrimerNombre',20);
		
		
  });
  
  $("#primNom").bind('keypress', function(event) {
	  return text(event);
	});
  
  
  
    $("#segNom").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#primApe").focusout(function(){
	  validateInputTextRequerido('primApe','errorPrimerApellido');
	  validateLengthTxt('primApe','errorPrimerApellido',20);
	  
		
  });
  
  $("#segNom").focusout(function(){
	  validateLengthTxt('segNom','errorSegundoNombre',20);
		
  });
  
   $("#primApe").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#segApe").focusout(function(){
	  //validateInputTextRequerido('segApe','errorSegundoApellido');
	  validateLengthTxt('segApe','errorSegundoApellido',20);
	  
		
  });
  
   $("#segApe").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#fechaNac").focusout(function(){
	  validateInputTextRequerido('fechaNac','errorFechaNacimiento');
		
		
  });
  
  $("#email").focusout(function(){
	  
	  validateInputTextRequerido('email','errorEmail');
	  validateEmail('email','errorEmail');
		
  });
  
  $("#direccion").focusout(function(){
	  validateLengthTxt('direccion','errorDireccion',50);
		
  });
  
  $("#telefono").focusout(function(){
	  validateInputNumberRequerido('telefono','errorTelefono');
  });
   $("#telefono").bind('keypress', function(event) {
	 return numbers(event);
	});
  
  $("#codigo").focusout(function(){
	  validateInputTextRequerido('codigo','errorCodigoEstudiante');
    
  });
  
  $("#codDocente").focusout(function(){
	  validateInputTextRequerido('codDocente','errorCodigoDocente');
    
  });
  
  $("#codDocente").bind('keypress', function(event) {
	 return numbers(event);
	});
  
  $("#dependencia").focusout(function(){
	  validateInputTextRequerido('dependencia','errorDependenciaAdminvo');
	  validateLengthTxt('dependencia','errorDependenciaAdminvo',50);
		
    
  });
  
  $("#cargo").focusout(function(){
	  validateInputTextRequerido('cargo','errorCargoAdminvo');
	  validateLengthTxt('cargo','errorCargoAdminvo',50);
  });
  
  
  
  $('#anioGraduado').mask("0000", {placeholder: "AAAA"});
  $("#anioGraduado").focusout(function(){
	  validateAnio('anioGraduado','errorAnioGraduado');
  });
  
  $("#anioGraduado").bind('keypress', function(event) {
	 return numbers(event);
	});
  
  $("#profesion").focusout(function(){
	  validateInputTextRequerido('profesion','errorProfesionExterno');
	  validateLengthTxt('profesion','errorProfesionExterno',50);
    
  });
  
  $("#empresa").focusout(function(){
	  validateInputTextRequerido('empresa','errorEmpresa');
	  validateLengthTxt('empresa','errorEmpresa',50);
  });
});

function validateAnio(idInput,idError){
	var date=new Date();
	if(document.getElementById(idInput).value == ''){
		validateInputTextRequerido(idInput,idError);
		return false;
	}else{
		if( document.getElementById(idInput).value >date.getFullYear()){
			document.getElementById(idInput).classList.add("is-invalid");
			document.getElementById(idError).innerText='El año ingresado es superior al actual';
			return false;
		}else{
			if(document.getElementById(idInput).value.length != 4){
				document.getElementById(idInput).classList.add("is-invalid");
				document.getElementById(idError).innerText='El año ingresado es inválido, debe tener 4 dígitos';
				return false;
			}else{
				document.getElementById(idInput).classList.remove("is-invalid");
				document.getElementById(idError).innerText='';
				return true;
			}
			
		}
	}
	
}
function numbers(event){
	 var regex = new RegExp("^[0-9]+$");
	  var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	  if (!regex.test(key)) {
	    event.preventDefault();
	    return false;
	  }
}


function text(event){
	 var regex = new RegExp("^[a-zA-ZÀ-ÿ\u00f1\u00d1 ]+$");
	  var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	  if (!regex.test(key)) {
	    event.preventDefault();
	    return false;
	  }
}
