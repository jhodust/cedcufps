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
	 
function asistencia(element){
	if(element.checked){
		$.ajax({
	   		headers: {"X-CSRF-TOKEN": token},
	   		type: "GET",
	   		contentType: "application/json; charset=utf-8",
	   		url: "/educacion-continua/attendance/check",
	   		data:{'id_jornada':element.dataset.jornada, 'id_participante':element.dataset.participante, 'cant_participantes':eduContinua.participantes.length},
	   		cache: false,
	   		success: function(result) {
	   			toastr.success('Se ha actualizado la asistencia del Participante', 'Excelente!',{"positionClass": "toast-top-right"});
	   			$('#'+element.dataset.jornada+'_all').prop('checked', result); 
	   			refreshTableAsistentes();
	   		},
	   		error: function(err) {
	   			toastr.error('No se guardó la asistencia del Participante', 'Error!',{"positionClass": "toast-top-right"});
	   			element.checked=false;
	   			//$('#'+idJornada+'_'+idParticipante).prop('checked', false); 
	   		}
	   	});
	}else{
		$.ajax({
	   		headers: {"X-CSRF-TOKEN": token},
	   		type: "GET",
	   		contentType: "application/json; charset=utf-8",
	   		url: "/educacion-continua/attendance/uncheck",
	   		data:{'id_jornada':element.dataset.jornada, 'id_participante':element.dataset.participante},
	   		cache: false,
	   		success: function(result) {
	   			$('#'+element.dataset.jornada+'_all').prop('checked', false); 
	   			toastr.success('Se ha actualizado la asistencia del Participante', 'Excelente!',{"positionClass": "toast-top-right"});
	   			refreshTableAsistentes();
	   			
	   		},
	   		error: function(err) {
	   			toastr.error('No se guardó la asistencia del Participante', 'Error!',{"positionClass": "toast-top-right"});
	   			element.checked=true;
	   			//$('#'+idJornada+'_'+idParticipante).prop('checked', true); 
	   		}
	   	});
	}
}
	 
function asistenciaGeneral(element){
	if(element.checked){
		$.ajax({
	   		headers: {"X-CSRF-TOKEN": token},
	   		type: "GET",
	   		contentType: "application/json; charset=utf-8",
	   		url: "/educacion-continua/attendance/general/check",
	   		data:{'id_jornada':element.dataset.jornada},
	   		cache: false,
	   		success: function(result) {
	   			toastr.success('Se han actualizado las asistencias de la Jornada', 'Excelente!',{"positionClass": "toast-top-right"});
	   			$('.j_'+element.dataset.jornada).prop('checked', true);  
	   			refreshTableAsistentes();
	   		},
	   		error: function(err) {
	   			console.log("entra aca");
	   			console.log(element.dataset.jornada+'_all');
	   			toastr.error('No se guardaron las asistencias de la Jornada', 'Error!',{"positionClass": "toast-top-right"});
	   			$('#'+element.dataset.jornada+'_all').prop('checked', false); 
	   		}
	   	});
	}else{
		$.ajax({
	   		headers: {"X-CSRF-TOKEN": token},
	   		type: "GET",
	   		contentType: "application/json; charset=utf-8",
	   		url: "/educacion-continua/attendance/general/uncheck",
	   		data:{'id_jornada':element.dataset.jornada},
	   		cache: false,
	   		success: function(result) {
	   			toastr.success('Se han actualizado las asistencias de la Jornada', 'Excelente!',{"positionClass": "toast-top-right"});
	   		 	$('.j_'+element.dataset.jornada).prop('checked', false); 
	   		    refreshTableAsistentes();
	   		},
	   		error: function(err) {
	   			console.log("entra en else");
	   			console.log(element.dataset.jornada+'_all');
	   			toastr.error('No se guardaron las asistencias de la Jornada', 'Error!',{"positionClass": "toast-top-right"});
	   			$('#'+element.dataset.jornada+'_all').prop('checked', true); 
	   		}
	   	});
	}
}
	  
	 
