$(document).ready(function() {
	
	manejoFormsPersona();
	indicarCodigoPrograma();
	
});

function indicarCodigoPrograma(){//carga el codigo del programa antes del codigo de estudiante en form de registro
	$("#select_programa").on("change", function() {
		console.log(this.value);
		$("#codigo_programa").val(this.value);
	});
}
function manejoFormsPersona(){//mmuestra el formulario de la persona segun sea el tipo de persona
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