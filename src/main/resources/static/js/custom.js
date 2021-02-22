var months=["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
toastr.options = {
		  "closeButton": true,
		  "debug": false,
		  "newestOnTop": false,
		  "progressBar": false,
		  "positionClass": "toast-bottom-right",
		  "preventDuplicates": false,
		  "onclick": null,
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "5000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
		}

var token = $("meta[name='_csrf']").attr("content");

$(document).ready(function ()
{
	$('[data-toggle="tooltip"]').tooltip();
	
	$('[data-toggle="popover"]').popover();
	
	var swiper = new Swiper('.swiper-container', {
	      effect: 'coverflow',
	      grabCursor: true,
	      centeredSlides: true,
	      slidesPerView: 'auto',
	      coverflowEffect: {
	        rotate: 25,
	        stretch: 0,
	        depth: 250,
	        modifier: 1,
	        slideShadows : true,
	      },
	      autoplay: {
	          delay: 2500,
	          disableOnInteraction: false,
	        },
	        loop: true,
	      pagination: {
	        el: '.swiper-pagination',
	      },
	    });
	/*
	var swiper = new Swiper('.swiper-container', {
	      slidesPerView: 1,
	      spaceBetween: 30,
	      loop: true,
	      pagination: {
	        el: '.swiper-pagination',
	        clickable: true,
	      },
	      navigation: {
	        nextEl: '.swiper-button-next',
	        prevEl: '.swiper-button-prev',
	      },
	      autoplay: {
	          delay: 2500,
	          disableOnInteraction: false,
	        },
	    });
		*/
	
	
});


function logout(){
	document.getElementById('formLogout').submit();
}

function ocultar(id) {// oculta un elemento del formulario
	document.getElementById(id).style.display = 'none';
}
function mostrar(id,style) {// muestra un elemento del formulario
	document.getElementById(id).style.display = style;
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


function updateInfoDiploma(nombre, documento, tipoParticipante, canvasObject){
	console.log("actualizando info diploma");
	console.log(nombre);
	console.log(documento);
	console.log(tipoParticipante);
	console.log(JSON.stringify(canvasObject));
	console.log(canvasObject.getObjects());
	canvasObject.getObjects().forEach(function(o){
		console.log(o);
		if(o.type=="textbox"){
			  var regex = /%nombreAsistente%/gi;
			  o.text=o.text.replace(regex, nombre);
			  
			  var regex = /%documentoAsistente%/gi;
			  o.text=o.text.replace(regex, documento);
			  
			  var regex = /%tipoAsistente%/gi;
			  o.text=o.text.replace(regex, tipoParticipante);
			  canvasObject.renderAll();
		  }
	});
}

function paginadorLoad(element){
	paginadorLoadAjax(element.dataset.url);
}

function paginadorLoadAjax(url){
	$('#div_list_paginador').load(url);
}

function showSpinnerModal(idBtnClick, idSpinner){
	document.getElementById(idSpinner).style.display='flex';
	document.getElementById(idBtnClick).style.display='none';
}

function hideSpinnerModal(idBtnClick, idSpinner){
	document.getElementById(idSpinner).style.display='none';
	document.getElementById(idBtnClick).style.display='inline';
}
