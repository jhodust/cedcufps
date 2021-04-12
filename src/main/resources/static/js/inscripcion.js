
var token = $("meta[name='_csrf']").attr("content");

var meses = [
	  'Enero',
	  'Febrero',
	  'Marzo',
	  'Abril',
	  'Mayo',
	  'Junio',
	  'Julio',
	  'Agosto',
	  'Septiembre',
	  'Octubre',
	  'Noviembre',
	  'Diciembre'
	];
var ctx;
var c;
fabric.Object.prototype.objectCaching = true;

var canvasInscripcion = new fabric.Canvas('mycanvasInscripcion',{
	renderOnAddRemove:true,
	canvasInscripcionOriginalWidth: 800,
    canvasInscripcionOriginalHeight: 800,
    canvasInscripcionWidth: 800,
    canvasInscripcionHeight: 800,
});


$(document).ready(function () {
	 //c = document.getElementById("mycanvasInscripcion");
	 //ctx= c.getContext("2d");
	
	//ctx.fillStyle = "#FFF";
	//ctx.fillRect(0, 0, c.width, c.height);
	
	/*var imgFondo = new Image();
	imgFondo.src="/img/tarjeta_inscripcion.png";
	imgFondo.onload = function() {
		
	    
	}*/
	
	
	
	/*var img2 = new Image();
	img2.src="/logos/logo-ufps-vertical.jpg";
	img2.onload = function() {
	    ctx.drawImage(img2, 50, 50, 236.22, 173.23);
	    
	}*/

	createTemplateCanvasInscripcion();
	
});

function createTemplateCanvasInscripcion(){
	fabric.Image.fromURL("/img/tarjeta_inscripcion.jpg", function(img) {
		  canvasInscripcion.setBackgroundImage(img, canvasInscripcion.renderAll.bind(canvasInscripcion), {
	            scaleX: canvasInscripcion.width / img.width,
	            scaleY: canvasInscripcion.height / img.height
	         });
	  });
	
	
		
	fabric.Image.fromURL("/logos/logo-ufps-vertical.jpg", function(img) {
		  img.scale(0.065);
		  canvasInscripcion.add(img.set({
			  left: 50, 
		      top: 50
		  }));
	});
	 
}

function buildOptions(){
	buttonsJson={};
	listTipoPersonaValidInscripcion.forEach(function(e){
		
		var btn='btn'+e[1];
		btnn={};
		btnn.text=e[1];
		btnn.value=e[0];
		buttonsJson[btn]=btnn;
	});
	swal("Cómo desea realizar la inscripción?", {
		  buttons: buttonsJson,
		})
		.then((value) => {
			if(value != null){
				prepararInscripcion(value);
			}
			
			
		});
}

function realizarInscripcion(){
	var idTipoPersona;
	if(listTipoPersonaValidInscripcion.length > 1 ){
		buildOptions();
	}else{
		idTipoPersona=listTipoPersonaValidInscripcion[0][0];
		prepararInscripcion(idTipoPersona);
	}
	
		
}

function showSpinnerInscripcion(){
	document.getElementById("btnSpinnerInscripcion").style.display='flex';
	document.getElementById("btn_inscripcion").style.display='none';
}

function hideSpinnerInscripcion(){
	try{
		document.getElementById("btnSpinnerInscripcion").style.display='none';
		document.getElementById("btn_inscripcion").style.display='inline';
	}catch(error){
		
	}
	
}



function prepararInscripcion(idTipoPersona){
	showSpinnerInscripcion();
	ajaxRealizarInscripcion(idTipoPersona);
}

