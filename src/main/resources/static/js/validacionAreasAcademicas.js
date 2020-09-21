$(document).ready(function(){
	console.log("documento listo");
	$("#facultad").focusout(function(){
    if($("#facultad").val() == ""){
		document.getElementById('facultad').classList.add("is-invalid");
	}else{
		document.getElementById('facultad').classList.remove("is-invalid");
	}
  });
  
  $("#facultad").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#departamento").focusout(function(){
    if($("#departamento").val() == ""){
		document.getElementById('departamento').classList.add("is-invalid");
	}else{
		document.getElementById('departamento').classList.remove("is-invalid");
	}
  });
  
  $("#departamento").bind('keypress', function(event) {
	  return text(event);
	});
	
	 $("#programa").focusout(function(){
    if($("#programa").val() == ""){
		document.getElementById('programa').classList.add("is-invalid");
	}else{
		document.getElementById('programa').classList.remove("is-invalid");
	}
  });
  
  $("#programa").bind('keypress', function(event) {
	  return text(event);
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
