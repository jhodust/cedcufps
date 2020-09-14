$(document).ready(function ()
			{
			var url=null;
			var id=null;
			if(caso==0){
				id=null;
				url="/";
			}else{
				id=persona.id;
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
							//userJSON.isEstudiante=document.getElementById("cbEst").checked ? 1:0;
							//userJSON.isDocente=document.getElementById("cbDoc").checked ? 1:0;
							//userJSON.isAdministrativo=document.getElementById("cbAdminvo").checked ? 1:0;
							//userJSON.isGraduado=document.getElementById("cbGraduado").checked ? 1:0;
							//userJSON.isExterno=document.getElementById("cbExt").checked ? 1:0;
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
							if(id!=null && persona.docente){
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
											/*window.setTimeout(function() {
													window.location.href = url;
												}, 1000);*/
										},
										error : function(err) {
											toastr
													.error(
															'No se pudo procesar la solicitud...',
															'Error!');
											console.log(err);
											/*err.responseJSON.forEach(function(error){
												if(error.field=="horaInicio"){
													var inputHoraInicio=document.getElementById('horaInicio');
													var errorHoraInicio=document.getElementById('errorHoraInicio');
													errorHoraInicio.innerText=error.defaultMessage;
													inputHoraInicio.classList.add("is-invalid");
												}
												if(error.field=="horaFin"){
													var inputHoraFin=document.getElementById('horaFin');
													var errorHoraFin=document.getElementById('errorHoraFin');
													errorHoraFin.innerText=error.defaultMessage;
													inputHoraFin.classList.add("is-invalid");
												}
											  });*/
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
