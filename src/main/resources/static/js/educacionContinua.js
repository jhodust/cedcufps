$(document).ready(function ()
{
console.log("preparando datepicker");
	$("#fechaLimInscripcionEduCont").flatpickr({
   		enableTime: true,
   	    dateFormat: "d/m/Y H:i",
   	    defaultDate:fechaLimInscripcionEvento,
   	    maxDate: fechaFinEvento,
   	});
	
	$("#fechaInicioEduCont").flatpickr({
		enableTime: true,
		dateFormat: "d/m/Y H:i",
		defaultDate:fechaInicioEvento,
	    plugins: [new rangePlugin({ 
	    							input: "#fechaFinEduCont",
	    							enableTime: true,
	    						    dateFormat: "d/m/Y H:i",
	    							defaultDate:fechaFinEvento,
	    							})],
		onChange: function(selectedDates, dateStr, instance) {
		    	console.log(selectedDates);
		    	console.log(selectedDates[0].toDateString());
		    	console.log(dateStr);
		    	console.log(selectedDates[0].getDate());
		    	console.log("toString:" + selectedDates[0].toString());
		    	console.log("dia: " + selectedDates[0].getDay());
		    	console.log("mes: " + selectedDates[0].getMonth());
		    	console.log("UTCMONTH " + selectedDates[0].getUTCMonth());
		    	console.log("fullYear: " + selectedDates[0].getFullYear());
		    	console.log("años: " + selectedDates[0].getYear());
		    	console.log("horas: "+ selectedDates[0].getHours());
		    	console.log("toLocaleString: "+selectedDates[0].toLocaleString());
		    	console.log("toLocaleDateString: "+selectedDates[0].toLocaleDateString());
		    	
		    	
		    	console.log("minDate: "+selectedDates[0].toDateString());
	           
		    	$("#fechaLimInscripcionEduCont").flatpickr({
	       		enableTime: true,
	       	    dateFormat: "d/m/Y H:i",
	       	    defaultDate:fechaLimInscripcionEvento,
	       	    maxDate: selectedDates[1].toLocaleDateString(),
	       	});
		}
	});
});