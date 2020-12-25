var token = $("meta[name='_csrf']").attr("content");
var datos_paises;
var datos_colombia;




$(document).ready(function ()
{
	comportamientoSelectsNacimiento();
	
	/***cada vez que el pais cambia a 'Colombia', se cargan los departamentos***/
	$('#selectPaisNacimiento').on('select2:select', function (e) { 
		var idPaisSeleccionado=e.params.data.id;
		if(idPaisSeleccionado=='170'){//id de Colombia
			mostrar('rowDeptoColombia','flex');
	   		mostrar('rowMpioColombia','flex');
		}else{
			$('#selectDepartamentoNacimiento').val('0').trigger('change');
			$('#selectMunicipioNacimiento').empty().trigger("change");
			$('#selectMunicipioNacimiento').val('0').trigger('change');
			
			ocultar('rowDeptoColombia');//div row del departamento y municipio
			ocultar('rowMpioColombia');
		}
	});
	
	/***cada vez que el depto cambia, se cargan los municipios asociados al select de municipios***/
	$('#selectDepartamentoNacimiento').on('select2:select', function (e) { 
		llenarSelectMunicipios(e.params.data.id);
	});
	
	/***establece el input mask dependiendo el programa asociado***/
	$('#programaAsociado').on('select2:select', function (e) { 
		$('#codigo').val("");
		if(e.params.data.id!="0"){
			$('#codigo').mask("0000000", {placeholder: "0000000"});
			/*$.ajax({
				headers: {"X-CSRF-TOKEN": token},
				type: "GET",
				contentType: "application/json; charset=utf-8",
				url: "/search/programa/"+e.params.data.id,
				cache: false,
				success: function(result) {
					console.log(result);
					$('#codigo').mask(result.codigo+"-0000", {placeholder: result.codigo+"-0000"});
				},
				error: function(err) {
					console.log(err);
					$('#codigo').mask("000-0000", {placeholder: "000-0000"});
				}
			});*/
		}
		
		});
	
});


/***funcion para cargar el select de paises***/
function cargarPaises(){
	/***ajax que carga el select del pais de nacimiento***/
	
	$.getJSON( "/data/paises.json", function( json )
	{       
    var datos_paises = json;
    var x = document.getElementById("selectPaisNacimiento");
    datos_paises.forEach(function(elemento){
    	var id=elemento.id;
        var newOption = new Option(elemento.pais, id, false, false);
        $('#selectPaisNacimiento').append(newOption);
    });
    
  //caso de editar usuario
    if(idPaisNac!=null){
    	$('#selectPaisNacimiento').val(idPaisNac).trigger('select');//esto se hace en caso de editar usuario ya que recibo las variables mediante un script en el form de camposPersona
	   if(idPaisNac=='170'){
		   mostrar('rowDeptoColombia','flex');
		   mostrar('rowMpioColombia','flex');
	   }
	   //termina caso de editar usuario
    }
  });
	
}


/***funcion para cargar el select de departamentos***/
function cargarDepartamentosColombia(){
	/***ajax que carga el select del departamento de nacimiento (solo colombia)***/
	var deptoTemp=null;
	$.getJSON( "/data/divipola.json", function( json )
	{       
		//console.log(json);
				datos_colombia=json;
			    datos_colombia.forEach(function(elemento){
			    	if(deptoTemp == null || deptoTemp!=elemento.dpto){
			    		deptoTemp=elemento.dpto;
			    		var newOption = new Option(elemento.dpto, elemento.cod_depto, false, false);
			        	$('#selectDepartamentoNacimiento').append(newOption);
			    	}
			        
			    });
			  //caso de editar usuario
			    if(idDepNac!=null){
			    	
				    $('#selectDepartamentoNacimiento').val(idDepNac).trigger('select');//esto se hace para editar usuario ya que recibo las variables mediante un script en el form de camposPersona
				    llenarSelectMunicipios(idDepNac);
				    $('#selectMunicipioNacimiento').val(idMunNac).trigger('select');
				    
			    }
			  //termina caso de editar usuario
			  });
	
}

/***funcion para cargar el select de municipios***/
function llenarSelectMunicipios(idDepartamento){
	 $('#selectMunicipioNacimiento').empty().trigger("change");
	   var arrayMunicipios= getFilteredByKey(datos_colombia,"cod_depto",idDepartamento);
	   arrayMunicipios.forEach(function(elemento){
	        var newOption = new Option(elemento.nom_mpio, elemento.cod_mpio, false, false);
	        $('#selectMunicipioNacimiento').append(newOption).trigger('change');
	    });
}

/***funcion que filtra un json de acuerdo a una propiedad(key) y un valor(value)***/
function getFilteredByKey(array, key, value) {
	  return array.filter(function(e) {
	    return e[key] == value;
	  });
	}


function comportamientoSelectsNacimiento(){
	cargarPaises();
	ocultar('rowDeptoColombia');//div row del departamento y municipio
	ocultar('rowMpioColombia');
	cargarDepartamentosColombia();
}

function ocultar(id) {// oculta un elemento del formulario
	document.getElementById(id).style.display = 'none';
}
function mostrar(id,style) {// muestra un elemento del formulario
	document.getElementById(id).style.display = style;
}


