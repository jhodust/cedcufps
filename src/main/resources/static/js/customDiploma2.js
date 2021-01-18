var logoIzq={};
  logoIzq.id=null;
  logoIzq.x=80;
  logoIzq.y=150;
  logoIzq.imagen="/img/plantilla_diploma/logo1.jpg";

  var logoDer={};
  logoDer.id=null;
  logoDer.x=765;
  logoDer.y=150;
  logoDer.imagen="/img/plantilla_diploma/logo2.jpg";
  
  var tituloDefault={};
  tituloDefault.id=null;
  tituloDefault.x=485;
  tituloDefault.y=180;
  tituloDefault.texto="";
  tituloDefault.categoria="titulo";
  
  var subtituloDefault={};
  subtituloDefault.id=null;
  subtituloDefault.x=485;
  subtituloDefault.y=200;
  subtituloDefault.texto="";
  subtituloDefault.categoria="subtitulo";
  
  var firma1Default={};
  firma1Default.x=485;
  firma1Default.y=600;
  firma1Default.nombre="";
  firma1Default.xNombre="";
  firma1Default.yNombre="";
  firma1Default.cargo="";
  firma1Default.xCargo="";
  firma1Default.yCargo="";
  firma1Default.imagen="";
  
  var firma2Default={};
  firma2Default.x=485;
  firma2Default.y=600;
  firma2Default.nombre="";
  firma2Default.xNombre="";
  firma2Default.yNombre="";
  firma2Default.cargo="";
  firma2Default.xCargo="";
  firma2Default.yCargo="";
  firma2Default.imagen="";
  
  /*************INICIALIZAR CROPPIES***************************************/

/*var croppieLogo1 = document.getElementById('croppieLogo1'),
croppieLogo1 = new Croppie(croppieLogo1, {
	viewport: {
		width: 120,
		height: 120,
		type: 'square'
	},
	enforceBoundary:false,
	 boundary: { width: 165, height: 165 },
	enableExif: true
});

var croppieLogo2 = document.getElementById('croppieLogo2'),
croppieLogo2 = new Croppie(croppieLogo2, {
	viewport: {
		width: 120,
		height: 120,
		type: 'square'
	},
	enforceBoundary:false,
	 boundary: { width: 165, height: 165 },
	enableExif: true
});

var croppieFirma1 = document.getElementById('croppieFirma1'),
croppieFirma1 = new Croppie(croppieFirma1, {
	viewport: {
		width: 250,
		height: 100,
		type: 'square'
	},
	enforceBoundary:false,
	 boundary: { width: 350, height: 200 },
	enableExif: true
});
	
var croppieFirma2 = document.getElementById('croppieFirma2'),
croppieFirma2 = new Croppie(croppieFirma2, {
	viewport: {
		width: 250,
		height: 100,
		type: 'square'
	},
	enforceBoundary:false,
	 boundary: { width: 350, height: 200 },
	enableExif: true
});*/

croppieLogo1 = $('#croppieLogo1').croppie({
	viewport: {
		width: 120,
		height: 120,
		type: 'square'
	},
	enforceBoundary:false, 
	mouseWheelZoom: true,
	 boundary: { width: 165, height: 165 },
	enableExif: true
});

croppieLogo2 = $('#croppieLogo2').croppie({
	viewport: {
		width: 120,
		height: 120,
		type: 'square'
	},
	enforceBoundary:false,
	 boundary: { width: 165, height: 165 },
	enableExif: true
});

croppieFirma1 = $('#croppieFirma1').croppie({
	viewport: {
		width: 250,
		height: 100,
		type: 'square'
	},
	enforceBoundary:false,
	 boundary: { width: 350, height: 200 },
	enableExif: true
});

