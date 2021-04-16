$(document).ready(function ()
{
	

$('#selectTipoContinuaPanel').on("change", function (e) { 
		e.preventDefault();
		var idTipoEdC=$(this).find('option:selected').val();
		var idPrograma=$('#selectProgramaPanel').val();
		var idPublico=$('#selectPublicoPanel').val();
		var baseUri=convertUri("/reload",idTipoEdC,idPrograma,idPublico);
		paginadorLoadAjax(baseUri);
		
		
	});

$('#selectProgramaPanel').on("change", function (e) { 
	e.preventDefault();
	var idTipoEdC=$('#selectTipoContinuaPanel').val();
	var idPrograma=$(this).find('option:selected').val();
	var idPublico=$('#selectPublicoPanel').val();
	var baseUri=convertUri("/reload",idTipoEdC,idPrograma,idPublico);
	paginadorLoadAjax(baseUri);
	
	
});

$('#selectPublicoPanel').on("change", function (e) { 
	e.preventDefault();
	var idTipoEdC=$('#selectTipoContinuaPanel').val();
	var idPrograma=$('#selectProgramaPanel').val();
	var idPublico=$(this).find('option:selected').val();
	var baseUri=convertUri("/reload",idTipoEdC,idPrograma,idPublico);
	paginadorLoadAjax(baseUri);
	
	
});

});

function convertUri(baseUri,idTipoEdC,idPrograma,idPublico){
	var anyAttrib=false;
	if(idTipoEdC!=0){
		anyAttrib=true;
		baseUri=baseUri.concat('?idTipoEdC=',idTipoEdC);
	}
	
	if(idPrograma!=0 && idTipoEdC==0){
		anyAttrib=true;
		baseUri=baseUri.concat('?idPrograma=',idPrograma);
	}else if(idPrograma!=0 && idTipoEdC!=0){
		anyAttrib=true;
		baseUri=baseUri.concat('&idPrograma=',idPrograma);
	}
	
	if(idPublico!=0 && idTipoEdC==0 && idPrograma==0){
		anyAttrib=true;
		baseUri=baseUri.concat('?idPublico=',idPublico);
	}else if(idPublico!=0 && (idTipoEdC==0 || idPrograma==0)){
		anyAttrib=true;
		baseUri=baseUri.concat('&idPublico=',idPublico);
	}else if(idPublico!=0){
		anyAttrib=true;
		baseUri=baseUri.concat('&idPublico=',idPublico);
	}
	if(anyAttrib){
		baseUri=baseUri.concat('&baseUri=',baseUri);
	}else{
		baseUri=baseUri.concat('?baseUri=',baseUri);
	}
	
	return baseUri;
}