$(document).ready(function ()
		{
	console.log("EDUUUUUUUUUUUUU CONTINUAAAAAAAAAAAAAAAAA EN ASISTENTES");
	console.log(eduContinua);
	hideCellsTableAsistentes();
	
	
	if(diploma != null && diploma.estructuraDiploma.objects.length > 0 ){
		canvasParticipante.loadFromJSON(diploma.estructuraDiploma);
	}
});

$('.swAprobarAsistente').on('click',function(e){
	e.preventDefault();
	console.log("click en aprobar asistente");
	console.log(e);
	var habilitar;
	if(e.target.checked){
		console.log("se habilita");
		habilitar=true;
		generarDiploma(e.target.dataset,habilitar,e);
	}else{
		console.log("se deshabilita");
		habilitar=false;
		deshacerDiploma(e.target.dataset,habilitar,e);
	}
});



function generarDiploma(dataParticipante, boolean,elemento){
	updateInfoDiploma(dataParticipante.participante, dataParticipante.tipodocumento + ' ' + dataParticipante.documento, dataParticipante.tipoparticipante, canvasParticipante);
	var formData = new FormData();
	var imagen=canvasParticipante.toDataURL({
        format: 'jpg',
        quality: 0.8
    });
	console.log(imagen);
	var blob = dataURItoBlob(imagen);
	console.log("blobbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
	console.log(blob);
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
   			console.log(elemento);
   			elemento.target.checked=boolean; 
   			document.getElementById('aCertificado_'+dataParticipante.token).classList.remove("action-item-disabled");
   		},
   		error: function(err) {
   			
   		}
   	});
   
}

function deshacerDiploma(dataParticipante, boolean,elemento){
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
   			console.log(elemento);
   			elemento.target.checked=boolean; 
   			document.getElementById('aCertificado_'+dataParticipante.token).classList.add("action-item-disabled");
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

function hideCellsTableAsistentes(){
	console.log("hide cells table asistentes");
	console.log(eduContinua.enableAsistencia);
	if(!eduContinua.enableAsistencia || eduContinua.diploma==null){
		ocultar('headerAprobado');
		ocultar('headerImgCertificado');
		updateStyleCells('swAprobado','none');
		updateStyleCells('tdImgCertificados','none');
	}
	
}

function showCellsTableAsistentes(){
	console.log("DIPLOMMMMMMMMMMMAAAAAAAAAA EN SHOW CELLS");
	console.log(eduContinua.diploma);
	console.log(eduContinua.enableAsistencia);
	if(eduContinua.enableAsistencia && eduContinua.diploma!=null){
		mostrar('headerAprobado','');
		mostrar('headerImgCertificado','');
		updateStyleCells('swAprobado','');
		updateStyleCells('tdImgCertificados','');
		
	}
	try{
		document.getElementById("msgCertificado").remove();
	}catch(error){
		
	}
	
}

function loadDiplomaStructureParticipantes(json){
	console.log("LOADDDDDDDDDDD JSOSNNNNNNNNNNN PARTICIPANTE");
	console.log(json);
	canvasParticipante.loadFromJSON(json);
}


