if(eduContinua.jornadas.length>0){
	if(asistenciaGlobal!=null){
		  asistenciaGlobal.forEach(function(a){
			    console.log(a);
			   if(a[0]==eduContinua.participantes.length){
				   var idCheckBox=a[1]+'_all';
				   $('#'+idCheckBox).prop('checked', true);  
			   }
			  }); 
	  }
	  
}	  
	 
	  
	  
	 
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
			   		url: "/asistencia-general-marcar",
			   		data:{'id_jornada':idJornada},
			   		cache: false,
			   		success: function(result) {
			   			toastr.success('Se han actualizado las asistencias de la Jornada', 'Excelente!',{"positionClass": "toast-top-right"});
			   			$('.j_'+idJornada).prop('checked', true);  
			   			
			   		},
			   		error: function(err) {
			   			console.log("entra aca");
			   			console.log(idJornada+'_all');
			   			toastr.error('No se guardaron las asistencias de la Jornada', 'Error!',{"positionClass": "toast-top-right"});
			   			$('#'+idJornada+'_all').prop('checked', false); 
			   		}
			   	});
		       
			}else{//se va a quitar la asistencia
				$.ajax({
			   		headers: {"X-CSRF-TOKEN": token},
			   		type: "GET",
			   		contentType: "application/json; charset=utf-8",
			   		url: "/asistencia-general-desmarcar",
			   		data:{'id_jornada':idJornada},
			   		cache: false,
			   		success: function(result) {
			   			toastr.success('Se han actualizado las asistencias de la Jornada', 'Excelente!',{"positionClass": "toast-top-right"});
			   		 	$('.j_'+idJornada).prop('checked', false); 
			   			
			   		},
			   		error: function(err) {
			   			console.log("entra en else");
			   			console.log(idJornada+'_all');
			   			toastr.error('No se guardaron las asistencias de la Jornada', 'Error!',{"positionClass": "toast-top-right"});
			   			$('#'+idJornada+'_all').prop('checked', true); 
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
			   		url: "/asistencia-marcar",
			   		data:{'id_jornada':idJornada, 'id_participante':idParticipante, 'cant_participantes':eduContinua.participantes.length},
			   		cache: false,
			   		success: function(result) {
			   			toastr.success('Se ha actualizado la asistencia del Participante', 'Excelente!',{"positionClass": "toast-top-right"});
			   			$('#'+idJornada+'_all').prop('checked', result); 
			   			
			   		},
			   		error: function(err) {
			   			toastr.error('No se guardó la asistencia del Participante', 'Error!',{"positionClass": "toast-top-right"});
			   			$('#'+idJornada+'_'+idParticipante).prop('checked', false); 
			   		}
			   	});
		       
			}else{//se va a quitar la asistencia
				$.ajax({
			   		headers: {"X-CSRF-TOKEN": token},
			   		type: "GET",
			   		contentType: "application/json; charset=utf-8",
			   		url: "/asistencia-desmarcar",
			   		data:{'id_jornada':idJornada, 'id_participante':idParticipante},
			   		cache: false,
			   		success: function(result) {
			   			$('#'+idJornada+'_all').prop('checked', false); 
			   			toastr.success('Se ha actualizado la asistencia del Participante', 'Excelente!',{"positionClass": "toast-top-right"});
			   		 	
			   			
			   		},
			   		error: function(err) {
			   			toastr.error('No se guardó la asistencia del Participante', 'Error!',{"positionClass": "toast-top-right"});
			   			$('#'+idJornada+'_'+idParticipante).prop('checked', true); 
			   		}
			   	});
			}  
		    });  
		});
