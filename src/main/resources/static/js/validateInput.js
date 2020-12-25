var msjRequerido='El campo es requerido';
	var msjPorcentaje='Debe ser un valor entre 0 y 100';
	var msjNumeroPositivo='Debe ser un valor mayor a 0';
	var msjSelect= 'Debe seleccionar una opción';
var msjEmail='El email ingresado es inválido';
var msjDesbordamiento= 'El campo supera la cantidad de caracteres permitidos';

function validateEmail(idInput, idError){
	var email = document.getElementById(idInput).value.trim();
	if(email!=''){
		var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!regex.test(email)){
			document.getElementById(idInput).classList.add("is-invalid");
			document.getElementById(idError).innerText=msjEmail;
			return false;
		}else{
			cleanError(idInput,idError);
			return true;
		}
	    
	}
	 
}
function validateInputTextRequerido(idInput, idError){
 if(document.getElementById(idInput).value.trim() == ""){
	 console.log("entra a vacio input text");
		document.getElementById(idInput).classList.add("is-invalid");
		document.getElementById(idError).innerText=msjRequerido;
		return false;
	}else{
		console.log("limpia input text requerido");
		cleanError(idInput,idError);
		return true;
	}
}

function validateInputNumberRequerido(idInput, idError){
		if(document.getElementById(idInput).value.trim() == ""){
				document.getElementById(idInput).classList.add("is-invalid");
				document.getElementById(idError).innerText=msjRequerido;
				return false;
			}else{
				if(document.getElementById(idInput).value <= 0 && document.getElementById(idInput).value!=""){
					document.getElementById(idInput).classList.add("is-invalid");
					document.getElementById(idError).innerText=msjNumeroPositivo;
					return false;
				}else{
					cleanError(idInput,idError);
					return true;
				}
			}
	}

function validateInputNumberNotRequired(idInput, idError){
	if(document.getElementById(idInput).value <= 0 && document.getElementById(idInput).value.trim()!=""){
		document.getElementById(idInput).classList.add("is-invalid");
		document.getElementById(idError).innerText=msjNumeroPositivo;
		return false;
	}else{
		cleanError(idInput,idError);
		return true;
	}
}

function validateInputPorcentaje(idInput, idError){
	if(document.getElementById(idInput).value.trim() == ""){
		document.getElementById(idInput).classList.add("is-invalid");
		document.getElementById(idError).innerText=msjRequerido;
		return false;
	}else{
		if(document.getElementById(idInput).value>100 	|| document.getElementById(idInput).value<0 ){
			document.getElementById(idInput).classList.add("is-invalid");
			document.getElementById(idError).innerText=msjPorcentaje;
			return false;
		}else{
			cleanError(idInput,idError);
			return true;
		}
		
	}
}

function validateSelect(idSelect, idError){
	//console.log('validateSelect');
	//console.log(document.getElementById(idSelect).value);
	if(document.getElementById(idSelect).value == 0){
		document.getElementById(idSelect).classList.add("is-invalid");
		document.getElementById(idError).innerText=msjSelect;
		return false;
	}else{
		cleanError(idSelect,idError);
		return true;
	}
}

function validateCheckboxGenero(idError){
	if($('input[name="genero"]:checked').val() == null ){
		document.getElementById(idError).innerText=msjRequerido;
		return false;
	}else{
		cleanMsj(idError);
		return true;
	}
}

function validateCheckboxEstadoCivil(idError){
	if($('input[name="estadoCivil"]:checked').val() == null ){
		document.getElementById(idError).innerText=msjRequerido;
		return false;
	}else{
		cleanMsj(idError);
		return true;
	}
}

function validateCheckboxPerfiles(idCheckbox1,idCheckbox2,idCheckbox3,idCheckbox4,idCheckbox5,idError){
	console.log(document.getElementById(idCheckbox1).checked);
	console.log(document.getElementById(idCheckbox2).checked);
	console.log(document.getElementById(idCheckbox3).checked);
	console.log(document.getElementById(idCheckbox4).checked);
	console.log(document.getElementById(idCheckbox5).checked);
	if(!document.getElementById(idCheckbox1).checked && !document.getElementById(idCheckbox2).checked &&
			!document.getElementById(idCheckbox3).checked && !document.getElementById(idCheckbox4).checked &&
			!document.getElementById(idCheckbox5).checked){
		document.getElementById(idError).innerText=msjRequerido;
		ocultar('divAdicional');
		return false;
	}else{
		cleanMsj(idError);
		return true;
	}
}

function cleanMsj(idError){
	document.getElementById(idError).innerText='';
}

function cleanError(idCampo,idError){
	document.getElementById(idCampo).classList.remove("is-invalid");
	document.getElementById(idError).innerText='';
}

function validateLengthTxt(idInput, idError, cantidadCaracteres){
	 if(document.getElementById(idInput).value != undefined && document.getElementById(idInput).value.trim() != ""){
		 if(document.getElementById(idInput).value.trim().length > cantidadCaracteres){
			 document.getElementById(idInput).classList.add("is-invalid");
				document.getElementById(idError).innerText=msjDesbordamiento + '('+cantidadCaracteres+')';
				return false;
		 }else{
			
			cleanError(idInput,idError);
			return true;
		}
	}
	 return true;
}