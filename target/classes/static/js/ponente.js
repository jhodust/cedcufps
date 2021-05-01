var token = $("meta[name='_csrf']").attr("content");
var idPonente;
var id_persona;
$(document).ready(function ()
		{
				
			
			
	$('#modalPonentes').on('show.bs.modal', function (event) {
		limpiarForm();
		limpiarErrores();
		
	});
	 
	
});


function guardarPonente(){
	var tema = $('#temaPonente').val();
	limpiarErrores();
	var JSONponente={};
	JSONponente.id=idPonente;
	JSONponente.tema=tema;
	JSONponente.educacionContinua={};
	JSONponente.educacionContinua.id=idEducacionContinua;
	JSONponente.tipoParticipante={};
	JSONponente.tipoParticipante.id="2";
	if(id_persona != "0"){
		JSONponente.persona={};
		JSONponente.persona.id=id_persona;
	}
	showSpinnerModal("btnSavePonente","btnSpinnerPonente");
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(JSONponente),
		url: "/educacion-continua/ponentes/save",
		cache: false,
		success: function(result) {
			if(result != null && result != ""){
				
				creacionTarjetaInscripcion(result);
			}
			toastr.success('Se ha guardado el ponente', 'Excelente!');
			$('#modalPonentes').modal('hide');
			//window.setTimeout(function(){location.reload()},1000);
			//actualizarConsultaPonentes();
			//reloadListPonentes();
			hideSpinnerModal("btnSavePonente","btnSpinnerPonente");
			refreshTableAsistentes();
		},
		error: function(err) {
			hideSpinnerModal("btnSavePonente","btnSpinnerPonente");
			if(err.responseJSON.length >0){
				toastr.error('No se pudo procesar la solicitud...', 'Error!');
				err.responseJSON.forEach(function(error){
					if(error.field=="persona"){
						var selectPonente=document.getElementById('select_ponentes');
						var errorPonente=document.getElementById('errorPonente');
						errorPonente.innerText=error.defaultMessage;
						selectPonente.classList.add("is-invalid");
					}
					if(error.field=="tema"){
						var inputTema=document.getElementById('temaPonente');
						var errorTema=document.getElementById('errorTema');
						errorTema.innerText=error.defaultMessage;
						inputTema.classList.add("is-invalid");
					}
				  });
			  
			}else{
				toastr.error(err.responseJSON.message, 'Error!');
				
			}
		}
	});
	
	
}

function editarPonente(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/educacion-continua/ponentes/search/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			$('#modalPonentes').modal();
			$('#temaPonente').val(result.tema);
			$('#tdPonente').val(result.participante.tipoDocumento);
			$('#documentoPonente').val(result.participante.numeroDocumento);
			$('#nombrePonente').val(result.participante.nombrePersona);
			 idPonente=result.participante.id;
			 id_persona=result.participante.idPersona;
			 
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
		
}

function eliminarPonente(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		data: {id:elemento.dataset.id},
		url: "/educacion-continua/ponentes/delete",
		cache: false,
		success: function(result) {
			 toastr.success(result, 'Excelente!');
			 reloadListPonentes();
			 //window.setTimeout(function(){location.reload()},1000);
		},
		error: function(err) {
			toastr.error(err.responseJSON.message, 'Error!');
		}
	});
		
}


function searchPosiblePonente(){
	var value=$('#valueBusqueda').val();
	var tipoBusqueda=$('#selectTipoBusqueda').val();
	if(value=="" || tipoBusqueda==0){
		toastr.error('Diligencie los filtros de búsqueda correctamente', 'Error!');
		return;
	}
	document.getElementById('tablaPonentes').style.display='inline';
	table =$('#table2').DataTable( {
		destroy: true,
		lengthMenu: [5],
		language: {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
		ajax: {
	    	contentType: "application/json; charset=utf-8",
	    	url: "/educacion-continua/ponentes/filtro",
	    	type: "GET",
	    	data: {tipo_busqueda:tipoBusqueda,value:value}
	    	
	    },
	    columns: [{
            data: "documento"
        },
        {
            data: "tipoDocumento"
        },
        {
            data: "nombre"
        },
        {
        	data: "id",
            render: function(data) {
                return '<button type="button" class="btn btn-sm btn-rojoufps btn-icon-only" onclick="agregarPonente(this)" data-id="'+data+'" data-toggle="tooltip" data-placement="right" title="Agregar Ponente">'+
                '<span class="btn-inner--icon">'+
		        '<i class="far fa-user"></i>'+
		    '</span>'+
		'</button>';
            }
        }
    ],
    drawCallback: function( settings ) {
    	$('[data-toggle="tooltip"]').tooltip();
    }
	} );
		
}


	function limpiarForm(){
		$('#temaPonente').val("");
		$('#select_ponentes').val(0).trigger('change');
		$('#tpPonente').val("");
		$('#documentoPonente').val("");
		$('#nombrePonente').val("");
		idPonente=null;
		$("#select_ponentes").prop("disabled", false);
	}
	
	function agregarPonente(element){
		$.ajax({
			headers: {"X-CSRF-TOKEN": token},
			type: "GET",
			contentType: "application/json; charset=utf-8",
			url: "/educacion-continua/ponentes/search/persona",
			data: {id:element.dataset.id},
			cache: false,
			success: function(result) {
				$('#modalPonentes').modal('show');
				$('#tdPonente').val(result.tipoDocumento);
				$('#documentoPonente').val(result.documento);
				$('#nombrePonente').val(result.nombre);
				id_persona=result.id;
				idPonente=0;
				
			},
			error: function(err) {
				$("#msg").html( "<span style='color: red'>Programa is required</span>" );
			}
		});
		
	}
	
	function limpiarErrores(){
		
	
		var inputTema=document.getElementById('temaPonente');
		var errorTema=document.getElementById('errorTema');
		errorTema.innerText="";
		inputTema.classList.remove("is-invalid");
		
	}
	
	function reloadListPonentes(){
		var urlReload = '/educacion-continua/ponentes/'+idEducacionContinua;
		$('#div_list_ponentes').load(urlReload);
		var urlReloadDetalles = '/educacion-continua/ponentes/detalles/'+idEducacionContinua;
		$('#div_list_detalles_ponentes').load(urlReloadDetalles);
	}