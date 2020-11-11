var token = $("meta[name='_csrf']").attr("content");
$(document).ready(function ()
{
	
	$('#selectEdCBase').on('select2:select', function (e) { 
		console.log(e);
		findEducacionContinuaBase(e.params.data.id);
	});
	
	$('#selectProgramaBase').on('select2:select', function (e) { 
		console.log(e.params.data);
		loadListEduContinuaBase(e.params.data.id);
	});
	
	$("#selectTipoContinua").select2({
		  tags: true,
		  createTag: function (params) {
			    console.log(params);
			    return {
			      id: -1,
			      text: params.term,
			      newTag: true // add additional parameters
			    }
			  }
		});
	
	$('#selectTipoContinua').on("change", function (e) { 
		e.preventDefault();
		validateSelect('selectTipoContinua','errTipoEdc');
		var tipoContinua= $(this).find('option:selected');
		var inputDuracion=document.getElementById('duracion');
		var errorDuracion=document.getElementById('errDuracionEdc');
		var boton = document.getElementById('btnGuardarEdC');
		console.log("error");
		console.log(errorDuracion);
		console.log(inputDuracion);
		console.log(inputDuracion.value);
		console.log(tipoContinua.val());
		if(tipoContinua.val()=="1" || tipoContinua.val()=="4"){//curso talleres
			console.log("entra por curso");
			if(inputDuracion.value<16){
				errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 16 horas de duración";
				inputDuracion.classList.add("is-invalid");
				boton.disabled=true;
			}
		}else if(tipoContinua.val()==2){//diplomados
			if(inputDuracion.value<90){
				errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 90 horas de duración";
				inputDuracion.classList.add("is-invalid");
				boton.disabled=true;
			}
		}else{
			errorDuracion.innerText="";
			inputDuracion.classList.remove("is-invalid");
			boton.disabled=false;
		}
		
	});
	
	$('#selectClasificacionCINE').on("change", function (e) { 
		e.preventDefault();
		validateSelect('selectClasificacionCINE','errClasificacionEdc');
	});
	
	$('#programaResponsable').on("change", function (e) { 
		e.preventDefault();
		validateSelect('programaResponsable','errProgRespEdc');
	});
	
	$('#docenteResponsable').on("change", function (e) { 
		e.preventDefault();
		validateSelect('docenteResponsable','errDocRespEdc');
	});
	
	$('#selectTipoBeneficiarios').on("change", function (e) { 
		e.preventDefault();
		validateSelect('selectTipoBeneficiarios','errTipoBenefEdc');
	});
	
	 $('#duracion').keyup(function(event) {
		 var horas = event.target.value;
		 var tipoContinua= $('#selectTipoContinua').find('option:selected');
		var inputDuracion=document.getElementById('duracion');
		var errorDuracion=document.getElementById('errDuracionEdc');
			var boton = document.getElementById('btnGuardarEdC');
			console.log("horas");
			console.log(horas);
			if(tipoContinua.val()==1 || tipoContinua.val()==4){//curso talleres
				console.log("entra por curso");
				console.log(horas<16);
				if(horas<16){
					errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 16 horas de duración";
					inputDuracion.classList.add("is-invalid");
					boton.disabled=true;
				}else{
					errorDuracion.innerText="";
					inputDuracion.classList.remove("is-invalid");
					boton.disabled=false;
				}
			}else if(tipoContinua.val()==2){//diplomados
				console.log("horas en diplomados if");
				console.log(horas);
				if(horas<90){
					errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 90 horas de duración";
					inputDuracion.classList.add("is-invalid");
					boton.disabled=true;
				}else{
					errorDuracion.innerText="";
					inputDuracion.classList.remove("is-invalid");
					boton.disabled=false;
				}
			}else{
				errorDuracion.innerText="";
				inputDuracion.classList.remove("is-invalid");
				boton.disabled=false;
			}
		    
	  });
	
console.log("preparando datepicker");
	$("#fechaLimInscripcionEduCont").flatpickr({
   		enableTime: true,
   	    dateFormat: "d/m/Y H:i",
   	    defaultDate:fechaLimInscripcionEvento,
   	    maxDate: fechaFinEvento,
   	});
	
	$("#fechaInicioEduCont").flatpickr({
		enableTime: true,
		dateFormat: "d/m/Y H:i",
		defaultDate:fechaInicioEvento,
	    plugins: [new rangePlugin({ 
	    							input: "#fechaFinEduCont",
	    							enableTime: true,
	    						    dateFormat: "d/m/Y H:i",
	    							defaultDate:fechaFinEvento,
	    							})],
		onChange: function(selectedDates, dateStr, instance) {
		    	console.log(selectedDates);
		    	console.log(selectedDates[0].toDateString());
		    	console.log(dateStr);
		    	console.log(selectedDates[0].getDate());
		    	console.log("toString:" + selectedDates[0].toString());
		    	console.log("dia: " + selectedDates[0].getDay());
		    	console.log("mes: " + selectedDates[0].getMonth());
		    	console.log("UTCMONTH " + selectedDates[0].getUTCMonth());
		    	console.log("fullYear: " + selectedDates[0].getFullYear());
		    	console.log("años: " + selectedDates[0].getYear());
		    	console.log("horas: "+ selectedDates[0].getHours());
		    	console.log("toLocaleString: "+selectedDates[0].toLocaleString());
		    	console.log("toLocaleDateString: "+selectedDates[0].toLocaleDateString());
		    	
		    	
		    	console.log("minDate: "+selectedDates[0].toDateString());
	           
		    	$("#fechaLimInscripcionEduCont").flatpickr({
	       		enableTime: true,
	       	    dateFormat: "d/m/Y H:i",
	       	    defaultDate:fechaLimInscripcionEvento,
	       	    maxDate: selectedDates[1].toLocaleDateString(),
	       	});
		}
	});
	
	$("#cbEdCExistente").click(function() {
		if(document.getElementById("cbEdCExistente").checked){
	  		document.getElementById("divEdCBase").style.display='block';
	  	}else{
	  		$('#selectProgramaBase').val('0').trigger('change');
	  		clearSelectEduContinuaBase()
	  		clearEduContinuaBase();
	  		document.getElementById("divEdCBase").style.display='none';
	  		
	  	}
		clearErrors();
	})
});