function ajaxRealizarInscripcion(idTipoPersona){
	
	if(idTipoPersona==undefined){
		var data={'idEduContinua':idEduContinua};
	}else{
		var data={'idEduContinua':idEduContinua, 'idTipoPersona':idTipoPersona};
	}
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/preinscripcion/realizar-inscripcion",
		data: data,
		cache: false,
		success: function(result) {
			creacionTarjetaInscripcion(result);
			//window.setTimeout(function(){location.reload()},1000);
			
		},
		error: function(err) {
			hideSpinnerInscripcion();
		}
	});
}
function creacionTarjetaInscripcion(p){
	
    var primerNombre = p.primerNombre;
    var segundoNombre = p.segundoNombre;
    var primerApellido = p.primerApellido;
    var segundoApellido = p.segundoApellido;
    var fecha=new Date(p.fechaInicioEduContinua);
    var imgQR="/"+p.imagenQr;
    
    var nombres="";
    if(segundoNombre!=null){
    	nombres= primerNombre + " " + segundoNombre;
    }else{
    	nombres= primerNombre;
    }
   
	var apellidos = primerApellido + " " + segundoApellido;
	
	var dia=null;
	var hora=null;
	var minutos=null;
	
	if(fecha.getDate()<10){
		dia="0"+fecha.getDate();
	}else{
		dia=fecha.getDate();
	}
	var fechaE=dia + " " + meses[fecha.getMonth()] + " " + fecha.getFullYear();
	
	if(fecha.getHours()<10){
		hora="0"+fecha.getHours();
	}else{
		hora=fecha.getHours();
	}
	
	if(fecha.getMinutes()<10){
		minutos="0"+fecha.getMinutes();
	}else{
		minutos=fecha.getMinutes();
	}
	
	var tiempo=null;
	if(fecha.getHours()<= 11){
		tiempo=hora+":"+minutos + " A.M";
	}else if (fecha.getHours()==12){
		tiempo=hora+":"+minutos + " M";
	}else{
		tiempo=hora+":"+minutos;
	}
	
	
	
	var sizeEdC= p.educacionContinua.length>70 ? 25 : 30;
	var topEdC= p.educacionContinua.length>70 ? 50 : 80;
	var text = new fabric.Textbox(p.educacionContinua, { 
		fontSize: sizeEdC,
		fontFamily: 'Berlin Sans FB',
		textAlign: 'center',
		fill: '#000',
		width: 420,
		fixedWith: 420,
		left: 300, 
	      top: topEdC
		
	});
	canvasInscripcion.add(text);
	
	
	var topApellidos= nombres.length >30  ? 350:300
	var text = new fabric.Textbox(nombres, { 
		fontSize: 30,
		fontFamily: 'Berlin Sans FB',
		textAlign: 'left',
		fill: '#000',
		width: 350,
		fixedWith: 350,
		left: 80, 
	      top: 270
		
	});
	canvasInscripcion.add(text);
	
	var text = new fabric.Textbox(apellidos, { 
		fontSize: 30,
		fontFamily: 'Berlin Sans FB',
		textAlign: 'left',
		fill: '#000',
		width: 350,
		fixedWith: 350,
		left: 80, 
	      top: topApellidos
		
	});
	canvasInscripcion.add(text);
	
	var text = new fabric.Textbox(p.tipoParticipante.toUpperCase(), { 
		fontSize: 35,
		fontFamily: 'Berlin Sans FB',
		textAlign: 'left',
		fontWeight: 'bold',
		fill: '#000',
		width: 400,
		fixedWith: 450,
		left: 80, 
	      top: 440
		
	});
	canvasInscripcion.add(text);
	
	var text = new fabric.Textbox(p.tipoEduContinua.toUpperCase(), { 
		fontSize: 35,
		fontFamily: 'Berlin Sans FB',
		textAlign: 'left',
		fontWeight: 'bold',
		fill: '#000',
		width: 250,
		fixedWith: 250,
		left: 500, 
	      top: 530
		
	});
	canvasInscripcion.add(text);
	
	/*ctx.font = "italic 30px Arial";
	ctx.fillStyle = "#484A4B";
	ctx.fillText('Participante', 80, 300);
	*/
	/*var text = new fabric.Textbox("Participante", { 
		fontSize: 20,
		fontFamily: 'Arial',
		fontStyle: 'italic',
		textAlign: 'center',
		fill: '#818386',
		width: 100,
		fixedWith: 150,
		left: 80, 
	      top: 300
		
	});*/
	//canvasInscripcion.add(text);
	
	var text = new fabric.Textbox(p.lugarEducacionContinua, { 
		fontSize: 30,
		fontFamily: 'Berlin Sans FB',
		textAlign: 'left',
		fill: '#000',
		width: 700,
		fixedWith: 700,
		left: 80, 
	      top: 600
		
	});
	canvasInscripcion.add(text);
	
	var text = new fabric.Textbox("Lugar", { 
		fontSize: 27,
		fontFamily: 'Berlin Sans FB',
		fontStyle: 'italic',
		textAlign: 'left',
		fill: '#818386',
		width: 100,
		fixedWith: 150,
		left: 80, 
	      top: 640
		
	});
	canvasInscripcion.add(text);
	
	var text = new fabric.Textbox(fechaE, { 
		fontSize: 30,
		fontFamily: 'Berlin Sans FB',
		textAlign: 'left',
		fill: '#000',
		width: 400,
		fixedWith: 450,
		left: 80, 
	      top: 690
		
	});
	canvasInscripcion.add(text);
	
	
	var text = new fabric.Textbox("Fecha Inicio", { 
		fontSize: 27,
		fontFamily: 'Berlin Sans FB',
		fontStyle: 'italic',
		textAlign: 'left',
		fill: '#818386',
		width: 400,
		fixedWith: 400,
		left: 80, 
	      top: 720
		
	});
	canvasInscripcion.add(text);
	
	var text = new fabric.Textbox(tiempo, { 
		fontSize: 30,
		fontFamily: 'Berlin Sans FB',
		textAlign: 'left',
		fill: '#000',
		width: 400,
		fixedWith: 450,
		left: 450, 
	      top: 690
		
	});
	canvasInscripcion.add(text);
	
	var text = new fabric.Textbox("Hora Inicio", { 
		fontSize: 27,
		fontFamily: 'Berlin Sans FB',
		fontStyle: 'italic',
		textAlign: 'left',
		fill: '#818386',
		width: 400,
		fixedWith: 400,
		left: 450, 
	      top: 720
		
	});
	canvasInscripcion.add(text);
	
	fabric.Image.fromURL(imgQR, function(img) {
		  canvasInscripcion.add(img.set({
			  left: 430, 
		      top: 220
		  }));
		  var imagen=canvasInscripcion.toDataURL({
		        format: 'jpg',
		        quality: 0.8
		    });
			
			var blob = dataURItoBlob(imagen);
			guardarTarjeta(blob,p.id);
			clearCanvas();
	});
	
	
	
	
	
	
}

