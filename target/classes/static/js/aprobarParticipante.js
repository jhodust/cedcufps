$(document).ready(function ()
		{
	
	
	if(diploma != null && diploma.estructuraDiploma.objects.length > 0 ){
		canvas.loadFromJSON(diploma.estructuraDiploma);
	}
});

function aprobarAsistente(element){
	if(element.checked){
		generarDiploma(element.dataset,element);
	}else{
		deshacerDiploma(element.dataset,element);
	}
}

function generarDiploma(dataParticipante,elemento){
	updateInfoDiploma(dataParticipante.participante, dataParticipante.tipodocumento + ' ' + dataParticipante.documento, dataParticipante.tipoparticipante, canvas);
	var formData = new FormData();
	var imagen=canvas.toDataURL({
        format: 'jpg',
        quality: 0.8
    });
	
	var blob = dataURItoBlob(imagen);
	var file = new File([blob], 'certificado.jpg', {type: 'image/jpg'});
	formData.append('file',file);
	formData.append('tokenParticipante',dataParticipante.token);
	formData.append('idEduContinua',eduContinua.id);
	formData.append('documentoParticipante',dataParticipante.documento);
	$.ajax({
   		headers: {"X-CSRF-TOKEN": token},
   		type: "POST",
   		contentType: "application/json; charset=utf-8",
   		url: "/educacion-continua/certificarParticipacion",
   		data:formData,
   		enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
   		cache: false,
   		success: function(result) {
   			toastr.success('Se ha aprobado la participación exitosamente', 'Excelente!',{"positionClass": "toast-top-right", "preventDuplicates": true});
   			loadDiplomaStructureParticipantes(JSON.stringify(eduContinua.diploma.estructuraDiploma));  
   			document.getElementById('aCertificado_'+dataParticipante.token).classList.remove("action-item-disabled");
   			refreshTableAsistentes();
   		},
   		error: function(err) {
   			
   		}
   	});
   
}

function deshacerDiploma(dataParticipante,elemento){
	var formData=new FormData();
	formData.append('tokenParticipante',dataParticipante.token)
	$.ajax({
   		headers: {"X-CSRF-TOKEN": token},
   		type: "POST",
   		contentType: "application/json; charset=utf-8",
   		url: "/educacion-continua/cancelarCertificacionParticipacion",
   		data: formData,
   		enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
   		cache: false,
   		success: function(result) {
   			toastr.success('Se ha retirado la certificación exitosamente', 'Excelente!',{"positionClass": "toast-top-right","preventDuplicates": true});
   			document.getElementById('aCertificado_'+dataParticipante.token).classList.add("action-item-disabled");
   			refreshTableAsistentes();
   		},
   		error: function(err) {
   			
   		}
   	});
}

function updateStyleCells(classId,style){
	var elementos=document.getElementsByClassName(classId);
	Array.prototype.forEach.call(elementos, function(el) {
	    el.style.display=style;
	});
}



function refreshTableAsistentes(){
	var urlListado = '/educacion-continua/detalles/asistentes/'+eduContinua.idAcceso;
	$('#div_table_asistentes').load(urlListado);
	
}

function loadDiplomaStructureParticipantes(json){
	
	canvas.loadFromJSON(json);
}