var data = {
	    id: 1,
	    text: 'Barn owl'
	};

var idEduContinua=0;
var nombre;
var fechaInicio;
var fechaFin;
var duracion;
var fechaLimInscripcion;
var cantMaxParticipantes;
var costoInscripcion;
var lugar;
var costoEducacionContinua;
var requisitos;
var objetivo;
var porcentajeAsistencia;
var resumen;
var contenidoGeneral;
var idTipoEduContinua;
var tipoEduContinua;
var idProgramaResponsable;
var idDocenteResponsable;
var idClasificacionCine;
var consecutivo;
var idTipoBeneficiarios=[];
function guardarEdc(){
	  listTipoBen=[];
	  
	  $('#selectTipoBeneficiarios').val().forEach(element => {
	  listTipoBen.push(element);
	  });
	 idTipoBeneficiarios=listTipoBen;
	  var nombre=$('#nombreEdc').val();
	  var fechaInicio=$('#fechaInicioEduCont').val();
	  var fechaFin=$('#fechaFinEduCont').val();
	  var duracion=$('#duracion').val();
	  var fechaLimInscripcion=$('#fechaLimInscripcionEduCont').val();
	  var cantMaxParticipantes=$('#cantMaxPartEdc').val();
	  var costoInscripcion=$('#costoInscripcionEdc').val();
	  var lugar=$('#lugarEdc').val();
	  var costoEducacionContinua=$('#costoTotalEdc').val();
	  var requisitos=$('#requisitosEdc').val();
	  var objetivo=$('#objetivoEdc').val();
	  var porcentajeAsistencia=$('#porcentajeAsistenciaEdc').val();
	  var resumen=$('#resumenEdc').val();
	  var contenidoGeneral=$('#contenidoGeneralEdc').val();
	  var idTipoEduContinua=$('#selectTipoContinua').select2('data')[0].id;
	  var tipoEduContinua=$('#selectTipoContinua').select2('data')[0].text;
	  var idProgramaResponsable=$('#programaResponsable').val();
	  var idDocenteResponsable=$('#docenteResponsable').val();
	  var idClasificacionCine=$('#selectClasificacionCINE').val();
	  if(nombre=="" || fechaInicio=="" || fechaFin=="" || duracion<=0 || duracion=="" || fechaLimInscripcion=="" ||
			  cantMaxParticipantes<=0 || costoInscripcion<=0 || lugar=="" || costoEducacionContinua<=0 || costoEducacionContinua=="" ||
			  porcentajeAsistencia <0 || porcentajeAsistencia>100 || porcentajeAsistencia=="" || idTipoEduContinua==0 || 
			  idProgramaResponsable==0 || idDocenteResponsable==0 || idClasificacionCine==0){
		  validateInputTextRequerido('nombreEdc','errNombreEdc');
		  validateInputTextRequerido('fechaInicioEduCont','errFechaInicioEdc');
		  validateInputTextRequerido('fechaFinEduCont','errFechaFinEdc');
		  validateInputTextRequerido('fechaLimInscripcionEduCont','errFechaLimInscEdc');
		  validateInputNumberRequerido('duracion','errDuracionEdc');
		  validateInputNumberRequerido('costoTotalEdc','errCostoTotalEdc');
		  validateInputNumberNotRequired('costoInscripcionEdc','errCostoInscripEdc');
		  validateInputNumberNotRequired('cantMaxPartEdc','errCantPartEdc');
		  validateInputPorcentaje('porcentajeAsistenciaEdc','errPorcAsisEdc');
		  validateInputTextRequerido('lugarEdc','errLugarEdc');
		  validateSelect('selectTipoContinua','errTipoEdc');
		  validateSelect('selectTipoBeneficiarios','errTipoBenefEdc');
		  validateSelect('selectClasificacionCINE','errClasificacionEdc');
		  validateSelect('programaResponsable','errProgRespEdc');
		  validateSelect('docenteResponsable','errDocRespEdc');
		  toastr
			.error(
					'Debes ingresar todos los campos requeridos',
					'Error!');
					return;
	  }
	  
	  var imagen = $('#inputImagenEvento')[0].files;
	  console.log(imagen[0]);
	  var formData = new FormData();
	  formData.append('imagen',imagen[0]);
	  formData.append('id',idEduContinua);
	  formData.append('nombre',nombre);
	  formData.append('fechaInicio',fechaInicio);
	  formData.append('fechaFin',fechaFin);
	  formData.append('duracion',duracion);
	  formData.append('fechaLimInscripcion',fechaLimInscripcion);
	  formData.append('cantMaxParticipantes',cantMaxParticipantes);
	  formData.append('costoInscripcion',costoInscripcion);
	  formData.append('lugar',lugar);
	  formData.append('costoEducacionContinua',costoEducacionContinua);
	  formData.append('requisitos',requisitos);
	  formData.append('objetivo',objetivo);
	  formData.append('porcentajeAsistencia',porcentajeAsistencia);
	  formData.append('resumen',resumen);
	  formData.append('contenidoGeneral',contenidoGeneral);
	  formData.append('idTipoEduContinua',idTipoEduContinua);
	  formData.append('tipoEduContinua',tipoEduContinua);
	  formData.append('idProgramaResponsable',idProgramaResponsable);
	  formData.append('idDocenteResponsable',idDocenteResponsable);
	  formData.append('idClasificacionCine',idClasificacionCine);
	  formData.append('idTipoBeneficiarios',idTipoBeneficiarios);
	  if(consecutivo != undefined){
		  formData.append('consecutivo',consecutivo);
	  }
	  
	  
	  console.log(formData);
  $.ajax({
		headers: {"X-CSRF-TOKEN": token},
		url: "/educacion-continua/save",
		type: "POST",
        data: formData,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
		success: function(result) {
			console.log(result);
			toastr.success('Se ha guardado la información', 'Excelente!');
			//window.setTimeout(function(){location.reload()},1000);
			idEduContinua=0;
		},
		error: function(err) {
			console.log(err);
			if(err.responseJSON.length >0){
				toastr.error('No se pudo procesar la solicitud...', 'Error!');
				err.responseJSON.forEach(function(error){
					if(error.field=="facultad"){
						var inputFacultad=document.getElementById('facultad');
						var errorFacultad=document.getElementById('errorFacultad');
						errorFacultad.innerText=error.defaultMessage;
						inputFacultad.classList.add("is-invalid");
					}
				  });
			  
			}else{
				toastr.error(err.responseJSON.message, 'Error!');
				
			}
		}
	});
	
	
}

