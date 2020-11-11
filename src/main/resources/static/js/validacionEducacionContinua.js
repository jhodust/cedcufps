
$(document).ready(function(){
	console.log("documento listo");
	
	$("#temaPonente").focusout(function(){
    if($("#temaPonente").val() == ""){
		document.getElementById('temaPonente').classList.add("is-invalid");
	}else{
		document.getElementById('temaPonente').classList.remove("is-invalid");
	}
  });
	
	$("#horaInicio").focusout(function(){
	    if($("#horaInicio").val() == ""){
			document.getElementById('horaInicio').classList.add("is-invalid");
		}else{
			document.getElementById('horaInicio').classList.remove("is-invalid");
		}
	  });
	
	$("#horaFin").focusout(function(){
	    if($("#horaFin").val() == ""){
			document.getElementById('horaFin').classList.add("is-invalid");
		}else{
			document.getElementById('horaFin').classList.remove("is-invalid");
		}
	  });
  
  
  
  $("#nombreEdc").focusout(function(){
	  validateInputTextRequerido('nombreEdc','errNombreEdc');
  });
  
  $("#fechaInicioEduCont").focusout(function(){
	  validateInputTextRequerido('fechaInicioEduCont','errFechaInicioEdc');
	  });
  
  $("#fechaFinEduCont").focusout(function(){
	  validateInputTextRequerido('fechaFinEduCont','errFechaFinEdc');
	    
	  });
  $("#fechaLimInscripcionEduCont").focusout(function(){
	  validateInputTextRequerido('fechaLimInscripcionEduCont','errFechaLimInscEdc');
	    
	  });
  $("#duracion").focusout(function(){
	  validateInputNumberRequerido('duracion','errDuracionEdc');
	    
	  });
  
  $("#duracion").bind('keypress', function(event) {
	  return numbers(event);
	});
  
  $("#costoTotalEdc").focusout(function(){
	  validateInputNumberRequerido('costoTotalEdc','errCostoTotalEdc');
	    
	  });

$("#costoTotalEdc").bind('keypress', function(event) {
	  return numbers(event);
	});

$("#costoInscripcionEdc").focusout(function(){
	validateInputNumberNotRequired('costoInscripcionEdc','errCostoInscripEdc');
  });

$("#costoInscripcionEdc").bind('keypress', function(event) {
  return numbers(event);
});

$("#cantMaxPartEdc").focusout(function(){
	validateInputNumberNotRequired('cantMaxPartEdc','errCantPartEdc');
  });

$("#cantMaxPartEdc").bind('keypress', function(event) {
  return numbers(event);
});

$("#porcentajeAsistenciaEdc").focusout(function(){
	validateInputPorcentaje('porcentajeAsistenciaEdc','errPorcAsisEdc');
  });

$("#porcentajeAsistenciaEdc").bind('keypress', function(event) {
  return numbers(event);
});
	
$("#lugarEdc").focusout(function(){
	validateInputTextRequerido('lugarEdc','errLugarEdc');
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
	
	 $("#codigo").focusout(function(){
    if($("#codigo").val() == ""){
		document.getElementById('codigo').classList.add("is-invalid");
	}else{
		document.getElementById('codigo').classList.remove("is-invalid");
	}
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

