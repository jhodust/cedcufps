$(document).ready(function(){

	 const pattern = new RegExp('^[A-Z]+$', 'i');

  $("#numDoc").focusout(function(){
    if($("#numDoc").val() == ""){
		document.getElementById('numDoc').classList.add("is-invalid");
	}else{
		document.getElementById('numDoc').classList.remove("is-invalid");
	}
  });
  
  $("#numDoc").bind('keypress', function(event) {
	 return numbers(event);
	});
  
  $("#fechaExp").focusout(function(){
    if($("#fechaExp").val() == ""){
		document.getElementById('fechaExp').classList.add("is-invalid");
	}else{
		document.getElementById('fechaExp').classList.remove("is-invalid");
	}
  });
  
  
  $("#primNom").focusout(function(){
    if($("#primNom").val() == ""){
		document.getElementById('primNom').classList.add("is-invalid");
	}else{
		document.getElementById('primNom').classList.remove("is-invalid");
	}
  });
  
  $("#primNom").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#segNom").focusout(function(){
    if($("#segNom").val() == ""){
		document.getElementById('segNom').classList.remove("is-invalid");
	}
  });
  
    $("#segNom").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#primApe").focusout(function(){
    if($("#primApe").val() == ""){
		document.getElementById('primApe').classList.add("is-invalid");
	}else{
		document.getElementById('primApe').classList.remove("is-invalid");
	}
  });
  
   $("#primApe").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#segApe").focusout(function(){
    if($("#segApe").val() == ""){
		document.getElementById('segApe').classList.add("is-invalid");
	}else{
		document.getElementById('segApe').classList.remove("is-invalid");
		
	}
  });
  
   $("#segApe").bind('keypress', function(event) {
	  return text(event);
	});
  
  $("#fechaNac").focusout(function(){
    if($("#fechaNac").val() == ""){
		document.getElementById('fechaNac').classList.add("is-invalid");
	}else{
		document.getElementById('fechaNac').classList.remove("is-invalid");
	}
  });
  
  $("#email").focusout(function(){
    if($("#email").val() == ""){
		document.getElementById('email').classList.add("is-invalid");
	}else{
		document.getElementById('email').classList.remove("is-invalid");
	}
  });
  
  $("#telefono").focusout(function(){
    if($("#telefono").val() == ""){
		document.getElementById('telefono').classList.add("is-invalid");
	}else{
		document.getElementById('telefono').classList.remove("is-invalid");
	}
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
