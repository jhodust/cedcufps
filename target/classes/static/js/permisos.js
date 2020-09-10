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
 