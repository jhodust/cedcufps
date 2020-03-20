var token = $("meta[name='_csrf']").attr("content");
var datos_paises;
var datos_colombia;
toastr.options = {
		  "closeButton": true,
		  "debug": false,
		  "newestOnTop": false,
		  "progressBar": false,
		  "positionClass": "toast-bottom-right",
		  "preventDuplicates": false,
		  "onclick": null,
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "5000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
		}

$(document).ready(function() {
	manejoFormsPersona();
	indicarCodigoPrograma();
	
});

$(document).ready(function ()
{
	console.log("preparando datepicker");
	$("#fechaInicioEduCont").flatpickr({
	    "plugins": [new rangePlugin({ input: "#fechaFinEduCont"})]
	});
	
	
	
	console.log("antes");
	$.getJSON( "/data/countries_iso3166.json", function( json )
		  
  {       
    var datos_paises = json;
    var x = document.getElementById("selectPaisNacimiento");
    datos_paises.forEach(function(elemento){
        //var id=elemento.pais+"-"+elemento.id;;
    	var id=elemento.id;;
        var newOption = new Option(elemento.pais, id, false, false);
        $('#selectPaisNacimiento').append(newOption).trigger('change');
    })
  });
	
	
	$.getJSON( "https://www.datos.gov.co/resource/gdxc-w37w.json", function( json )
			  
			  {       
		console.log(json);
				datos_colombia=json;
			    datos_colombia.forEach(function(elemento){
			        var newOption = new Option(elemento.dpto, elemento.cod_depto, false, false);
			        $('#selectDepartamentoNacimiento').append(newOption).trigger('change');
			    })
			  });
	
	$('#selectDepartamentoNacimiento').on('select2:select', function (e) { 
	   $('#selectMunicipioNacimiento').empty().trigger("change");
	   var arrayMunicipios= getFilteredByKey(datos_colombia,"cod_depto",e.params.data.id);
	   arrayMunicipios.forEach(function(elemento){
	        var newOption = new Option(elemento.nom_mpio, elemento.cod_mpio, false, false);
	        $('#selectMunicipioNacimiento').append(newOption).trigger('change');
	    })
	});
	
	
	
	
	
	
	 
	
});

function indicarCodigoPrograma(){// carga el codigo del programa antes del
									// codigo de estudiante en form de registro
	$("#select_programa").on("change", function() {
		console.log(this.value);
		$("#codigo_programa").val(this.value);
	});
}
function manejoFormsPersona(){// mmuestra el formulario de la persona segun
								// sea el tipo de persona
	ocultar("campos_estudiante");
	ocultar("campos_docente");
	ocultar("campos_administrativo");
	ocultar("campos_externo");
	$("#select_tipo_usuario").on("change", function() {
		console.log(this.value);
		if(this.value=="Estudiante"){
			mostrar("campos_estudiante");
			ocultar("campos_docente");
			ocultar("campos_administrativo");
			ocultar("campos_externo");
		}else if(this.value=="Docente"){
			ocultar("campos_estudiante");
			mostrar("campos_docente");
			ocultar("campos_administrativo");
			ocultar("campos_externo");
		}else if(this.value=="Administrativo"){
			ocultar("campos_estudiante");
			ocultar("campos_docente");
			mostrar("campos_administrativo");
			ocultar("campos_externo");
		}else if(this.value=="Externo"){
			ocultar("campos_estudiante");
			ocultar("campos_docente");
			ocultar("campos_administrativo");
			mostrar("campos_externo");
		}
	});
}

function ocultar(id) {// oculta un elemento del formulario
	document.getElementById(id).style.display = 'none';
}
function mostrar(id) {// muestra un elemento del formulario
	document.getElementById(id).style.display = 'block';
}

function logout(){
	document.getElementById('formLogout').submit();
}

function getFilteredByKey(array, key, value) {
	  return array.filter(function(e) {
	    return e[key] == value;
	  });
	}


