$(document).ready(function ()
{
	
	$('#selectTipoContinua').on("change", function (e) { 
		e.preventDefault();
		var tipoContinua= $(this).find('option:selected');
		var inputDuracion=document.getElementById('duracion');
		var errorDuracion=document.getElementById('inputErrorDuracion');
		var boton = document.getElementById('btnGuardar');
		console.log("error");
		console.log(errorDuracion);
		console.log(inputDuracion);
		console.log(inputDuracion.value);
		console.log(tipoContinua.val());
		if(tipoContinua.val()=="1" || tipoContinua.val()=="4"){//curso talleres
			console.log("entra por curso");
			if(inputDuracion.value<16){
				errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 16 horas de duración";
				inputDuracion.classList.add("is-invalid");
				boton.disabled=true;
			}
		}else if(tipoContinua.val()==2){//diplomados
			if(inputDuracion.value<90){
				errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 90 horas de duración";
				inputDuracion.classList.add("is-invalid");
				boton.disabled=true;
			}
		}else{
			errorDuracion.innerText="";
			inputDuracion.classList.remove("is-invalid");
			boton.disabled=false;
		}
		
	});
	
	 $('#duracion').keyup(function(event) {
		 var horas = event.target.value;
		 var tipoContinua= $('#selectTipoContinua').find('option:selected');
		var inputDuracion=document.getElementById('duracion');
		var errorDuracion=document.getElementById('inputErrorDuracion');
			var boton = document.getElementById('btnGuardar');
			console.log("horas");
			console.log(horas);
			if(tipoContinua.val()==1 || tipoContinua.val()==4){//curso talleres
				console.log("entra por curso");
				console.log(horas<16);
				if(horas<16){
					errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 16 horas de duración";
					inputDuracion.classList.add("is-invalid");
					boton.disabled=true;
				}else{
					errorDuracion.innerText="";
					inputDuracion.classList.remove("is-invalid");
					boton.disabled=false;
				}
			}else if(tipoContinua.val()==2){//diplomados
				if(horas.value<90){
					errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 90 horas de duración";
					inputDuracion.classList.add("is-invalid");
					boton.disabled=true;
				}else{
					errorDuracion.innerText="";
					inputDuracion.classList.remove("is-invalid");
					boton.disabled=false;
				}
			}else{
				errorDuracion.innerText="";
				inputDuracion.classList.remove("is-invalid");
				boton.disabled=false;
			}
		    
	  });
	
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