var token = $("meta[name='_csrf']").attr("content");
var idEducacionContinua;
var idPonente;
$(document).ready(function ()
		{
				
			/*$.getJSON('http://localhost:8080/programas-academicos/listar', function(json) {
				console.log(json)
			});*/
			
	$('#modalPonentes').on('show.bs.modal', function (event) {
		limpiarForm();
		idEducacionContinua=null;
		limpiarErrores();
	});

});


function guardarPonente(){
	var tema = $('#temaPonente').val();
	var id_persona = document.getElementById("select_ponentes").value;
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
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(JSONponente),
		url: "/educacion-continua/ponente/save",
		cache: false,
		success: function(result) {
			toastr.success('Se ha guardado la información del ponente', 'Excelente!')
			limpiarForm();
			actualizarConsultaPonentes();
			console.log("el metodo guardarPonente deja idEDuContinua en: " +idEducacionContinua);
		},
		error: function(err) {
			toastr.error('No se pudo procesar la solicitud...', 'Error!');
			console.log(err);
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
		}
	});
	
	
}

function editarPonente(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/educacion-continua/ponente/search/"+elemento.dataset.id,
		cache: false,
		success: function(result) {
			$('#temaPonente').val(result.tema);
			 $('#select_ponentes').val(result.persona.id);
			 $('#select_ponentes').select2().trigger('change');
			 idPonente=result.id;
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
		
}

function eliminarPonente(elemento){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({'id':elemento.dataset.id}),
		url: "/educacion-continua/ponente/delete",
		cache: false,
		success: function(result) {
				console.log(result);
			 toastr.success('Se ha eliminado la información del ponente', 'Excelente!')
			 actualizarConsultaPonentes();
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
		
}

function ponentes(elemento){
	$('#modalPonentes').modal();
	idEducacionContinua=elemento.dataset.id;
	console.log("el metodo ponentes deja idEDuContinua en: " +idEducacionContinua);
	actualizarConsultaPonentes();
		
}

function actualizarConsultaPonentes(){
	console.log("el metodo actualizarConsultaPonentes tiene idEDuContinua en: " +idEducacionContinua);
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/educacion-continua/"+idEducacionContinua+"/ponentes",
		cache: false,
		success: function(result) {
			console.log(result);
			actualizarTablaPonentes(result);
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
}

	function limpiarForm(){
		$('#temaPonente').val("");
		$('#select_ponentes').val(0).trigger('change');
		idPonente=null;
	}
	
	function actualizarTablaPonentes(listaPonentes){
		var tabla=document.getElementById("tablaPonentes");
		limpiarDatosTabla(tabla);
		agregarDatosTabla(tabla,listaPonentes);
		
	}
	
	function limpiarDatosTabla(tabla){
		var tbody = tabla.getElementsByTagName('tbody')[0];
		for(var i=tbody.rows.length; i>0; i--){
			tbody.deleteRow(i-1);
		}
	}
	
	function agregarDatosTabla(tabla,lista){
		var tbody = tabla.getElementsByTagName('tbody')[0];
		console.log("------------------------------------");
		lista.forEach(function(ponente){
			console.log(ponente.id);
			var row = tbody.insertRow(0);

			// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);

			// Add some text to the new cells:
			cell1.innerHTML = ponente.persona.primerNombre + " " + ponente.persona.segundoNombre + " " + ponente.persona.primerApellido + " " + ponente.persona.segundoApellido;
			cell2.innerHTML = ponente.tema;
			var elementoEditar=crearElementoParaGestionar(ponente.id,'#', 'action-item', 'editarPonente(this)','Editar','far fa-edit');
			var elementoEliminar=crearElementoParaGestionar(ponente.id,'#','action-item text-danger', 'eliminarPonente(this)','Eliminar','far fa-trash-alt');
			cell3.appendChild(elementoEditar);
			cell3.appendChild(elementoEliminar);
			
			
		});
		console.log("------------------------------------");
	}
	
	function crearElementoParaGestionar(idPonente, href,classLink,onclick,titleTooltip,icon){
		var a = document.createElement("a"); 
		a.setAttribute('class', classLink);
		a.setAttribute('href', href);
		a.setAttribute('data-id', idPonente);
		a.setAttribute('onclick', onclick);
		a.setAttribute('data-toggle', 'tooltip');
		a.setAttribute('data-placement', 'bottom');
		a.setAttribute('title', titleTooltip);
		
		var i=document.createElement("i");
		i.setAttribute('class', icon);
		a.appendChild(i);
		$(a).tooltip();
		return a;
	}
	
	function limpiarErrores(){
		var selectPonente=document.getElementById('select_ponentes');
		var errorPonente=document.getElementById('errorPonente');
		errorPonente.innerText="";
		selectPonente.classList.remove("is-invalid");
	
		var inputTema=document.getElementById('temaPonente');
		var errorTema=document.getElementById('errorTema');
		errorTema.innerText="";
		inputTema.classList.remove("is-invalid");
		
	}