$(document).ready(function(){

$("#fechaInicioReporte").focusout(function(){
	validateFechaInicioReporte();
	    
	  });

$("#fechaFinReporte").focusout(function(){
	validateFechaFinReporte();
	    
	  });

})

function validateFechaInicioReporte(){
	validateInputTextRequerido('fechaInicioReporte','errFechaInicioReporte');
}

function validateFechaFinReporte(){
	validateInputTextRequerido('fechaFinReporte','errFechaFinReporte');
}