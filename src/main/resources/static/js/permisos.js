persona.roles.forEach(function(rol) {
	  console.log(rol);
		  if(rol.authority=='ROLE_BECA'){
			  
			   $('#switchBeca').attr('checked', true);
		  }
	  }else if(persona.tipoPersona.tipoPersona=='Administrativo'){
		  if(rol.authority=='ROLE_ADMIN'){
			  var idSwitch='switchAdminAdministrativo';
			   $('#switchAdminAdministrativo').attr('checked', true);
		  }
	  }
	    
	});
  $(document).ready(function ()
			{
				
	  $(".switch").click(function(e) {  
	       console.log(e); 
	       console.log(e.target.dataset.role); 
	       
	       var role=e.target.dataset.role;
	       var idPersona=persona.id;
	       console.log($('.switch').is(':checked'));
	       if($('.switch').is(':checked')) {//se va a agregar el role
		      console.log("se quita el role");
	    	   $.ajax({
		   		headers: {"X-CSRF-TOKEN": token},
		   		type: "GET",
		   		contentType: "application/json; charset=utf-8",
		   		url: "/asignar-role/"+role+"/"+idPersona,
		   		cache: false,
		   		success: function(result) {
		   			toastr.success('Se han actualizado los roles a la persona', 'Excelente!');
		   			
		   			
		   		},
		   		error: function(err) {
		   			toastr.error('No se agregó el rol', 'Error!');
		   			$('.switch').attr('checked', false); 
		   		}
		   	});
	       
		}else{//se va a quitar el role
			$.ajax({
		   		headers: {"X-CSRF-TOKEN": token},
		   		type: "GET",
		   		contentType: "application/json; charset=utf-8",
		   		url: "/quitar-role/"+role+"/"+idPersona,
		   		cache: false,
		   		success: function(result) {
		   			toastr.success('Se han actualizado los roles a la persona', 'Excelente!');
		   			
		   		},
		   		error: function(err) {
		   			toastr.error('No se removió el rol', 'Error!');
		   			$('.switch').attr('checked', true);  
		   		}
		   	});
		}  
	    });
});