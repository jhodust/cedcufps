$(document).ready(function(){
	console.log("documento listo");
	$("#facultad").focusout(function(){
		validateInputTextRequerido('facultad','errorFacultad');
    
  });
  
  $("#facultad").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#departamento").focusout(function(){
	  validateInputTextRequerido('departamento','errorDepartamento');
  });
  
  $("#departamento").bind('keypress', function(event) {
	  return text(event);
	});
	
	 $("#programa").focusout(function(){
		 validateInputTextRequerido('programa','errorPrograma');
  });
  
  $("#programa").bind('keypress', function(event) {
	  return text(event);
	});
	
	 $("#codigo").focusout(function(){
		 validateInputTextRequerido('codigo','errorCodigo');
   
  });
  
  $("#codigo").bind('keypress', function(event) {
	  return numbers(event);
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