function guardarTarjeta(imagen,idParticipante){
	var formData = new FormData();
	var newFile = new File([imagen], 'tarjeta.jpg', {type: 'image/jpg'});
	formData.append('file', newFile);
	formData.append('idParticipante', idParticipante);
	$.ajax({
    	headers: {"X-CSRF-TOKEN": token},
    	url: "/preinscripcion/generar-tarjeta-inscripcion",
        type: "POST",
        data: formData,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (res) {
        	var enlace = document.getElementById("btn_inscripcion");
        	if(enlace!=null){
        		toastr.success('Se ha realizado la inscripción con éxito', 'Excelente!');
                
                enlace.innerHTML ="CANCELAR INSCRIPCIÓN!";
                enlace.setAttribute( "onclick","cancelarInscripcion()");
                hideSpinnerInscripcion();
        	}else{//esta en ponentes
        		reloadListPonentes();
        	}
        	loadCantidadInscritos();
            
        },
        error: function (err) {
            console.error(err);
        }
    });
	
	
	
}


function  cancelarInscripcion(){
	showSpinnerInscripcion();
	ajaxCancelarInscripcion();
}

function ajaxCancelarInscripcion(){
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/preinscripcion/cancelar-inscripcion/"+idEduContinua,
		cache: false,
		success: function(result) {
			toastr.success('Se ha cancelado la inscripción con éxito', 'Excelente!');
            var enlace = document.getElementById("btn_inscripcion");
            enlace.innerHTML ="INSCRÍBETE!";
            enlace.setAttribute( "onclick","realizarInscripcion()");
            hideSpinnerInscripcion();
            loadCantidadInscritos();
		},
		error: function(err) {
			hideSpinnerInscripcion();
		}
	});
}

function clearCanvas(){
	canvasInscripcion.clear();
	createTemplateCanvasInscripcion();
}

function loadCantidadInscritos(){
	var url = '/preinscripcion/reload/'+idAccesoEduContinua;
	$('#div_info_educacionContinua').load(url);
}