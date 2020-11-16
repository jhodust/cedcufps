var msjRequerido='El campo es requerido';
	var msjPorcentaje='Debe ser un valor entre 0 y 100';
	var msjNumeroPositivo='Debe ser un valor mayor a 0';
	var msjSelect= 'Debe seleccionar una opción';

function validateInputTextRequerido(idInput, idError){
 if(document.getElementById(idInput).value == ""){
		document.getElementById(idInput).classList.add("is-invalid");
		document.getElementById(idError).innerText=msjRequerido;
	}else{
		cleanError(idInput,idError);
	}
}

function validateInputNumberRequerido(idInput, idError){
		if(document.getElementById(idInput).value == ""){
				document.getElementById(idInput).classList.add("is-invalid");
				document.getElementById(idError).innerText=msjRequerido;
			}else{
				if(document.getElementById(idInput).value <= 0 && document.getElementById(idInput).value!=""){
					document.getElementById(idInput).classList.add("is-invalid");
					document.getElementById(idError).innerText=msjNumeroPositivo;
				}else{
					cleanError(idInput,idError);
				}
			}
	}

function validateInputNumberNotRequired(idInput, idError){
	if(document.getElementById(idInput).value <= 0 && document.getElementById(idInput).value!=""){
		document.getElementById(idInput).classList.add("is-invalid");
		document.getElementById(idError).innerText=msjNumeroPositivo;
	}else{
		cleanError(idInput,idError);
	}
}

function validateInputPorcentaje(idInput, idError){
	if(document.getElementById(idInput).value == ""){
		document.getElementById(idInput).classList.add("is-invalid");
		document.getElementById(idError).innerText=msjRequerido;
	}else{
		if(document.getElementById(idInput).value>100 	|| document.getElementById(idInput).value<0 ){
			document.getElementById(idInput).classList.add("is-invalid");
			document.getElementById(idError).innerText=msjPorcentaje;
		}else{
			cleanError(idInput,idError);
		}
		
	}
}

function validateSelect(idSelect, idError){
	//console.log('validateSelect');
	//console.log(document.getElementById(idSelect).value);
	if(document.getElementById(idSelect).value == 0){
		document.getElementById(idSelect).classList.add("is-invalid");
		document.getElementById(idError).innerText=msjSelect;
	}else{
		cleanError(idSelect,idError);
	}
}

function validateCheckboxGenero(idError){
	if($('input[name="genero"]:checked').val() == null ){
		document.getElementById(idError).innerText=msjRequerido;
	}else{
		cleanMsj(idError);
	}
}

function validateCheckboxEstadoCivil(idError){
	if($('input[name="estadoCivil"]:checked').val() == null ){
		document.getElementById(idError).innerText=msjRequerido;
	}else{
		cleanMsj(idError);
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
	}else{
		cleanMsj(idError);
	}
}

function cleanMsj(idError){
	document.getElementById(idError).innerText='';
}

function cleanError(idCampo,idError){
	document.getElementById(idCampo).classList.remove("is-invalid");
	document.getElementById(idError).innerText='';
}