croppieFirma2 = $('#croppieFirma2').croppie({
	viewport: {
		width: 250,
		height: 100,
		type: 'square'
	},
	enforceBoundary:false,
	 boundary: { width: 350, height: 200 },
	enableExif: true
});

  /*******************************OBTENER DATOS***************************/
  console.log("obtener datos");
  console.log(eduContinua);
  var arrayImagenes= null;
  var arrayTextos= null;
  var arrayFirmas= null;
  
  if(  eduContinua.diploma != null){
	  console.log("entra al if");
	  arrayImagenes= eduContinua.diploma.imagenes;
	  arrayTextos= eduContinua.diploma.textos;
	  arrayFirmas= eduContinua.diploma.firmas;
  }
  
  
 
  /******************************DEFINO VARIABLES DE CAMBIOS INICIALES***********/
  var logoInicialIzq=logoIzq;
  var logoInicialDer=logoDer;
  var tituloInicial=tituloDefault;
  var subtituloInicial=subtituloDefault;
  var firma1Inicial=firma1Default;
  var firma2Inicial=firma2Default;
  console.log("arrayImagenes");
  console.log(arrayImagenes);
  if(arrayImagenes!=null){
	  arrayImagenes.forEach(function(imagen) {
		  if (imagen.x == 80) {
			  logoInicialIzq=imagen;
			  console.log("logo inicial izq");
			  console.log(logoInicialIzq);
			  /*croppieLogo1.bind({
				    url: logoInicialIzq.ruta,
				});*/
				croppieLogo1.croppie('bind', {url: logoInicialIzq.imagen });
			  document.getElementById('checkBoxLogo1').checked=true;
		  }
		  if (imagen.x == 765) {
			  logoInicialDer=imagen;
			  /*croppieLogo2.bind({
				    url: logoInicialDer.ruta,
				});*/
				croppieLogo2.croppie('bind', {url: logoInicialDer.imagen});
			  document.getElementById('checkBoxLogo2').checked=true;
		  }
		});  
  }
  
  
  if(!document.getElementById('checkBoxLogo1').checked){
	  logoInicialIzq.imagen="";
  }
  if(!document.getElementById('checkBoxLogo2').checked){
	  logoInicialDer.imagen="";
  }
  
  if(arrayTextos!=null){
	  arrayTextos.forEach(function(texto) {
		  if (texto.categoria == "titulo") {
			  tituloInicial=texto;
		  }
		  if (texto.categoria == "subtitulo") {
			  subtituloInicial=texto;
		  }
		});  
  }
  
  
  
  if(tituloInicial.texto==""){
	  tituloInicial.texto="Facultad de " + eduContinua.facultad;
  }
  $("#inputTexto1").val(tituloInicial.texto);
  if(subtituloInicial.texto==""){
	  subtituloInicial.texto="Programa de " + eduContinua.programaResp;
  }
  $("#inputTexto2").val(subtituloInicial.texto);
  
  if(arrayFirmas!=null){
	  arrayFirmas.forEach(function(firma) {
		  if(firma1Inicial.nombre==""){
			  firma1Inicial=firma;
			  document.getElementById('checkBoxFirma1').checked=true;
			  $("#inputNombreFirma1").val(firma1Inicial.nombre);
			  $("#inputCargoFirma1").val(firma1Inicial.cargo);
			  console.log("firma1Inicia");
			  console.log(firma1Inicial);
			  croppieFirma1.croppie('bind', {url: firma1Inicial.imagen,});
			  
		 }else if(firma2Inicial.nombre==""){
			  firma2Inicial=firma;
			  document.getElementById('checkBoxFirma2').checked=true;
			  $("#inputNombreFirma2").val(firma2Inicial.nombre);
			  $("#inputCargoFirma2").val(firma2Inicial.cargo);
			  croppieFirma2.croppie('bind', {url: firma2Inicial.imagen,});
			  /*croppieFirma2.bind({
				    url: firma2Inicial.imagenFirmaDigital,
				});*/
		  }
		});  
  }
  
  /***********DEFINO VARIABLES QUE SE AFECTAN****************/
  
  var logo1=logoInicialIzq;
  var logo2=logoInicialDer;
  var titulo=tituloInicial;
  var subtitulo=subtituloInicial;
  var firma1=firma1Inicial;
  var firma2=firma2Inicial;
  
  var rutaLogo1;
  var rutaLogo2;
  var rutaFirma1;
  var rutaFirma2;
  
  var arrayTextos=null;
  var arrayImagenes=null;
  var arrayFirmas=null;
  var rutaAntiguaLogo1=null;
  var rutaAntiguaLogo2=null;
  var rutaAntiguaFirma1=null;
  var rutaAntiguaFirma2=null;
  
  /*********************DIBUJO CANVAS CON CONFIGURACIONES INICIALES**********/
  //canvas visible
  
  llenarCanvas('canvas',logo1 ,logo2,titulo,subtitulo,'Estudiante','Identificado con C.C. XXXXX, participó en el ' + eduContinua.tipoEduContinua, eduContinua.nombre, eduContinua.duracion,firma1,firma2);
  //canvas definitivo
  llenarCanvas('canvas2',logo1 ,logo2,titulo,subtitulo,'','', eduContinua.nombre, eduContinua.duracion,firma1,firma2);
  /***********COMPORTAMIENTO DEL INPUT IMAGEN LOGOS*******************/
  $("#inputLogo1").on("change",function(e){
	  /*console.log(this.files.length);
	  if(this.files.length>0){
		  var file = this.files[0]; //get first file
	      var reader  = new FileReader();
	      reader.readAsDataURL(file);
	      reader.onloadend = function (e) {
	    	  var image = new Image();
	          rutaLogo1 = e.target.result;
	         
	      }   
	  }*/
	  
	  if (this.files && this.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	        	croppieLogo1.croppie('bind', {
	        		url: e.target.result
	        	}).then(function(){
	        		console.log('jQuery bind complete');
	        	});
	        }
	        reader.readAsDataURL(this.files[0]);
	    }
	    else {
	    	toastr.error('Lo sentimos, su navegador no es compatible con la API FileReader', 'Error!');
	    }
  });
  
  $("#inputLogo2").on("change",function(e){
	  /*console.log(this.files.length);
	  if(this.files.length>0){
		  var file = this.files[0]; //get first file
	      var reader  = new FileReader();
	      reader.readAsDataURL(file);
	      reader.onloadend = function (e) {
	    	  var image = new Image();
	          rutaLogo2 = e.target.result;
	         
	      }   
	  }*/
	  if (this.files && this.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	        	croppieLogo2.croppie('bind', {
	        		url: e.target.result
	        	}).then(function(){
	        		console.log('jQuery bind complete');
	        	});
	        }
	        reader.readAsDataURL(this.files[0]);
	    }
	    else {
	    	toastr.error('Lo sentimos, su navegador no es compatible con la API FileReader', 'Error!');
	    }
  });
  
  
  /*********COMPORTAMIENTO DE INPUT IMAGEN FIRMAS************/
  $("#inputFirmaDigital1").on("change",function(e){
	  /*console.log(this.files.length);
	  if(this.files.length>0){
		  var file = this.files[0]; //get first file
	      var reader  = new FileReader();
	      reader.readAsDataURL(file);
	      reader.onloadend = function (e) {
	    	  var image = new Image();
	          rutaFirma1 = e.target.result;
	         
	      }   
	  }*/
	  if (this.files && this.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	        	croppieFirma1.croppie('bind', {
	        		url: e.target.result
	        	}).then(function(){
	        		console.log('jQuery bind complete');
	        	});
	        }
	        reader.readAsDataURL(this.files[0]);
	    }
	    else {
	    	toastr.error('Lo sentimos, su navegador no es compatible con la API FileReader', 'Error!');
	    }
  });
  
  $("#inputFirmaDigital2").on("change",function(e){
	  /*console.log(this.files.length);
	  if(this.files.length>0){
		  var file = this.files[0]; //get first file
	      var reader  = new FileReader();
	      reader.readAsDataURL(file);
	      reader.onloadend = function (e) {
	    	  var image = new Image();
	          rutaFirma2 = e.target.result;
	         
	      }   
	  }*/
	  if (this.files && this.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	        	croppieFirma2.croppie('bind', {
	        		url: e.target.result
	        	}).then(function(){
	        		console.log('jQuery bind complete');
	        	});
	        }
	        reader.readAsDataURL(this.files[0]);
	    }
	    else {
	    	toastr.error('Lo sentimos, su navegador no es compatible con la API FileReader', 'Error!');
	    }
  });
  
  
  
  
  /**************COMPORTAMIENTO DE CHECKBOX*****************/
  $("#checkBoxLogo1").on("change",function(e){
	  if(document.getElementById('checkBoxLogo1').checked){
		  logo1.imagen=rutaAntiguaLogo1;
	  }else{
		  rutaAntiguaLogo1=logo1.imagen;
		  logo1.imagen="";
	  }
  });
  
  $("#checkBoxLogo2").on("change",function(e){
	  if(document.getElementById('checkBoxLogo2').checked){
		  logo2.imagen=rutaAntiguaLogo2;
	  }else{
		  rutaAntiguaLogo2=logo2.imagen;
		  logo2.imagen="";
	  }
  });
  
  $("#checkBoxFirma1").on("change",function(e){
	  if(document.getElementById('checkBoxFirma1').checked){
		  firma1.imagen=rutaAntiguaFirma1;
	  }else{
		  rutaAntiguaFirma1=firma1.imagen;
		  firma1.imagen="";
	  }
  });
  
  $("#checkBoxFirma2").on("change",function(e){
	  if(document.getElementById('checkBoxFirma2').checked){
		  firma2.imagen=rutaAntiguaFirma2;
	  }else{
		  rutaAntiguaFirma2=firma2.imagen;
		  firma2.imagen="";
	  }
  });
  
 
  
  
  /**************METODO ACTUALIZAR CANVAS*****************/
  $("#btnActualizar").on("click",function(e){
	  arrayImagenes=new Array();
	  arrayTextos=new Array(); 
	  arrayFirmas=new Array(); 
	  
	  inputImagen1=document.getElementById('inputLogo1');
	  if(document.getElementById('checkBoxLogo1').checked){
		  
		  /*if(inputImagen1.files.length>0){
			  logo1.ruta=rutaLogo1;
			  arrayImagenes.push(logo1);
		  }*/
		  /*croppieLogo1.result({
			  	type: 'canvas',
				size: 'viewport'
			}).then(function (resp) {
				console.log(resp);
				logo1.ruta=resp;
				arrayImagenes.push(logo1);
			});*/
		  croppieLogo1.croppie('result', {
				type: 'canvas',
				size: 'viewport'
			}).then(function (resp) {
				logo1.imagen=resp;
				arrayImagenes.push(logo1);
			});
		  
	  }
	  
	  inputImagen2=document.getElementById('inputLogo2');
	  if(document.getElementById('checkBoxLogo2').checked){
		  /*if(inputImagen2.files.length>0){
			  logo2.ruta=rutaLogo2;
			  arrayImagenes.push(logo2);
		  }*/
		  /*croppieLogo2.result({
			    type: 'canvas',
				size: 'viewport'
			}).then(function (resp) {
				console.log(resp);
				logo2.ruta=resp;
				arrayImagenes.push(logo2);
			});*/
		  croppieLogo2.croppie('result', {
				type: 'canvas',
				size: 'viewport'
			}).then(function (resp) {
				logo2.imagen=resp;
				arrayImagenes.push(logo2);
			});
		  
	  }
      
	  tituloNuevo=document.getElementById('inputTexto1');
	  if(tituloNuevo.value!=""){
		  titulo.texto=tituloNuevo.value;
		  arrayTextos.push(titulo);
	  }
	  subtituloNuevo=document.getElementById('inputTexto2');
	  if(subtituloNuevo.value!=""){
		  subtitulo.texto=subtituloNuevo.value;
		  arrayTextos.push(subtitulo);
	  }
     
	  
	  
	  
	  if(document.getElementById('checkBoxFirma1').checked && document.getElementById('checkBoxFirma2').checked){
		 firma1.x=145;
		 firma1.y=600;
		 firma1.xNombre=280;
		 firma1.yNombre=615;
		 firma1.xCargo=280;
		 firma1.yCargo=627;
		 
		 firma2.x=530;
		 firma2.y=600;
		 firma2.xNombre=665;
		 firma2.yNombre=615;
		 firma2.xCargo=665;
		 firma2.yCargo=627;
 	     
	  }else{
		  if(document.getElementById('checkBoxFirma1').checked && !document.getElementById('checkBoxFirma2').checked){
			 firma1.x=345;
			 firma1.y=590;
			 firma1.xNombre=480;
			 firma1.yNombre=605;
			 firma1.xCargo=480;
			 firma1.yCargo=617;
			  
			 firma2.x="";
			 firma2.y="";
			 firma2.nombre="";
			 firma2.cargo="";
			 firma2.xNombre="";
			 firma2.yNombre="";
			 firma2.xCargo="";
			 firma2.yCargo="";
			 firma2.imagen="";
		  }else if(!document.getElementById('checkBoxFirma1').checked && document.getElementById('checkBoxFirma2').checked){
			 firma1.x="";
			 firma1.y="";
			 firma1.xNombre="";
			 firma1.yNombre="";
			 firma1.nombre="";
			 firma1.cargo="";
			 firma1.xCargo="";
			 firma1.yCargo="";
			 firma1.imagen="";
			 
			 firma2.x=345;
			 firma2.y=590;
			 firma2.xNombre=480;
			 firma2.yNombre=605;
			 firma2.xCargo=480;
			 firma2.yCargo=617;
			 
		  }else{
			 firma1.x="";
			 firma1.y="";
			 firma1.nombre="";
			 firma1.cargo="";
			 firma1.xNombre="";
			 firma1.yNombre="";
			 firma1.xCargo="";
			 firma1.yCargo="";
			 firma1.imagen="";
			 
			 firma2.x="";
			 firma2.y="";
			 firma2.nombre="";
			 firma2.cargo="";
			 firma2.xNombre="";
			 firma2.yNombre="";
			 firma2.xCargo="";
			 firma2.yCargo="";
			 firma2.imagen="";
		  }
	  }
	  
	  inputFirma1=document.getElementById('inputFirmaDigital1');
	  if(document.getElementById('checkBoxFirma1').checked){
		  firma1.nombre=$("#inputNombreFirma1").val();
		  firma1.cargo=$("#inputCargoFirma1").val();
		  /*if(inputFirma1.files.length>0){
			  firma1.imagenFirmaDigital=rutaFirma1;
			  arrayFirmas.push(firma1);
		  }*/
		  /*croppieFirma1.result({
				type: 'canvas',
				size: 'viewport'
			}).then(function (resp) {
				console.log(resp);
				firma1.imagenFirmaDigital=resp;
				  arrayFirmas.push(firma1);
			});
		  */
		  croppieFirma1.croppie('result', {
				type: 'canvas',
				size: 'viewport'
			}).then(function (resp) {
				firma1.imagen=resp;
				  arrayFirmas.push(firma1);
			});
	  }
	  
	  inputFirma2=document.getElementById('inputFirmaDigital2');
	  if(document.getElementById('checkBoxFirma2').checked){
		  firma2.nombre=$("#inputNombreFirma2").val();
		  firma2.cargo=$("#inputCargoFirma2").val();
		  /*if(inputFirma2.files.length>0){
			  firma2.imagenFirmaDigital=rutaFirma2;
			  arrayFirmas.push(firma2);
		  }*/
		  /*croppieFirma2.result({
				type: 'canvas',
				size: 'viewport'
			}).then(function (resp) {
				console.log(resp);
				firma2.imagenFirmaDigital=resp;
				  arrayFirmas.push(firma2);
			});*/
			croppieFirma2.croppie('result', {
				type: 'canvas',
				size: 'viewport'
			}).then(function (resp) {
				console.log(resp);
				firma2.imagen=resp;
				  arrayFirmas.push(firma2);
			});
	  }
	  
	 
	  
	 
	  //canvas visible
	  llenarCanvas('canvas',logo1 ,logo2,titulo,subtitulo,'Estudiante','Identificado con C.C. XXXXX, participó en el ' + eduContinua.tipoEduContinua, eduContinua.nombre, eduContinua.duracion,firma1,firma2);
	  //canvas definitivo
	  llenarCanvas('canvas2',logo1 ,logo2,titulo,subtitulo,'','', eduContinua.nombre, eduContinua.duracion,firma1,firma2);
  });
  
  
  /**********************METODO GUARDAR PLANTILLA*******************/
  $("#btnGuardar").on("click",function(e){
	 
	  var canvas = document.getElementById('canvas2');
	  var ctx = canvas.getContext('2d');
	  var dataURL = canvas.toDataURL('image/jpg');
	 
		var blob = dataURItoBlob(dataURL);
		//var formData = new FormData();
		var newFile = new File([blob], 'plantilla.jpg', {type: 'image/jpg'});
		
		//formData.append('file', newFile);
		//formData.append("idEduContinua",educacionContinua.id);
		//formData.append('imagenes', JSON.stringify(arrayImagenes));
		//formData.append('texto1', JSON.stringify(titulo));
		//formData.append('texto2', JSON.stringify(subtitulo));
		/*console.log("educontinua");
		console.log(JSON.stringify(educacionContinua));
		console.log("imagene1");
		console.log(JSON.stringify(logo1));
		console.log("imagene2");
		console.log(JSON.stringify(logo2));
		console.log("texto1");
		console.log(JSON.stringify(titulo));
		console.log("texto2");
		console.log(JSON.stringify(subtitulo));*/
		
		var idDiploma=null;
		if(eduContinua.diploma == null){
			/*var eduContinuaJson={};
			var diplomaJson={};
			//diplomaJson.imagenPlantilla="ruta imagen.jpg";
			/*eduContinuaJson.id=educacionContinua.id;
			eduContinuaJson.diploma=diplomaJson;
			eduContinuaJson.elementos=arrayTextos;
			console.log(eduContinuaJson);*/
			idDiploma=null;
		}else{
			idDiploma=educacionContinua.diploma.id;
		}
		guardarPlantilla(dataURL,idDiploma);
  });
  
  function guardarPlantilla(dataDiploma,idDiploma){
	  //var formData = new FormData();
	  //formData.append('file', file);
	   //formData.append('educacionContinua', JSON.stringify({'id':educacionContinua.id}));
	  //formData.append('edu', JSON.stringify({"id":educacionContinua.id,"nombre":educacionContinua.nombre}));
	 
	  $.ajax({
	    	headers: {"X-CSRF-TOKEN": token},
	    	url: "/educacion-continua/generar-plantilla-diploma",
	        type: "POST",
	        //data: formData,
	        data: JSON.stringify({"id":eduContinua.id,"diploma":{"id":idDiploma, "imagenPlantilla":dataDiploma,"textos":arrayTextos, "imagenes":arrayImagenes, "firmas":arrayFirmas}}),
	        enctype: 'multipart/form-data',
	        contentType: "application/json; charset=utf-8",
	        processData: false,
	        cache: false,
	        success: function (res) {
	        	console.log("resultado ajax");
	        	console.log(res);
	            /*toastr.success('Se ha realizado la inscripción con éxito', 'Excelente!');
	            var enlace = document.getElementById("btn_inscripcion");
	            enlace.innerHTML ="CANCELAR INSCRIPCIÓN!";
	            enlace.setAttribute( "onclick","cancelarInscripcion()");*/
	        },
	        error: function (err) {
	            console.error(err);
	        }
	    });
  }
  
  function llenarCanvas(idCanvas,logo1,logo2,titulo,subtitulo, infoParticipante, infoIdentificacion, nombreEduContinua, duracion,firmaD1,firmaD2){
	  
	  var posY=0;
	  var yTitulo;
	  var ySubtitulo;
      var canvas = document.getElementById(idCanvas);
      if (canvas.getContext) {
        var ctx = canvas.getContext('2d');
        ctx.beginPath();
        ctx.clearRect(0,0,canvas.width, canvas.height);
        var centerX = canvas.width/2;
      
	   
       /*var i=0;
       i=porcentajeReducido(imgLogo1.width);
       imgLogo1.width=imgLogo1.width*i/100;
       imgLogo1.height=imgLogo1.height*i/100;
       console.log("i: " + i);
       
       i=porcentajeReducido(imgLogo2.width);
       imgLogo2.width=imgLogo2.width*i/100;
       imgLogo2.height=imgLogo2.height*i/100;
       console.log("i: " + i);
       
       
       console.log("logo1  " + imgLogo1.width);
       console.log("ancho1 " + imgLogo1.height);
       console.log("logo2  " + imgLogo2.width);
       console.log("ancho2 " + imgLogo2.height);*/
       var img = new Image();
    	img.src="/img/plantilla_diploma/fondo.jpg";
    	img.onload = function() {
    		//dibujo fondo
    	    ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
    		
    		//dibujo logo1
    	    console.log("llenando logos");
    	    console.log(logo1);
    		if(logo1.ruta != ""){
    			console.log("enra al if de logo1!=null");
    			var imgLogo1 = new Image();
    			console.log("logo1")
    			console.log(logo1);
        		imgLogo1.src=logo1.imagen;
        		imgLogo1.onload = function() {
        	    	//imgLogo1= redimensionarLogos(imgLogo1);
        	    	ctx.drawImage(imgLogo1, logo1.x, logo1.y, imgLogo1.width, imgLogo1.height);//max 150 width
            	};	
    		}
    	    
        	
        	//dibujo logo2
    		if(logo2.ruta != ""){
    			var imgLogo2 = new Image();
    			console.log("logo2")
    			console.log(logo2);
      		    imgLogo2.src=logo2.imagen;
        	    imgLogo2.onload = function() {
        	    	//imgLogo2= redimensionarLogos(imgLogo2);
        	    	ctx.drawImage(imgLogo2, logo2.x, logo2.y, imgLogo2.width, imgLogo2.height);//max 150 width
        	    };
    		}
    	    
    	    //Great Vibes
    	    //Monotype Corsiva
    	    //Microsoft Himalaya
    	    //Harrington
    	    //Papyrus
    	    //Playball
    	    //Script MT Bold
    	    //Vivaldi
    	    //Edwardian Script ITC
    	    //Cookie
    	    //Vladimir Script
    	    ctx.font = '35px Monotype Corsiva';
    		ctx.textAlign="center"; 
            ctx.fillText('Universidad Francisco de Paula Santander', centerX, 140);
            
            posY=escribirTextoVariasLineas(ctx,35,titulo.texto,centerX,titulo.y,30);
            yTitulo=posY;
            posY=escribirTextoVariasLineas(ctx,40,subtitulo.texto,centerX,posY+10,30);
            ySubtitulo=posY;
            
            if(posY<=300){
            	posY=300;
            }else{
            	posY=posY;
            }
            
            ctx.fillText('Certifican que:', centerX, posY);
          //persona
            //ctx.fillText('Jhocel Duvan Suescun Torres', centerX, 350);
          	//ctx.fillText('Jhocel Duvan Suescun Torres', centerX, 360);
          	ctx.fillText(infoParticipante, centerX, 360);
            ctx.font = '24px Monotype Corsiva';
            //ctx.fillText('Identificado con C.C. 1090502154, participó en el ' + tipoEduContinua, centerX, 410);
            ctx.fillText(infoIdentificacion, centerX, 410);
            posY=escribirTextoVariasLineas(ctx,65,nombreEduContinua,centerX,445,30);
            ctx.fillText('con una intensidad de ' + duracion + ' horas.', centerX, posY);
            //ctx.clearRect(80,120,200, 500);
            console.log("firmas  en actualizar");
            console.log(firmaD1);
            console.log(firmaD2);
            if(firmaD1.nombre!="" && firmaD1.cargo!="" ){
    			var imgFirma1 = new Image();
    			console
    			imgFirma1.src=firmaD1.imagen;
    			console.log("firma1")
    			console.log(firmaD1);
    			//imgFirma1.src="/firma3.jpg";
    			console.log(imgFirma1);
    			imgFirma1.onload = function() {
        	    	//imgFirma1= redimensionarFirmas(imgFirma1);
        	    	console.log("tamaños firma1");
        	    	console.log(imgFirma1.width);
        	    	console.log(imgFirma1.height);
            	    ctx.drawImage(imgFirma1, firmaD1.x+10, firmaD1.y-70, imgFirma1.width, imgFirma1.height);
            	    
            	    ctx.moveTo(firmaD1.x,firmaD1.y);
               	    ctx.lineTo(firmaD1.x+270,firmaD1.y);
               	    ctx.strokeStyle = "#000";
               	    ctx.stroke();
               	    
	               	ctx.font = 'bold 13px Arial';
	         	    ctx.textAlign="center";
	         	    ctx.fillText(firmaD1.nombre, firmaD1.xNombre, firmaD1.yNombre);
	         	    ctx.fillText(firmaD1.cargo, firmaD1.xCargo, firmaD1.yCargo);
        	    };
            } 
        	    
        	    /*var imgFirma1 = new Image();
    			//imgFirma1.src=firmaD1.imagenFirmaDigital;
    			imgFirma1.src="/firma3.jpg";
    			imgFirma1.onload = function() {
        	    	//imgFirma1= redimensionarFirmas(imgFirma1);
        	    	console.log("tamaños");
        	    	console.log(imgFirma1.width);
        	    	console.log(imgFirma1.height);
            	    ctx.drawImage(imgFirma1, 355, 510, 250, 100);//max 150 width
            	    
            	    ctx.moveTo(345,590);
               	    ctx.lineTo(615,590);
               	    ctx.strokeStyle = "#000";
               	    ctx.stroke();
               	    
	               	ctx.font = 'bold 13px Arial';
	         	    ctx.textAlign="center";
	         	    ctx.fillText('MSc. Oscar Alberto Gallardo', 480, 605);
	         	    ctx.fillText('Dir. Departamento Sistemas e Informática', 480, 617);
        	    };*/
        	    if(firmaD2.nombre!="" && firmaD2.cargo!="" ){
	        	   var imgFirma2 = new Image();
	    			imgFirma2.src=firmaD2.imagen;
	    			console.log("firma2")
	    			console.log(firmaD2);
	    			//imgFirma2.src="/firma2.png";
	    			imgFirma2.onload = function() {
	        	    	//imgFirma1= redimensionarFirmas(imgFirma1);
	        	    	 ctx.drawImage(imgFirma2, firmaD2.x+10, firmaD2.y-70, imgFirma2.width, imgFirma2.height);
	            	    
	            	    ctx.moveTo(firmaD2.x,firmaD2.y);
	               	    ctx.lineTo(firmaD2.x+270,firmaD2.y);
	               	    ctx.strokeStyle = "#000";
	               	    ctx.stroke();
	               	    
	            	    ctx.font = 'bold 13px Arial';
	            	    ctx.textAlign="center";
	            	    ctx.fillText(firmaD2.nombre, firmaD2.xNombre, firmaD2.yNombre);
	            	    ctx.fillText(firmaD2.cargo, firmaD2.xCargo, firmaD2.yCargo);
	        	    };
        	    }
        	    ctx.font = '23px Monotype Corsiva';
        	    ctx.textAlign="right"; 
        	    var fecha= new Date(eduContinua.fechaFin);
        	   var months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre" ,"Diciembre"];
        	    ctx.fillText('San José de Cúcuta, ' +fecha.getDate()+ ' de ' + months[fecha.getMonth()] + ' de ' + fecha.getFullYear(), 870, 665);
            //}
    	}
    	
         
      }
  }
  
  
    function redimensionarLogos(imagen){
    	var i=0;
    	widthFinal=0;
    	while(widthFinal<=150){
     	   if((widthFinal+imagen.width*0.01)<150){
     		  widthFinal=widthFinal+imagen.width*0.01;
     		  i++;
     	   }else{
     		   break;
     	   }
        }
    	
    	imagen.width=imagen.width*i/100;
        imagen.height=imagen.height*i/100;
        return imagen
    }
    
    function redimensionarFirmas(imagen){
    	var i=0;
    	widthFinal=0;
    	heightFinal=0;
    	while(widthFinal<=250 && heightFinal<=100){
     	   if((widthFinal+imagen.width*0.01)<250 && (heightFinal+imagen.height*0.01)<100){
     		  widthFinal=widthFinal+imagen.width*0.01;
     		  heightFinal=heightFinal+imagen.height*0.01;
     		  i++;
     	   }else{
     		   break;
     	   }
        }
    	
    	imagen.width=imagen.width*i/100;
        imagen.height=imagen.height*i/100;
        return imagen
    }
    
   function escribirTextoVariasLineas(ctx,intCaracSeparacion,texto,puntox,altura,amplitud){
	   //console.log("longitudCadena: " + texto.length);
	   var array= texto.split(" ");
       var cantCaracteres=0;
       var y=0;
       var z=0;
       var cad="";
       var posY=altura;
       var amplitud=amplitud;
       for (var i=0; i<array.length; i++){
       	//console.log("i: " +i + " contenido: " + array[i] + " " + array[i].length);
       	
       	if((cantCaracteres+array[i].length)<intCaracSeparacion){
       		
       		cantCaracteres=cantCaracteres+array[i].length;
       		cantCaracteres=cantCaracteres+1;//espacio
       		//console.log("cant:" + cantCaracteres);
       	}else{
       		
       		z=z+cantCaracteres;
       		
       		cantCaracteres=0;
       		//console.log("i: "+ i + " z: " + z);
       		cad=texto.substring(y,z);
       		//console.log(texto.substring(y,z));
       		ctx.fillText(cad, puntox, posY);
       		//console.log("escribe");
       		posY=posY+amplitud;
       		y=z;
       		i=i-1;
       	}
       	if(i==array.length-1){
   			z=z+cantCaracteres;
       		
       		cantCaracteres=0;
       		//console.log("i: "+ i + " z: " + z);
       		cad=texto.substring(y,z);
       		//console.log(texto.substring(y,z));
       		ctx.fillText(cad, puntox, posY);
       		//console.log("escribe");
       		posY=posY+amplitud;
       		y=z;
       	}
       	
       }
	   return posY;
   }
   function dataURItoBlob(dataURI) {
	    // convert base64/URLEncoded data component to raw binary data held in a string
	    var byteString;
	    if (dataURI.split(',')[0].indexOf('base64') >= 0)
	        byteString = atob(dataURI.split(',')[1]);
	    else
	        byteString = unescape(dataURI.split(',')[1]);

	    // separate out the mime component
	    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
	    console.log("mimeString: " +  mimeString);
	    // write the bytes of the string to a typed array
	    var ia = new Uint8Array(byteString.length);
	    for (var i = 0; i < byteString.length; i++) {
	        ia[i] = byteString.charCodeAt(i);
	    }

	    return new Blob([ia], {type: mimeString});
	}