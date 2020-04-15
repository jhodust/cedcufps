
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
$(document).ready(function () {
	 c = document.getElementById("myCanvas");
	 ctx= c.getContext("2d");
	
	ctx.fillStyle = "#FFF";
	ctx.fillRect(0, 0, c.width, c.height);
	
	var img = new Image();
	img.src="/img/tarjeta_inscripcion.png";
	img.onload = function() {
	    ctx.drawImage(img, 0, 0, c.width, c.height);
	    
	}
	
	var img2 = new Image();
	img2.src="/logos/logo-ufps-vertical.jpg";
	img2.onload = function() {
	    ctx.drawImage(img2, 50, 50, 236.22, 173.23);
	    
	}

	
	
});


function realizarInscripcion(){
	
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/realizar-inscripcion/"+idEduContinua,
		cache: false,
		success: function(result) {
			creacionTarjetaInscripcion(result);
			console.log(result);
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
		
}

function creacionTarjetaInscripcion(p){
	var educacionContinua = p.educacionContinua.nombre;
    var primerNombre = p.persona.primerNombre;
    var segundoNombre = p.persona.segundoNombre;
    var primerApellido = p.persona.primerApellido;
    var segundoApellido = p.persona.segundoApellido;
    var tipoParticipante = p.tipoParticipante.tipoParticipante;
    var lugar = p.educacionContinua.lugar;
    var fecha=new Date(p.educacionContinua.fechaInicio);
    var imgQR=p.imagenCodigoQR;
    var tipoEduContinua=p.educacionContinua.tipoEduContinua.tipoEduContinua;
    
    var nombres="";
    if(segundoNombre!=null){
    	nombres= primerNombre + " " + segundoNombre;
    }else{
    	nombres= primerNombre;
    }
   
	var apellidos = primerApellido + " " + segundoApellido;
	
	ctx.font = "italic 30px Arial";
	ctx.fillStyle = "#484A4B";
	ctx.fillText('Participante', 80, 300);
	
	ctx.font = "normal 38px Berlin Sans FB";
	ctx.fillStyle = "#000";
	ctx.fillText(nombres, 80, 350);
	
	ctx.font = "normal 38px Berlin Sans FB";
	ctx.fillText(apellidos, 80, 390);
	
	ctx.font = "800 38px Arial";
	ctx.fillText(tipoParticipante.toUpperCase(), 120, 480);
	
	
	
	ctx.font = "italic 30px Arial";
	ctx.fillStyle = "#484A4B";
	ctx.fillText('Lugar', 80, 580);
	
	
	ctx.font = "normal 33px Berlin Sans FB";
	ctx.fillStyle = "#000";
	ctx.fillText(lugar, 80, 620);
	
	ctx.font = "italic 30px Arial";
	ctx.fillStyle = "#484A4B";
	ctx.fillText('Fecha Inicio', 80, 680);
	
	ctx.font = "normal 33px Berlin Sans FB";
	ctx.fillStyle = "#000";
	ctx.fillText(fecha.getDate() + " " + meses[fecha.getMonth()] + " "+ fecha.getFullYear(), 80, 720);
	
	
	
	ctx.font = "italic 30px Arial";
	ctx.fillStyle = "#484A4B";
	ctx.fillText('Hora Inicio', 450, 680);
	
	ctx.font = "normal 33px Berlin Sans FB";
	ctx.fillStyle = "#000";
	if(fecha.getHours()<12){
		ctx.fillText(fecha.getHours()+":"+fecha.getMinutes()+" A.M", 450, 720);
	}else{
		ctx.fillText(fecha.getHours()+":"+fecha.getMinutes(), 450, 720);
	}
	
	
	ctx.font = "800 38px Arial";
	ctx.textAlign = "center";
	ctx.fillText(tipoEduContinua.toUpperCase(), 580, 550);
	
	
	var array= educacionContinua.split(" ");
    var cantCaracteres=0;
    var y=0;
    var z=0;
    var cad="";
    console.log("longitud nombre: " + educacionContinua.length);
    if(educacionContinua.length<25){
    	var height=120;
    	ctx.font = "bold 38px Trebuchet MS";
    }else if(educacionContinua.length<=50){
    	var height=100;
    	ctx.font = "bold 36px Trebuchet MS";
    }else if(educacionContinua.length<100){
    	var height=80;
    	ctx.font = "bold 32px Trebuchet MS";
    }
    for (var i=0; i<array.length; i++){
    	console.log("i: " +i + " contenido: " + array[i] + " " + array[i].length);
    	
    	if((cantCaracteres+array[i].length)<25){
    		
    		cantCaracteres=cantCaracteres+array[i].length;
    		cantCaracteres=cantCaracteres+1;//espacio
    		console.log("cant:" + cantCaracteres);
    	}else{
    		
    		z=z+cantCaracteres;
    		
    		cantCaracteres=0;
    		console.log("i: "+ i + " z: " + z);
    		cad=educacionContinua.substring(y,z);
    		console.log(educacionContinua.substring(y,z));
    		ctx.fillText(cad, 530, height);
    		console.log("escribe");
    		height=height+35;
    		y=z;
    		i=i-1;
    	}
    	if(i==array.length-1){
			z=z+cantCaracteres;
    		
    		cantCaracteres=0;
    		console.log("i: "+ i + " z: " + z);
    		cad=educacionContinua.substring(y,z);
    		console.log(educacionContinua.substring(y,z));
    		ctx.fillText(cad, 530, height);
    		console.log("escribe");
    		height=height+35;
    		y=z;
    	}
    	
    }
    
    var img3 = new Image();
	img3.src=imgQR;
	img3.onload = function() {
	    ctx.drawImage(img3, 430, 220, 300, 300);
	    var dataURL = c.toDataURL('image/jpg');
		var blob = dataURItoBlob(dataURL);
		guardarTarjeta(blob,p.id);
	    
	}
    
}

function guardarTarjeta(imagen,idParticipante){
	var formData = new FormData();
	var newFile = new File([imagen], 'tarjeta.jpg', {type: 'image/jpg'});
	formData.append('file', newFile);
	formData.append('idParticipante', idParticipante);
	$.ajax({
    	headers: {"X-CSRF-TOKEN": token},
    	url: "/realizar-inscripcion/generar-tarjeta-inscripcion",
        type: "POST",
        data: formData,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (res) {
            toastr.success('Se ha realizado la inscripción con éxito', 'Excelente!');
            var enlace = document.getElementById("btn_inscripcion");
            enlace.innerHTML ="CANCELAR INSCRIPCIÓN!";
            enlace.setAttribute( "onclick","cancelarInscripcion()");
        },
        error: function (err) {
            console.error(err);
        }
    });
	
	
	
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

function  cancelarInscripcion(){
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: "/cancelar-inscripcion/"+idEduContinua,
		cache: false,
		success: function(result) {
			toastr.success('Se ha cancelado la inscripción con éxito', 'Excelente!');
            var enlace = document.getElementById("btn_inscripcion");
            enlace.innerHTML ="INSCRÍBETE!";
            enlace.setAttribute( "onclick","realizarInscripcion()");
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Programa is required</span>" );
		}
	});
}