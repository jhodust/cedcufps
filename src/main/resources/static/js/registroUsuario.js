$(document).ready(function ()
			{
			var url=null;
			var id=0;
			if(caso==0){
				id=0;
				url="/";
			}else{
			console.log("id persona en caso 1");
			console.log(persona.id);
				if(persona.id==null){
					id=0;
				}else{
					id=persona.id;
				}
				
				url="/usuarios";
			}
		/*ocultar('formEstudiante');
		ocultar('formDocente');
		ocultar('formAdministrativo');
		ocultar('formGraduado');
		ocultar('formExterno');
		*/
			$('#selectTipoDoc').on("change", function (e) { 
				e.preventDefault();
				validateSelect('selectTipoDoc','errorTipoDoc');
				
			});
			
			$('#selectPaisNacimiento').on("change", function (e) { 
				console.log("entra a change pais");
				e.preventDefault();
				validateSelect('selectPaisNacimiento','errorPaisNacimiento');
				
			});
			$('#selectDepartamentoNacimiento').on("change", function (e) { 
				e.preventDefault();
				validateSelect('selectDepartamentoNacimiento','errorDeptoNacimiento');
				
			});
			$('#selectMunicipioNacimiento').on("change", function (e) { 
				e.preventDefault();
				validateSelect('selectMunicipioNacimiento','errorMpioNacimiento');
				
			});
			$('#programaAsociado').on("change", function (e) { 
				e.preventDefault();
				validateSelect('programaAsociado','errorProgramaEstudiante');
				
			});
			$('#selectDeptoAdscrito').on("change", function (e) { 
				e.preventDefault();
				validateSelect('selectDeptoAdscrito','errorDeptoDocente');
				
			});
			$('#selectProgramaGraduado').on("change", function (e) { 
				e.preventDefault();
				validateSelect('selectProgramaGraduado','errorProgramaGraduado');
				
			});
			$('input[name="genero"]').click(function() {
				cleanMsj('errorGenero');
			});
			$('input[name="estadoCivil"]').click(function() {
				cleanMsj('errorEstadoCivil');
			});
			
			$("#cbEst").click(function() {
				validateCheckboxPerfiles('cbEst','cbDoc','cbAdminvo','cbGraduado','cbExt','errorPerfiles');
			});
			$("#cbDoc").click(function() {
				validateCheckboxPerfiles('cbEst','cbDoc','cbAdminvo','cbGraduado','cbExt','errorPerfiles');
			});
			$("#cbAdminvo").click(function() {
				validateCheckboxPerfiles('cbEst','cbDoc','cbAdminvo','cbGraduado','cbExt','errorPerfiles');
			});
			$("#cbGraduado").click(function() {
				validateCheckboxPerfiles('cbEst','cbDoc','cbAdminvo','cbGraduado','cbExt','errorPerfiles');
			});
			$("#cbExt").click(function() {
				validateCheckboxPerfiles('cbEst','cbDoc','cbAdminvo','cbGraduado','cbExt','errorPerfiles');
			});
			
		$('.save')
				.on(
						'click',
						function(e) {
							e.preventDefault();
							
							showLoader();
							ajaxSaveUsuario(id,url);
			});
			});
	function showLoader(){
		document.getElementById("divLoader").style.display='flex';
		document.getElementById("formUsuario").style.display='none';
	}
	
	function hideLoader(){
		document.getElementById("divLoader").style.display='none';
		document.getElementById("formUsuario").style.display='inline';
	}
	function ajaxSaveUsuario(id,url){
		var anyError=false;
		
		
		var valid1=validateSelect('selectTipoDoc','errorTipoDoc');
		var valid2=validateInputTextRequerido('numDoc','errorNumeroDocumento');
		var valid3=validateInputTextRequerido('fechaExp','errorFechaExp');
		var valid4=validateInputTextRequerido('primNom','errorPrimerNombre');
		var valid5=validateInputTextRequerido('primApe','errorPrimerApellido');
		//var valid6=validateInputTextRequerido('segApe','errorSegundoApellido');
		var valid7=validateInputTextRequerido('fechaNac','errorFechaNacimiento');
		var valid8=validateCheckboxGenero('errorGenero');
		var valid9=validateCheckboxEstadoCivil('errorEstadoCivil');
		var valid10=validateInputTextRequerido('email','errorEmail');
		var valid0= validateEmail('email','errorEmail');
		var valid11=validateInputNumberRequerido('telefono','errorTelefono');
		var valid12=validateCheckboxPerfiles('cbEst','cbDoc','cbAdminvo','cbGraduado','cbExt','errorPerfiles');
		var valid13=validateSelect('selectPaisNacimiento','errorPaisNacimiento');
		var valid50=validateLengthTxt('primNom','errorPrimerNombre',20);
		var valid51=validateLengthTxt('segNom','errorSegundoNombre',20);
		var valid52=validateLengthTxt('primApe','errorPrimerApellido',20);
		var valid53=validateLengthTxt('segApe','errorSegundoApellido',20);
		var valid54=validateLengthTxt('direccion','errorDireccion',50);
			
		console.log("imprimiendo validaciones");
		console.log(valid1);
		console.log(valid2);
		console.log(valid3);
		console.log(valid4);
		console.log(valid5);
		//console.log(valid6);
		console.log(valid7);
		console.log(valid8);
		console.log(valid9);
		console.log(valid10);
		console.log(valid11);
		console.log(valid12);
		console.log(valid13);
		console.log(valid50);
		console.log(valid51);
		console.log(valid52);
		console.log(valid53);
		console.log(valid54);
			if(!valid1 || !valid2 || !valid3 || !valid4 || !valid5  || !valid7
					|| !valid8 || !valid9 || !valid10 || !valid0 || !valid11 || !valid12 || !valid13
					|| !valid50 || !valid51 || !valid52 || !valid53 || !valid54){
			
				anyError=true;
			
		}
			if($('#selectPaisNacimiento').val().trim()=="170"){
				console.log("entra al if de pais");
				console.log($('#selectDepartamentoNacimiento').val());
				console.log($('#selectMunicipioNacimiento').val());
				var valid14=validateSelect('selectDepartamentoNacimiento','errorDeptoNacimiento');
				var valid15=validateSelect('selectMunicipioNacimiento','errorMpioNacimiento');
				console.log("entra a validar municipios por el pais")
				console.log(valid14);
				console.log(valid15);
				if(!valid14 || !valid15){
						console.log("entra");
						anyError=true;
						
					}
				}
			
			if(document.getElementById("cbEst").checked){
				console.log("entra a validacion cbEst");
				var valid16=validateSelect('programaAsociado','errorProgramaEstudiante');
				var valid17=validateInputNumberRequerido('codigo','errorCodigoEstudiante');
				console.log("entra a validar check estudiante")
				console.log(valid16);
				console.log(valid17);
				if(!valid16 || !valid17){
					anyError=true;
						
					}
				
				}
				if(document.getElementById("cbDoc").checked){
					console.log("entra a validacion cbDoc");
					var valid18=validateSelect('selectDeptoAdscrito','errorDeptoDocente');
					var valid19=validateInputNumberRequerido('codDocente','errorCodigoDocente');
					console.log("entra a validar check docente")
					console.log(valid18);
					console.log(valid19);
					if(!valid18 || !valid19){
						anyError=true;
						
					}
				
				}
				if(document.getElementById("cbAdminvo").checked){
					console.log("entra a validacion cbAdminvo");
					var valid20=validateInputTextRequerido('dependencia','errorDependenciaAdminvo');
					var valid21=validateInputTextRequerido('cargo','errorCargoAdminvo');
					var valid55=validateLengthTxt('dependencia','errorDependenciaAdminvo',50);
					var valid56=validateLengthTxt('cargo','errorCargoAdminvo',50);
					console.log("entra a validar check adminvo")
					console.log(valid20);
					console.log(valid21);
					console.log(valid55);
					console.log(valid56);
					if(!valid20 || !valid21 || !valid55 || !valid56){
						anyError=true;
						
					}
				
				}
				if(document.getElementById("cbGraduado").checked){
					console.log("entra a validacion cbGraduado");
					var valid20=validateSelect('selectProgramaGraduado','errorProgramaGraduado');
					var valid21=validateAnio('anioGraduado','errorAnioGraduado');
					console.log("entra a validar check graduado")
					console.log(valid20);
					console.log(valid21);
					if(!valid20 || !valid21){
						anyError=true;
						
					}
				
				}
				if(document.getElementById("cbExt").checked){
					console.log("entra a validacion cbExt");
					var valid22=validateInputTextRequerido('profesion','errorProfesionExterno');
					var valid23=validateInputTextRequerido('empresa','errorEmpresa');
					var valid57=validateLengthTxt('profesion','errorProfesionExterno',50);
					var valid58=validateLengthTxt('empresa','errorEmpresa',50);
					console.log("entra a validar check externo")
					console.log(valid22);
					console.log(valid23);
					console.log(valid57);
					console.log(valid58);
					if(!valid22 || !valid23 || !valid57 || !valid58){
						anyError=true;
						
					}
				
				}
		
		
			if(anyError){
				hideLoader();
				toastr
				.error(
						'Debes diligenciar el formulario correctamente',
						'Error!');
						return;
			}
		
		console.log();
		var userJSON = {};
		userJSON.id=id;
		userJSON.tipoDocumento = $('#selectTipoDoc').val();
		userJSON.numeroDocumento = $('#numDoc').val();
		var partsDateED =$('#fechaExp').val().split('/');
		userJSON.fechaExpedicionDocumento = new Date(partsDateED[2], partsDateED[1] - 1, partsDateED[0]); 
		userJSON.primerNombre = $('#primNom').val();
		userJSON.segundoNombre = $('#segNom').val();
		userJSON.primerApellido = $('#primApe').val();
		userJSON.segundoApellido = $('#segApe').val();
		userJSON.genero = $('input[name="genero"]:checked')
				.val();
		userJSON.estadoCivil = $(
				'input[name="estadoCivil"]:checked').val();
		var partsDateFN =$('#fechaNac').val().split('/');
		userJSON.fechaNacimiento = new Date(partsDateFN[2], partsDateFN[1] - 1, partsDateFN[0]); 
		userJSON.idPaisNacimiento = $(
				'#selectPaisNacimiento').val();
		userJSON.idDepartamentoNacimiento = $(
				'#selectDepartamentoNacimiento').val();
		userJSON.idMunicipioNacimiento = $(
				'#selectMunicipioNacimiento').val();
		userJSON.email = $('#email').val();
		userJSON.direccion = $('#direccion').val();
		userJSON.telefono = $('#telefono').val();
		userJSON.codigo = $('#codigo').val();
		userJSON.programa = $('#programaAsociado').val();
		userJSON.profesion = $('#profesion').val();
		userJSON.estudiante=document.getElementById("cbEst").checked;
		userJSON.docente=document.getElementById("cbDoc").checked;
		userJSON.administrativo=document.getElementById("cbAdminvo").checked;
		userJSON.graduado=document.getElementById("cbGraduado").checked;
		userJSON.externo=document.getElementById("cbExt").checked;
		userJSON.programaGraduado=$('#selectProgramaGraduado').val();
		userJSON.anioGraduado=$('#anioGraduado').val();
		userJSON.deptoAdscrito=$('#selectDeptoAdscrito').val();
		userJSON.codigoDocente=$('#codDocente').val();
		userJSON.dependencia=$('#dependencia').val();
		userJSON.cargo=$('#cargo').val();
		userJSON.empresa=$('#empresa').val();
		if(id!=0 && persona.docente){
			userJSON.estadoDocente=document.getElementById("switchEstadoDocente").checked;
		}else if(userJSON.docente){
			userJSON.estadoDocente=true;
		}else{
			userJSON.estadoDocente=false;
		}
		
		console.log(userJSON);
		$
				.ajax({
					headers : {
						"X-CSRF-TOKEN" : token
					},
					type : "POST",
					contentType : "application/json; charset=utf-8",
					data : JSON.stringify(userJSON),//variable idEducacionContinua la recibo de un script al final del index de jornadas
					url : "/registrarse",
					cache : false,
					success : function(result) {
						if(caso==0){
						
							toastr
									.success(
											'Se ha creado su usuario, inicie Sesión para acceder',
											'Excelente!')
							
						}else{
							toastr
									.success(
											'Se ha guardado el usuario exitosamente',
											'Excelente!');
						
						}
						window.setTimeout(function() {
								window.location.href = url;
							}, 1000);
					},
					error : function(err) {
						hideLoader();
						toastr
								.error(
										err.responseJSON.message,
										'Error!');
						console.log(err);
						
					}
				});
	}
	function estudiante() {
		mostrar('divAdicional','block');
		var cbEst = document.getElementById("cbEst");
		if (cbEst.checked == true) {
			mostrar('formEstudiante','block');
			ocultar('formExterno');
			$("#cbExt").prop("checked", false);
		} else {
			ocultar('formEstudiante');
		}
	}

	function docente() {
		mostrar('divAdicional','block');
		var cbDoc = document.getElementById("cbDoc");
		if (cbDoc.checked == true) {
			mostrar('formDocente','block');
			ocultar('formExterno');
			$("#cbExt").prop("checked", false);
		} else {
			ocultar('formDocente');
		}
	}

	function adminvo() {
		mostrar('divAdicional','block');
		var cbAdmin = document.getElementById("cbAdminvo");
		if (cbAdmin.checked == true) {
			mostrar('formAdministrativo','block');
			ocultar('formExterno');
			$("#cbExt").prop("checked", false);
		} else {
			ocultar('formAdministrativo');
		}
	}

	function graduado() {
		mostrar('divAdicional','block');
		var cbGrad = document.getElementById("cbGraduado");
		if (cbGrad.checked == true) {
			mostrar('formGraduado','block');
			ocultar('formExterno');
			$("#cbExt").prop("checked", false);
		} else {
			ocultar('formGraduado');
		}
	}

	function externo() {
		mostrar('divAdicional','block');
		var cbEx = document.getElementById("cbExt");
		if (cbEx.checked == true) {
			$("#cbEst").prop("checked", false);
			$("#cbDoc").prop("checked", false);
			$("#cbAdminvo").prop("checked", false);
			$("#cbGraduado").prop("checked", false);
			mostrar('formExterno','block');
			ocultar('formEstudiante','block');
			ocultar('formDocente','block');
			ocultar('formAdministrativo','block');
			ocultar('formGraduado','block');

		} else {
			ocultar('divAdicional');
		}
	}
	
	
	function loadUsuario(){
		if(id!=null){
			$('#selectTipoDoc').val();
			$('#numDoc').val();
			$('#fechaExp').val();
			$('#primNom').val();
			$('#segNom').val();
			$('#primApe').val();
			$('#segApe').val();
			$('#fechaNac').val();
			$('#selectPaisNacimiento').val();
			$('#selectDepartamentoNacimiento').val();
			$('#selectMunicipioNacimiento').val();
			$('#email').val();
			$('#telefono').val();
			$('#direccion').val();
			$('#selectTipoDoc').val();
			$('#selectTipoDoc').val();
		}
	}
