$( document ).ready(function() {
	  
	  console.log(persona);
	  var idsEduCForPrograma= [];
	  var eduCForPrograma= persona.programasForEduContinua;
	  eduCForPrograma.forEach(element => idsEduCForPrograma.push(element.id));

	  console.log(idsEduCForPrograma);
	  $('#programasEC').val(idsEduCForPrograma);
	  $('#programasEC').trigger('change');
	  
	  var idsProgramaForEst= [];
	  var prograForEst=persona.programasForEstudiantes;
	  prograForEst.forEach(element => idsProgramaForEst.push(element.id));

	  console.log(idsProgramaForEst);
	  $('#programasEstudiantes').val(idsProgramaForEst);
	  $('#programasEstudiantes').trigger('change');
	  
	  var idsProgramaForGrad= [];
	  var prograForGradu= persona.programasForGraduados;
	  prograForGradu.forEach(element => idsProgramaForGrad.push(element.id));

	  console.log(idsProgramaForGrad);
	  $('#programasGraduados').val(idsProgramaForGrad);
	  $('#programasGraduados').trigger('change');
	  
	  var idsDeptoForDocentes= [];
	  var deptoForDoc= persona.deptosForDocentes;
	  deptoForDoc.forEach(element => idsDeptoForDocentes.push(element.id));

	  console.log(idsDeptoForDocentes);
	  $('#deptosDocentes').val(idsDeptoForDocentes);
	  $('#deptosDocentes').trigger('change');
	  
	  var idsEduCForAtten= [];
	  var eduCForAtten= persona.eduContinuasForAttendance;
	  eduCForAtten.forEach(element => idsEduCForAtten.push(element.id));

	  console.log(idsEduCForAtten);
	  $('#educacionesContinuas').val(idsEduCForAtten);
	  $('#educacionesContinuas').trigger('change');
	  
	  
	validadorErroresPermisos();
	  
	  $('#guardarPermisos').click(function(){
	  	actualizarPermisos()
	  });
	});
  
 
  function actualizarPermisos(){
	  var token = $("meta[name='_csrf']").attr("content");
	 	var idsProgramasEdC=$("#programasEC").val();
		var idsProgramasPerEst=$("#programasEstudiantes").val();
		var idsProgramasPerGra=$("#programasGraduados").val();
		var idsDeptosDoc=$("#deptosDocentes").val();
		var idsEduAtt=$("#educacionesContinuas").val();
		var switchEdC=document.getElementById("switchEduContinua");
		var switchP=document.getElementById("switchPersonas");
		var switchA=document.getElementById("switchAsistencias");
		var cbAdminvo=document.getElementById("cbAdminvo");
		var cbE=document.getElementById("cbE");
		
		if(persona.programasForEduContinuaIntocables != null){
			persona.programasForEduContinuaIntocables.forEach(element => idsProgramasEdC.push(element.id));
		}
		if(persona.programasForEstudiantesIntocables != null){
			persona.programasForEstudiantesIntocables.forEach(element => idsProgramasPerEst.push(element.id));
		}
		if(persona.programasForGraduadosIntocables != null){
			persona.programasForGraduadosIntocables.forEach(element => idsProgramasPerGra.push(element.id));
		}
		if(persona.deptosForDocentesIntocables != null){
			persona.deptosForDocentesIntocables.forEach(element => idsDeptosDoc.push(element.id));
		}
		if(persona.eduContinuasForAttendanceIntocables != null){
			persona.eduContinuasForAttendanceIntocables.forEach(element => idsEduAtt.push(element.id));
		}
		
		
		
		
		
		
		
		console.log(idsProgramasEdC);
		console.log(idsProgramasPerEst);
		console.log(idsProgramasPerGra);
		console.log(idsDeptosDoc);
		console.log(idsEduAtt);
		console.log(switchEdC.checked);
		console.log(switchP.checked);
		console.log(switchA.checked);
		console.log(cbAdminvo.checked);
		console.log(cbE.checked);
		if(persona.docente){
			if((switchP.checked && idsProgramasPerEst.length==0 && 
				idsDeptosDoc.length==0 && idsProgramasPerGra.length==0 && !cbAdminvo.checked && !cbE.checked) || 
				(switchA.checked && idsEduAtt.length==0 )){
					toastr.error('Por favor, diligencie el formulario correctamente', 'Error!');
					return;
				}
		}else{
			if((switchEdC.checked && idsProgramasEdC.length==0 ) || (switchP.checked && idsProgramasPerEst.length==0 && 
			idsDeptosDoc.length==0 && idsProgramasPerGra.length==0 && !cbAdminvo.checked && !cbE.checked) || 
			(switchA.checked && idsEduAtt.length==0 )){
				toastr.error('Por favor, diligencie el formulario correctamente', 'Error!');
				return;
			}
		}
			$.ajax({
			headers: {"X-CSRF-TOKEN": token},
			type: "GET",
			contentType: "application/json; charset=utf-8",
			data: {"idPersona":persona.idPersona,"permisosEdC":switchEdC.checked, "permisosPersonas":switchP.checked, 
				"permisosAttendance":switchA.checked,"idsPEdC":idsProgramasEdC,"idsPEs":idsProgramasPerEst,
				"idsDD":idsDeptosDoc,"idsPG":idsProgramasPerGra,"permisoAdminvo":cbAdminvo.checked,
				"permisoExterno":cbE.checked,"idsEdCA":idsEduAtt,"isDirPrograma":persona.dirPrograma,"isDoc":persona.docente,"idProgramaDirector":persona.idProgramaDirector},
			url: "/update-roles",
			cache: false,
			success: function(result) {
				
				toastr.success('Se han actualizado los roles a la persona', 'Excelente!');
				 
				
			},
			error: function(err) {
				console.log(err);
				toastr.error(err.responseJSON.message, 'Error!');
			}
		});
		
		
		
	}
	
	
	function validadorErroresPermisos(){
		$("#switchEduContinua").click(function() {
	  	if(document.getElementById("switchEduContinua").checked){
	  	console.log($("#programasEC").val().length);
	  		if($("#programasEC").val().length==0){
	  		console.log("no hay educaciones");
	  		console.log(persona);
	  			if(persona.docente){
	  				console.log("entra");
		  			document.getElementById("ePEC").style.display='none';
		  			document.getElementById("ePEC").innerText="";
		  			
		  			document.getElementById("wPEC").innerText="Solo podrá gestionar las educaciones continuas en las que el docente sea responsable";
		  			document.getElementById("wPEC").style.display='block';
	  			}else{
	  				console.log("entra");
		  			document.getElementById("ePEC").style.display='block';
		  			document.getElementById("ePEC").innerText="Debes seleccionar como mínimo un Programa Académico al cual se va a gestionar las educaciones continuas";
		  			
		  			document.getElementById("wPEC").innerText="";
		  			document.getElementById("wPEC").style.display='none';
	  			}
	  			
	  			
	  		}else{
	  			document.getElementById("ePEC").innerText="";
	  			document.getElementById("ePEC").style.display='none';
	  			document.getElementById("wPEC").innerText="";
	  			document.getElementById("wPEC").style.display='none';
	  			
	  		}
	  	}else{
	  		if($("#programasEC").val().length==0){
	  			document.getElementById("wPEC").style.display='none';
	  			document.getElementById("wPEC").innerText="";
	  			document.getElementById("ePEC").innerText="";
	  			document.getElementById("ePEC").style.display='none';
	  			
	  			
	  			
	  		}else{
	  			document.getElementById("ePEC").innerText="";
	  			document.getElementById("ePEC").style.display='none';
	  			document.getElementById("wPEC").innerText="Debes activar el estado del permiso para que sea válido el registro";
	  			document.getElementById("wPEC").style.display='block';
	  			
	  		}
	  	}
	  	
	  });
	  
	  $('#programasEC').on('select2:select', function (e) { 
		if($("#programasEC").val().length==0){
			if(document.getElementById("switchEduContinua").checked){
				document.getElementById("ePEC").style.display='block';
				document.getElementById("ePEC").innerText="Debes seleccionar como mínimo un Programa Académico al cual se va a gestionar las educaciones continuas";
				document.getElementById("wPEC").innerText="";
				document.getElementById("wPEC").style.display='none';
				
			}else{
				document.getElementById("ePEC").innerText="";
				document.getElementById("ePEC").style.display='none';
				document.getElementById("wPEC").innerText="";
				document.getElementById("wPEC").style.display='none';
				
			}
		
		}else{
			if(document.getElementById("switchEduContinua").checked){
				document.getElementById("ePEC").innerText="";
				document.getElementById("ePEC").style.display='none';
				document.getElementById("wPEC").innerText="";
				document.getElementById("wPEC").style.display='none';
				
			}else{
				document.getElementById("wPEC").style.display='block';
				document.getElementById("wPEC").innerText="Debes activar el estado del permiso para que sea válido el registro";
				document.getElementById("ePEC").innerText="";
				document.getElementById("ePEC").style.display='none';
				
			}
		}
	});
	
	$('#programasEC').on('select2:unselect', function (e) { 
	console.log("entra a ca");
	console.log($("#programasEC").val());
		if($("#programasEC").val().length==0){
			if(document.getElementById("switchEduContinua").checked){
				document.getElementById("ePEC").style.display='block';
				document.getElementById("ePEC").innerText="Debes seleccionar como mínimo un Programa Académico al cual se va a gestionar las educaciones continuas";
				document.getElementById("wPEC").innerText="";
				document.getElementById("wPEC").style.display='none';
				
			}else{
				document.getElementById("ePEC").innerText="";
				document.getElementById("ePEC").style.display='none';
				document.getElementById("wPEC").innerText="";
				document.getElementById("wPEC").style.display='none';
				
			}
		
		}else{
			if(document.getElementById("switchEduContinua").checked){
				document.getElementById("ePEC").innerText="";
				document.getElementById("ePEC").style.display='none';
				document.getElementById("wPEC").innerText="";
				document.getElementById("wPEC").style.display='none';
				
			}else{
				document.getElementById("wPEC").style.display='block';
				document.getElementById("wPEC").innerText="Debes activar el estado del permiso para que sea válido el registro";
				document.getElementById("ePEC").innerText="";
				document.getElementById("ePEC").style.display='none';
				
			}
			
		}
	});
	
	$("#switchPersonas").click(function() {
	  	if(document.getElementById("switchPersonas").checked){
	  	console.log($("#programasEC").val().length);
	  		if($("#programasEstudiantes").val().length==0 && $("#deptosDocentes").val().length==0 && $("#programasGraduados").val().length==0 && !document.getElementById("cbAdminvo").checked && !document.getElementById("cbE").checked){
	  		console.log("no hay items para personas");
	  		console.log(persona);
	  			
  				console.log("entra");
	  			document.getElementById("ePP").style.display='block';
	  			document.getElementById("ePP").innerText="Debes seleccionar como mínimo alguno de los aspectos que se encuentran a continuación";
	  			
	  			document.getElementById("wPP").innerText="";
	  			document.getElementById("wPP").style.display='none';
	  			
	  			
	  			
	  		}else{
	  			document.getElementById("ePP").innerText="";
	  			document.getElementById("ePP").style.display='none';
	  			document.getElementById("wPP").innerText="";
	  			document.getElementById("wPP").style.display='none';
	  			
	  		}
	  	}else{
	  		if($("#programasEstudiantes").val().length==0 && $("#deptosDocentes").val().length==0 && $("#programasGraduados").val().length==0 && !cbAdminvo.checked && !cbE.checked){
	  			document.getElementById("wPP").style.display='none';
	  			document.getElementById("wPP").innerText="";
	  			document.getElementById("ePP").innerText="";
	  			document.getElementById("ePP").style.display='none';
	  			
	  			
	  			
	  		}else{
	  			document.getElementById("ePP").innerText="";
	  			document.getElementById("ePP").style.display='none';
	  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
	  			document.getElementById("wPP").style.display='block';
	  			
	  		}
	  	}
	  	
	  });
	
	$("#cbAdminvo").click(function() {
	  	if(document.getElementById("cbAdminvo").checked){
	  		if(document.getElementById("switchPersonas").checked){
	  		
	  			
		  		document.getElementById("ePP").innerText="";
	  			document.getElementById("ePP").style.display='none';
	  			document.getElementById("wPP").innerText="";
	  			document.getElementById("wPP").style.display='none';
	  			
	  			
	  			
	  		}else{
	  			document.getElementById("ePP").innerText="";
	  			document.getElementById("ePP").style.display='none';
	  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
	  			document.getElementById("wPP").style.display='block';
	  			
	  		}
	  	}else{
	  		if(document.getElementById("switchPersonas").checked){
	  			if($("#programasEstudiantes").val().length==0 && 
	  				$("#deptosDocentes").val().length==0 && $("#programasGraduados").val().length==0 && 
	  				!document.getElementById("cbE").checked){
	  				document.getElementById("ePP").style.display='block';
		  			document.getElementById("ePP").innerText="Debes seleccionar como mínimo alguno de los aspectos que se encuentran a continuación";
		  			
		  			document.getElementById("wPP").innerText="";
		  			document.getElementById("wPP").style.display='none';
	  			}else{
	  				document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="";
		  			document.getElementById("wPP").style.display='none';
	  			}
	  		}else{
	  			if($("#programasEstudiantes").val().length==0 && 
		  				$("#deptosDocentes").val().length==0 && $("#programasGraduados").val().length==0 && 
		  				!document.getElementById("cbE").checked){
		  				document.getElementById("ePP").innerText="";
			  			document.getElementById("ePP").style.display='none';
			  			document.getElementById("wPP").innerText="";
			  			document.getElementById("wPP").style.display='none';
		  			}else{
		  				document.getElementById("ePP").innerText="";
			  			document.getElementById("ePP").style.display='none';
			  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
			  			document.getElementById("wPP").style.display='block';
		  			}
	  			
	  		}
	  	}
	  	
	  });
	
	$("#cbE").click(function() {
	  	if(document.getElementById("cbE").checked){
	  		if(document.getElementById("switchPersonas").checked){
	  		
	  			
		  		document.getElementById("ePP").innerText="";
	  			document.getElementById("ePP").style.display='none';
	  			document.getElementById("wPP").innerText="";
	  			document.getElementById("wPP").style.display='none';
	  			
	  			
	  			
	  		}else{
	  			document.getElementById("ePP").innerText="";
	  			document.getElementById("ePP").style.display='none';
	  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
	  			document.getElementById("wPP").style.display='block';
	  			
	  		}
	  	}else{
	  		if(document.getElementById("switchPersonas").checked){
	  			if($("#programasEstudiantes").val().length==0 && 
	  				$("#deptosDocentes").val().length==0 && $("#programasGraduados").val().length==0 && 
	  				!document.getElementById("cbAdminvo").checked){
	  				document.getElementById("ePP").style.display='block';
		  			document.getElementById("ePP").innerText="Debes seleccionar como mínimo alguno de los aspectos que se encuentran a continuación";
		  			
		  			document.getElementById("wPP").innerText="";
		  			document.getElementById("wPP").style.display='none';
	  			}else{
	  				document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="";
		  			document.getElementById("wPP").style.display='none';
	  			}
	  		}else{
	  			if($("#programasEstudiantes").val().length==0 && 
		  				$("#deptosDocentes").val().length==0 && $("#programasGraduados").val().length==0 && 
		  				!document.getElementById("cbAdminvo").checked){
		  				document.getElementById("ePP").innerText="";
			  			document.getElementById("ePP").style.display='none';
			  			document.getElementById("wPP").innerText="";
			  			document.getElementById("wPP").style.display='none';
		  			}else{
		  				document.getElementById("ePP").innerText="";
			  			document.getElementById("ePP").style.display='none';
			  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
			  			document.getElementById("wPP").style.display='block';
		  			}
	  			
	  		}
	  	}
	  	
	  });
	
	 $('#programasEstudiantes').on('select2:select', function (e) { 
			if($("#programasEstudiantes").val().length>0){
				if(document.getElementById("switchPersonas").checked){
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="";
		  			document.getElementById("wPP").style.display='none';
					
				}else{
					
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
		  			document.getElementById("wPP").style.display='block';
				}
					
			
			}
		});
	 
	 $('#programasEstudiantes').on('select2:unselect', function (e) { 
			if($("#programasEstudiantes").val().length==0){
				if(document.getElementById("switchPersonas").checked){
					if($("#deptosDocentes").val().length==0 && $("#programasGraduados").val().length==0 
							&& !document.getElementById("cbAdminvo").checked && !document.getElementById("cbE").checked){
						document.getElementById("ePP").style.display='block';
			  			document.getElementById("ePP").innerText="Debes seleccionar como mínimo alguno de los aspectos que se encuentran a continuación";
			  			
			  			document.getElementById("wPP").innerText="";
			  			document.getElementById("wPP").style.display='none';
					
					}else{
						document.getElementById("ePP").innerText="";
			  			document.getElementById("ePP").style.display='none';
			  			document.getElementById("wPP").innerText="";
			  			document.getElementById("wPP").style.display='none';
					}
					
					
				}
					
			
			}else{
				if($("#deptosDocentes").val().length==0 && $("#programasGraduados").val().length==0 
						&& !document.getElementById("cbAdminvo").checked && !document.getElementById("cbE").checked){
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="";
		  			document.getElementById("wPP").style.display='none';
				
				}else{
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
		  			document.getElementById("wPP").style.display='block';
				}
			}
		});
	 
	 $('#deptosDocentes').on('select2:select', function (e) { 
			if($("#deptosDocentes").val().length>0){
				if(document.getElementById("switchPersonas").checked){
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="";
		  			document.getElementById("wPP").style.display='none';
					
				}else{
					
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
		  			document.getElementById("wPP").style.display='block';
				}
					
			
			}
		});
	 
	 $('#deptosDocentes').on('select2:unselect', function (e) { 
			if($("#deptosDocentes").val().length==0){
				if(document.getElementById("switchPersonas").checked){
					if($("#programasEstudiantes").val().length==0 && $("#programasGraduados").val().length==0 
							&& !document.getElementById("cbAdminvo").checked && !document.getElementById("cbE").checked){
						document.getElementById("ePP").style.display='block';
			  			document.getElementById("ePP").innerText="Debes seleccionar como mínimo alguno de los aspectos que se encuentran a continuación";
			  			
			  			document.getElementById("wPP").innerText="";
			  			document.getElementById("wPP").style.display='none';
					
					}else{
						document.getElementById("ePP").innerText="";
			  			document.getElementById("ePP").style.display='none';
			  			document.getElementById("wPP").innerText="";
			  			document.getElementById("wPP").style.display='none';
					}
					
					
				}
					
			
			}else{
				if($("#programasEstudiantes").val().length==0 && $("#programasGraduados").val().length==0 
						&& !document.getElementById("cbAdminvo").checked && !document.getElementById("cbE").checked){
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="";
		  			document.getElementById("wPP").style.display='none';
				
				}else{
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
		  			document.getElementById("wPP").style.display='block';
				}
			}
		});
	 
	 $('#programasGraduados').on('select2:select', function (e) { 
			if($("#programasGraduados").val().length>0){
				if(document.getElementById("switchPersonas").checked){
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="";
		  			document.getElementById("wPP").style.display='none';
					
				}else{
					
					document.getElementById("ePP").innerText="";
		  			document.getElementById("ePP").style.display='none';
		  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
		  			document.getElementById("wPP").style.display='block';
				}
					
			
			}
		});
	 
	 $('#programasGraduados').on('select2:unselect', function (e) { 
			if($("#programasGraduados").val().length==0){
				if(document.getElementById("switchPersonas").checked){
					if($("#deptosDocentes").val().length==0 && $("#programasEstudiantes").val().length==0 
							&& !document.getElementById("cbAdminvo").checked && !document.getElementById("cbE").checked){
						document.getElementById("ePP").style.display='block';
			  			document.getElementById("ePP").innerText="Debes seleccionar como mínimo alguno de los aspectos que se encuentran a continuación";
			  			
			  			document.getElementById("wPP").innerText="";
			  			document.getElementById("wPP").style.display='none';
					
					}else{
						document.getElementById("ePP").innerText="";
			  			document.getElementById("ePP").style.display='none';
			  			document.getElementById("wPP").innerText="";
			  			document.getElementById("wPP").style.display='none';
					}
					
					
				}else{
					if($("#deptosDocentes").val().length==0 && $("#programasEstudiantes").val().length==0 
							&& !document.getElementById("cbAdminvo").checked && !document.getElementById("cbE").checked){
						document.getElementById("ePP").innerText="";
			  			document.getElementById("ePP").style.display='none';
			  			document.getElementById("wPP").innerText="";
			  			document.getElementById("wPP").style.display='none';
					
					}else{
						document.getElementById("ePP").innerText="";
			  			document.getElementById("ePP").style.display='none';
			  			document.getElementById("wPP").innerText="Debes activar el estado del permiso para que sea válido el registro";
			  			document.getElementById("wPP").style.display='block';
					}
				}
					
			
			}
		});
	
	$("#switchAsistencias").click(function() {
	  	if(document.getElementById("switchAsistencias").checked){
	  	console.log($("#educacionesContinuas").val().length);
	  		if($("#educacionesContinuas").val().length==0){
	  			console.log("entra");
	  			document.getElementById("eA").style.display='block';
	  			document.getElementById("eA").innerText="Debes seleccionar como mínimo una educación continua al cual se va a gestionar la asistencia mediante la app"
	  			
	  			document.getElementById("wA").innerText="";
	  			document.getElementById("wA").style.display='none';
	  			
	  		}else{
	  			document.getElementById("eA").innerText="";
	  			document.getElementById("eA").style.display='none';
	  			document.getElementById("wA").innerText="";
	  			document.getElementById("wA").style.display='none';
	  			
	  		}
	  	}else{
	  		if($("#educacionesContinuas").val().length==0){
	  			document.getElementById("wA").style.display='none';
	  			document.getElementById("wA").innerText="";
	  			document.getElementById("eA").innerText="";
	  			document.getElementById("eA").style.display='none';
	  			
	  			
	  			
	  		}else{
	  			document.getElementById("eA").innerText="";
	  			document.getElementById("eA").style.display='none';
	  			document.getElementById("wA").innerText="Debes activar el estado del permiso para que sea válido el registro";
	  			document.getElementById("wA").style.display='block';
	  			
	  		}
	  	}
	  	
	  });
	  
	  $('#educacionesContinuas').on('select2:select', function (e) { 
		if($("#educacionesContinuas").val().length==0){
			if(document.getElementById("switchAsistencias").checked){
				document.getElementById("eA").style.display='block';
				document.getElementById("eA").innerText="Debes seleccionar como mínimo una educación continua al cual se va a gestionar la asistencia mediante la app"
				document.getElementById("wA").innerText="";
				document.getElementById("wA").style.display='none';
				
			}else{
				document.getElementById("eA").innerText="";
				document.getElementById("eA").style.display='none';
				document.getElementById("wA").innerText="";
				document.getElementById("wA").style.display='none';
				
			}
		
		}else{
			if(document.getElementById("switchAsistencias").checked){
				document.getElementById("eA").innerText="";
				document.getElementById("eA").style.display='none';
				document.getElementById("wA").innerText="";
				document.getElementById("wA").style.display='none';
				
			}else{
				document.getElementById("wA").style.display='block';
				document.getElementById("wA").innerText="Debes activar el estado del permiso para que sea válido el registro";
				document.getElementById("eA").innerText="";
				document.getElementById("eA").style.display='none';
				
			}
		}
	});
	
	$('#educacionesContinuas').on('select2:unselect', function (e) { 
	console.log("entra a ca");
	console.log($("#educacionesContinuas").val());
		if($("#educacionesContinuas").val().length==0){
			if(document.getElementById("switchAsistencias").checked){
				document.getElementById("eA").style.display='block';
				document.getElementById("eA").innerText="Debes seleccionar como mínimo una educación continua al cual se va a gestionar la asistencia mediante la app";
				document.getElementById("wA").innerText="";
				document.getElementById("wA").style.display='none';
				
			}else{
				document.getElementById("eA").innerText="";
				document.getElementById("eA").style.display='none';
				document.getElementById("wA").innerText="";
				document.getElementById("wA").style.display='none';
				
			}
		
		}else{
			if(document.getElementById("switchAsistencias").checked){
				document.getElementById("eA").innerText="";
				document.getElementById("eA").style.display='none';
				document.getElementById("wA").innerText="";
				document.getElementById("wA").style.display='none';
				
			}else{
				document.getElementById("wA").style.display='block';
				document.getElementById("wA").innerText="Debes activar el estado del permiso para que sea válido el registro";
				document.getElementById("eA").innerText="";
				document.getElementById("eA").style.display='none';
				
			}
			
			
		}
	});
	}
 