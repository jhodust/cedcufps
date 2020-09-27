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
		$('.save')
				.on(
						'click',
						function(e) {
							e.preventDefault();
							
							if($('#selectPaisNacimiento').val()=="170"){
								console.log("entra al if de pais");
								console.log($('#selectDepartamentoNacimiento').val());
								console.log($('#selectMunicipioNacimiento').val());
									if($('#selectDepartamentoNacimiento').val() == null || $('#selectMunicipioNacimiento').val() == null || $('#selectDepartamentoNacimiento').val() == "0" || $('#selectMunicipioNacimiento').val() == "0"){
										console.log("entra");
										toastr
													.error(
															'Debes seleccionar el departamento y municipio de nacimiento',
															'Error!');
															return;
									}
								}
								
								
							if(document.getElementById("cbEst").checked){
									if($('#programaAsociado').val() == "0" || $('#codigo').val() == ""){
										toastr
													.error(
															'Debes diligenciar la información academica como estudiante',
															'Error!');
															return;
									}
								
								}
								if(document.getElementById("cbDoc").checked){
									if($('#selectDeptoAdscrito').val() == "0" || $('#codDocente').val() == ""){
										toastr
													.error(
															'Debes diligenciar la información academica como docente',
															'Error!');
															return;
									}
								
								}
								if(document.getElementById("cbAdminvo").checked){
									if($('#dependencia').val() == "" || $('#cargo').val() == ""){
										toastr
													.error(
															'Debes diligenciar la información academica como administrativo',
															'Error!');
															return;
									}
								
								}
								if(document.getElementById("cbGraduado").checked){
								console.log("entra a validar graduado");
									if($('#selectProgramaGraduado').val() == "0" || $('#anioGraduado').val() == ""){
										toastr
													.error(
															'Debes diligenciar la información como graduado',
															'Error!');
															return;
									}else{
									 var date=new Date();
									 console.log(date.getFullYear());
									 console.log($('#anioGraduado').val());
									 if($('#anioGraduado').val()>date.getFullYear()){
									 	toastr
													.error(
															'El año de graduación ingresado es superior al actual',
															'Error!');
															return;
									 }
									}
								
								}
								if(document.getElementById("cbExt").checked){
									if($('#profesion').val() == "" || $('#empresa').val() == ""){
										toastr
													.error(
															'Debes diligenciar la información laboral',
															'Error!');
															return;
									}
								
								}
								
							if($('#selectTipoDoc').val() == "0" || $('#numDoc').val() == "" || $('#fechaExp').val() == "" 
							|| $('#primNom').val() == "" || $('#segNom').val() == "" || $('#primApe').val() == "" 
							|| $('#segApe').val() == "" || $('input[name="genero"]:checked').val() == null || 
							$('input[name="estadoCivil"]:checked').val() == null 
							|| $('#selectPaisNacimiento').val() == "0" || $('#fechaNac').val() == "" || $('#email').val() == "" 
							|| $('#telefono').val() == "" || (!document.getElementById("cbEst").checked && !document.getElementById("cbDoc").checked
							&& !document.getElementById("cbAdminvo").checked && !document.getElementById("cbGraduado").checked && !document.getElementById("cbExt").checked)){
							
								
								
								
								toastr
													.error(
															'Diligencie el formulario correctamente, algunos campos son requeridos',
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
							userJSON.profesion = $('#codigo').val();
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
																'Se ha registrado el usuario exitosamente',
																'Excelente!');
											
											}
											window.setTimeout(function() {
													window.location.href = url;
												}, 1000);
										},
										error : function(err) {
											toastr
													.error(
															'No se pudo procesar la solicitud...',
															'Error!');
											console.log(err);
											
										}
									});
						});
			});
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
			ocultar('formExterno');
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