function findEducacionContinuaBase(id){
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		url: "/educacion-continua/search-base",
		type: "GET",
        data: {'idEducacionContinua':id},
        contentType: "application/json; charset=utf-8",
        cache: false,
		success: function(result) {
			console.log("resultado find base");
			console.log(result);
			loadEduContinuaBase(result);
			
		},
		error: function(err) {
			console.log(err);
			
		}
	});
}

function loadListEduContinuaBase(idPrograma){
	console.log("cargando");
	clearSelectEduContinuaBase()
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		url: "/educacion-continua/search-educaciones-continuas-base",
		type: "GET",
        data: {'idPrograma':idPrograma},
        contentType: "application/json; charset=utf-8",
        cache: false,
		success: function(result) {
			console.log(result);
			result.forEach(function(element){
				console.log(element);
				var newOption = new Option(element.nombre, element.id, false, false);
				$('#selectEdCBase').append(newOption).trigger('change');
			});
		},
		error: function(err) {
			console.log(err);
			
		}
	});
	
	
}

function clearSelectEduContinuaBase(){
	$('#selectEdCBase').empty().trigger("change");
	var newOption = new Option('Seleccione...', '0', false, false);
	$('#selectEdCBase').append(newOption).trigger('change');
}
function loadEduContinuaBase(e){
	$('#nombreEdc').val(e.nombre);
	$('#duracion').val(e.duracion);
	$('#costoTotalEdc').val(e.costoEducacionContinua);
	$('#costoInscripcionEdc').val(e.costoInscripcion);
	$('#cantMaxPartEdc').val(e.cantMaxParticipantes);
	$('#porcentajeAsistenciaEdc').val(e.porcentajeAsistencia);
	$('#lugarEdc').val(e.lugar);
	$('#requisitosEdc').val(e.requisitos);
	$('#objetivoEdc').val(e.objetivo);
	$('#contenidoGeneralEdc').val(e.contenidoGral);
	$('#resumenEdc').val(e.resumen);
	$('#selectTipoContinua').val(e.idTipoEduContinua).trigger('change');
	$('#selectClasificacionCINE').val(e.idClasificacion).trigger('change');
	$('#programaResponsable').val(e.idProgramaResp).trigger('change');
	$('#docenteResponsable').val(e.idDocenteResp).trigger('change');
	console.log("idProgramaResp");
	console.log(e.idProgramaResp);
	console.log("idDocenteResp");
	console.log(e.idDocenteResp)
	var idsTB= [];
    e.tipoBeneficiarios.forEach(element => idsTB.push(element.id));
    $('#selectTipoBeneficiarios').val(idsTB).trigger('change');
    consecutivo=e.consecutivo;
}

