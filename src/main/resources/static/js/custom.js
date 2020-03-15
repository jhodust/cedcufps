var token = $("meta[name='_csrf']").attr("content");
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
	
	console.log("antes");
	$.getJSON( "../data/countries_iso3166.json", function( json )
		  
  {       
	 
    var datos = json;
    var x = document.getElementById("selectMunicipioNacimiento");
    datos.forEach(function(elemento){
        var id=elemento.pais+"-"+elemento.id;;
        var newOption = new Option(elemento.pais, id, false, false);
        $('#selectMunicipioNacimiento').append(newOption).trigger('change');
    })
  });
	$.getJSON( "https://www.datos.gov.co/resource/gdxc-w37w.json", function( json )
			  
			  {       
				var datos_municipios=json;
			    var x = document.getElementById("selectMunicipioNacimiento2");
			    $("#selectMunicipioNacimiento").select2();
			    $("#selectMunicipioNacimiento2").select2();
			    datos_municipios.forEach(function(elemento){
			    	var id=elemento.nom_mpio+"-"+elemento.cod_mpio;
			        var newOption = new Option(elemento.nom_mpio, id, false, false);
			        $('#selectMunicipioNacimiento2').append(newOption).trigger('change');
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
