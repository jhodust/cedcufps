$(document).ready(function ()
		{
	
	var canvasParticipante = new fabric.Canvas('canvasDiplomaParticipante',{
		renderOnAddRemove:true,
		canvasOriginalWidth: 800,
	    canvasOriginalHeight: 600,
	    canvasWidth: 800,
	    canvasHeight: 600,
	});
	
	
	$('.linkCertificado').on('click',function(e){
		e.preventDefault();
		var tokenParticipante=e.currentTarget.dataset.token;
		$.ajax({
	   		headers: {"X-CSRF-TOKEN": token},
	   		type: "GET",
	   		contentType: "application/json; charset=utf-8",
	   		url: "/certificaciones-educacion-continua/search-certificacion",
	   		data: {'token':tokenParticipante },
	   		cache: false,
	   		success: function(result) {
	   			if(result.updateDiploma){
	   				canvasParticipante.loadFromJSON(result.diplomaDto.estructuraDiploma, function(o, object) {
	   					updateInfoDiploma(result.participanteDto.nombrePersona, result.participanteDto.numeroDocumento,
			   					result.participanteDto.tipoParticipante, canvasParticipante);
	   					ajaxGenerarDiploma(result.participanteDto.token, result.participanteDto.idEducacionContinua,
			   					result.participanteDto.numeroDocumento, result.participanteDto.diplomaParticipacion,canvasParticipante);
		   			});
	   				
		   			
	   			}else{
	   				verDiploma(tokenParticipante);
	   			}
	   			
	   		},
	   		error: function(err) {
	   			
	   		}
	   	});
	});
});




function ajaxGenerarDiploma(tokenParticipante, idEduContinua, documentoParticipante, filename, canvasObject){
	var formData = new FormData();
	var imagen=canvasObject.toDataURL({
        format: 'jpg',
        quality: 0.8
    });
	var blob = dataURItoBlob(imagen);
	var file = new File([blob], 'certificado.jpg', {type: 'image/jpg'});
	formData.append('file',file);
	formData.append('filename',filename);
	formData.append('tokenParticipante',tokenParticipante);
	formData.append('idEduContinua',idEduContinua);
	formData.append('documentoParticipante',documentoParticipante);
	$.ajax({
   		headers: {"X-CSRF-TOKEN": token},
   		type: "POST",
   		contentType: "application/json; charset=utf-8",
   		url: "/certificaciones-educacion-continua/actualizarCertificado",
   		data:formData,
   		enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
   		cache: false,
   		success: function(result) {
   			verDiploma(tokenParticipante);
   		},
   		error: function(err) {
   			
   		}
   	});
   
}

function verDiploma(tokenParticipante){
	var a = document.createElement('a');
		a.href = "/certificaciones-educacion-continua/visualizar-diploma?token="+tokenParticipante;
		window.open(a.href,"_blank");
}

