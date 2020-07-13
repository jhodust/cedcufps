if(jornadas.length>0){
	if(asistenciaGlobal!=null){
		  asistenciaGlobal.forEach(function(a){
			    console.log(a);
			   if(a[0]==participantes.length){
				   var idCheckBox=a[1]+'_all';
				   $('#'+idCheckBox).attr('checked', true);  
			   }
			  }); 
	  }
	  if(asistencias!=null){
		  asistencias.forEach(function(j){
			    var idCheckBox = j.jornada.id+"_"+j.participante.id;
			    $('#'+idCheckBox).attr('checked', true);  
			    console.log("for each");
			  }); 
	  }
}	  
	  console.log("entra a aplicar cambios");
	  document.getElementById('cardListado').style.display='flex';
	  document.getElementById('spinner').style.display='none';
	  console.log("aplicó cambios");
	  
	  
	 
	  $(document).ready(function ()
				{
					
		  $(".all").click(function(e) {  
		       console.log(e); 
		       console.log(e.target.dataset.jornada); 
		       var idJornada=e.target.dataset.jornada;
		       if($('#'+idJornada+'_all').is(':checked')) {//se va a marcar la asistencia
			       $.ajax({
			   		headers: {"X-CSRF-TOKEN": token},
			   		type: "GET",
			   		contentType: "application/json; charset=utf-8",
			   		url: "/asistencia-general-marcar/"+idJornada,
			   		cache: false,
			   		success: function(result) {
			   			toastr.success('Se han actualizado las asistencias de la Jornada', 'Excelente!',{"positionClass": "toast-top-right"});
			   			$('.j_'+idJornada).attr('checked', true);  
			   			
			   		},
			   		error: function(err) {
			   			toastr.error('No se guardaron las asistencias de la Jornada', 'Error!',{"positionClass": "toast-top-right"});
			   			$('#'+idJornada+'_all').attr('checked', false); 
			   		}
			   	});
		       
			}else{//se va a quitar la asistencia
				$.ajax({
			   		headers: {"X-CSRF-TOKEN": token},
			   		type: "GET",
			   		contentType: "application/json; charset=utf-8",
			   		url: "/asistencia-general-desmarcar/"+idJornada,
			   		cache: false,
			   		success: function(result) {
			   			toastr.success('Se han actualizado las asistencias de la Jornada', 'Excelente!',{"positionClass": "toast-top-right"});
			   		 	$('.j_'+idJornada).attr('checked', false); 
			   			
			   		},
			   		error: function(err) {
			   			toastr.error('No se guardaron las asistencias de la Jornada', 'Error!',{"positionClass": "toast-top-right"});
			   			$('#'+idJornada+'_all').attr('checked', true); 
			   		}
			   	});
			}  
		    });  
		  
		  
		  $(".checkAsistencia").click(function(e) {  
		       console.log(e); 
		       console.log(e.target.dataset.jornada); 
		       var idJornada=e.target.dataset.jornada;
		       var idParticipante=e.target.dataset.participante;
		       //e.preventDefault();
		       if($('#'+idJornada+'_'+idParticipante).is(':checked')) {//se va a marcar la asistencia
			       $.ajax({
			   		headers: {"X-CSRF-TOKEN": token},
			   		type: "GET",
			   		contentType: "application/json; charset=utf-8",
			   		url: "/asistencia-marcar/"+idJornada+"/"+idParticipante,
			   		cache: false,
			   		success: function(result) {
			   			toastr.success('Se ha actualizado la asistencia del Participante', 'Excelente!',{"positionClass": "toast-top-right"});
			   			
			   			
			   		},
			   		error: function(err) {
			   			toastr.error('No se guardó la asistencia del Participante', 'Error!',{"positionClass": "toast-top-right"});
			   			$('#'+idJornada+'_'+idParticipante).attr('checked', false); 
			   		}
			   	});
		       
			}else{//se va a quitar la asistencia
				$.ajax({
			   		headers: {"X-CSRF-TOKEN": token},
			   		type: "GET",
			   		contentType: "application/json; charset=utf-8",
			   		url: "/asistencia-desmarcar/"+idJornada+"/"+idParticipante,
			   		cache: false,
			   		success: function(result) {
			   			toastr.success('Se ha actualizado la asistencia del Participante', 'Excelente!',{"positionClass": "toast-top-right"});
			   		 	
			   			
			   		},
			   		error: function(err) {
			   			toastr.error('No se guardó la asistencia del Participante', 'Error!',{"positionClass": "toast-top-right"});
			   			$('#'+idJornada+'_'+idParticipante).attr('checked', true); 
			   		}
			   	});
			}  
		    });  
		});
