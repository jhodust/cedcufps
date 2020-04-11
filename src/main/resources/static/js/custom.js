
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



$(document).ready(function ()
{
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


