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
		
  });
  
  $("#primNom").bind('keypress', function(event) {
	  return text(event);
	});
  
  
  
    $("#segNom").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#primApe").focusout(function(){
	  validateInputTextRequerido('primApe','errorPrimerApellido');
		
  });
  
   $("#primApe").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#segApe").focusout(function(){
	  validateInputTextRequerido('segApe','errorSegundoApellido');
		
  });
  
   $("#segApe").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#fechaNac").focusout(function(){
	  validateInputTextRequerido('fechaNac','errorFechaNacimiento');
		
		
  });
  
  $("#email").focusout(function(){
	  validateInputTextRequerido('email','errorEmail');
		
  });
  
  $("#telefono").focusout(function(){
	  validateInputNumberRequerido('telefono','errorTelefono');
  });
   $("#telefono").bind('keypress', function(event) {
	 return numbers(event);
	});
  
  $("#codigo").focusout(function(){
    if($("#codigo").val() == ""){
		document.getElementById('codigo').classList.add("is-invalid");
	}else{
		document.getElementById('codigo').classList.remove("is-invalid");
	}
  });
  
  $("#codDocente").focusout(function(){
    if($("#codDocente").val() == ""){
		document.getElementById('codDocente').classList.add("is-invalid");
	}else{
		document.getElementById('codDocente').classList.remove("is-invalid");
	}
  });
  
  $("#codDocente").bind('keypress', function(event) {
	 return numbers(event);
	});
  
  $("#dependencia").focusout(function(){
    if($("#dependencia").val() == ""){
		document.getElementById('dependencia').classList.add("is-invalid");
	}else{
		document.getElementById('dependencia').classList.remove("is-invalid");
	}
  });
  
  $("#cargo").focusout(function(){
    if($("#cargo").val() == ""){
		document.getElementById('cargo').classList.add("is-invalid");
	}else{
		document.getElementById('cargo').classList.remove("is-invalid");
	}
  });
  
  
  var date=new Date();
  $('#anioGraduado').mask("0000", {placeholder: "AAAA"});
  $("#anioGraduado").focusout(function(){
    if($("#anioGraduado").val() == ""){
		document.getElementById('anioGraduado').classList.add("is-invalid");
	}else{
		if($("#anioGraduado").val()>date.getFullYear()){
			document.getElementById('anioGraduado').classList.add("is-invalid");
		}else{
			document.getElementById('anioGraduado').classList.remove("is-invalid");
		}
		
	}
  });
  
  $("#anioGraduado").bind('keypress', function(event) {
	 return numbers(event);
	});
  
  $("#profesion").focusout(function(){
    if($("#profesion").val() == ""){
		document.getElementById('profesion').classList.add("is-invalid");
	}else{
		document.getElementById('profesion').classList.remove("is-invalid");
	}
  });
  
  $("#empresa").focusout(function(){
    if($("#empresa").val() == ""){
		document.getElementById('empresa').classList.add("is-invalid");
	}else{
		document.getElementById('empresa').classList.remove("is-invalid");
	}
  });
});
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