function clearEduContinuaBase(){
	$('#nombreEdc').val('');
	$('#duracion').val('');
	$('#costoTotalEdc').val('');
	$('#costoInscripcionEdc').val('');
	$('#cantMaxPartEdc').val('');
	$('#porcentajeAsistenciaEdc').val('');
	$('#lugarEdc').val('');
	$('#requisitosEdc').val('');
	$('#objetivoEdc').val('');
	$('#contenidoGeneralEdc').val('');
	$('#resumenEdc').val('');
	$('#selectTipoContinua').val('0').trigger('change');
	$('#selectClasificacionCINE').val('0').trigger('change');
	$('#programaResponsable').val('0').trigger('change');
    $('#selectTipoBeneficiarios').val([]).trigger('change');
    consecutivo=undefined;
}

function clearErrors(){
	cleanError('selectTipoContinua','errTipoEdc');
	cleanError('nombreEdc','errNombreEdc');
	cleanError('selectClasificacionCINE','errClasificacionEdc');
	cleanError('fechaInicioEduCont','errFechaInicioEdc');
	cleanError('fechaFinEduCont','errFechaFinEdc');
	cleanError('duracion','errDuracionEdc');
	cleanError('costoTotalEdc','errCostoTotalEdc');
	cleanError('programaResponsable','errProgRespEdc');
	cleanError('docenteResponsable','errDocRespEdc');
	cleanError('selectTipoBeneficiarios','errTipoBenefEdc');
	cleanError('fechaLimInscripcionEduCont','errFechaLimInscEdc');
	cleanError('costoInscripcionEdc','errCostoInscripEdc');
	cleanError('cantMaxPartEdc','errCantPartEdc');
	cleanError('porcentajeAsistenciaEdc','errPorcAsisEdc');
	cleanError('lugarEdc','errLugarEdc');
	
}