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
		 validDuracion(horas);
		    
	  });
console.log("preparando datepicker");
	$("#fechaLimInscripcionEduCont").flatpickr({
   		enableTime: true,
   	    dateFormat: "d/m/Y H:i",
   	    minDate: (fLim != null && new Date(fLim)<new Date()) ? new Date(fLim) : new Date(),
   	    defaultDate:fechaLimInscripcionEvento,
   	    maxDate: fechaFinEvento,
   	});
	
	var dates=[];
	dates.push(new Date());
	dates.push(fIni);
	
	
	$("#fechaInicioEduCont").flatpickr({
		enableTime: true,
		dateFormat: "d/m/Y H:i",
		minDate: (fIni != null && new Date(fIni)<new Date()) ? new Date(fIni) : new Date(),
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
	       	    minDate: (fLim != null && new Date(fLim)<new Date()) ? new Date(fLim) : new Date(),
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

function validDuracion(horas){
	
	
	var tipoContinua= $('#selectTipoContinua').find('option:selected');
	var inputDuracion=document.getElementById('duracion');
	var errorDuracion=document.getElementById('errDuracionEdc');
		var boton = document.getElementById('btnGuardarEdC');
		console.log("horas");
		console.log(horas);
		if(tipoContinua.val()==1 || tipoContinua.val()==4){//curso talleres
			console.log("entra por curso");
			console.log(horas<16);
			if(horas.trim()<16){
				errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 16 horas de duración";
				inputDuracion.classList.add("is-invalid");
				boton.disabled=true;
				return false;
			}else{
				errorDuracion.innerText="";
				inputDuracion.classList.remove("is-invalid");
				boton.disabled=false;
				return true;
			}
		}else if(tipoContinua.val()==2){//diplomados
			console.log("horas en diplomados if");
			console.log(horas);
			if(horas.trim()<90){
				errorDuracion.innerText="El " + tipoContinua.text() + " debe tener mínimo 90 horas de duración";
				inputDuracion.classList.add("is-invalid");
				boton.disabled=true;
				return false;
			}else{
				errorDuracion.innerText="";
				inputDuracion.classList.remove("is-invalid");
				boton.disabled=false;
				return true;
			}
		}else{
			if(horas.trim() == ''){
				errorDuracion.innerText="El campo es requerido";
				inputDuracion.classList.add("is-invalid");
				boton.disabled=true;
				return false;
			}else{
				errorDuracion.innerText="";
				inputDuracion.classList.remove("is-invalid");
				boton.disabled=false;
				return true;
			}
			
		}
	
}

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
function showLoader(){
	try {
		document.getElementById("divLoader").style.display='flex';
		document.getElementById("formEducacionContinua").style.display='none';
		document.getElementById("formEducacionContinuaBase").style.display='none';
	} catch (error) {
	  
	}
	
}

function hideLoader(){
	try {
		document.getElementById("divLoader").style.display='none';
		document.getElementById("formEducacionContinua").style.display='inline';
		document.getElementById("formEducacionContinuaBase").style.display='inline';
	} catch (error) {
	  
	}
	
}

function guardarEdc(){
	//showLoader();
	ajaxSaveEducacionContinua();
	
}

function ajaxSaveEducacionContinua(){
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
	  var porcentajeAsistencia=$('#porcentajeAsistenciaEdc').val();
	  var idTipoEduContinua=$('#selectTipoContinua').select2('data')[0].id;
	  var tipoEduContinua=$('#selectTipoContinua').select2('data')[0].text;
	  var idProgramaResponsable=$('#programaResponsable').val();
	  var idDocenteResponsable=$('#docenteResponsable').val();
	  var idClasificacionCine=$('#selectClasificacionCINE').val();
	  var infoAdicional=$('#infoAdicionalEdC').val();
	  console.log(nombre=="" );
	  console.log(fechaInicio=="");
	  console.log( fechaFin=="");
	  console.log(duracion<=0 );
	  console.log(duracion=="");
	  console.log(fechaLimInscripcion=="" );
	  console.log(cantMaxParticipantes);
	  console.log(cantMaxParticipantes<=0 && cantMaxParticipantes!="");
	  console.log(costoInscripcion);
	  console.log(costoInscripcion<=0 && costoInscripcion!="");
	  
	  console.log(lugar=="");
	  console.log(costoEducacionContinua<=0);
	  console.log(costoEducacionContinua=="" );
	  console.log(porcentajeAsistencia <0);
	  console.log(porcentajeAsistencia>100);
	  console.log(porcentajeAsistencia=="");
	  console.log(idTipoEduContinua==0);
	  console.log(idProgramaResponsable==0);
	  console.log(idDocenteResponsable==0);
	  console.log(idClasificacionCine==0);
	  var valid1 = validateInputTextRequerido('nombreEdc','errNombreEdc');
	  var valid2=validateLengthTxt('nombreEdc','errNombreEdc',150);
	  var valid3 = validateInputTextRequerido('fechaInicioEduCont','errFechaInicioEdc');
	  var valid4 = validateInputTextRequerido('fechaFinEduCont','errFechaFinEdc');
	  var valid5 = validateInputTextRequerido('fechaLimInscripcionEduCont','errFechaLimInscEdc');
	  var valid6 = validateInputNumberRequerido('duracion','errDuracionEdc');
	  var valid8 = validateInputNumberRequerido('costoTotalEdc','errCostoTotalEdc');
	  var valid7 = validDuracion(duracion);
	  var valid9 = validateInputNumberNotRequired('costoInscripcionEdc','errCostoInscripEdc');
	  var valid10 = validateInputNumberNotRequired('cantMaxPartEdc','errCantPartEdc');
	  var valid11 = validateInputPorcentaje('porcentajeAsistenciaEdc','errPorcAsisEdc');
	  var valid12 = validateInputTextRequerido('lugarEdc','errLugarEdc');
	  var valid13= validateLengthTxt('lugarEdc','errLugarEdc',40);
	  var valid14 = validateSelect('selectTipoContinua','errTipoEdc');
	  var valid15 = validateSelect('selectTipoBeneficiarios','errTipoBenefEdc');
	  var valid16 = validateSelect('selectClasificacionCINE','errClasificacionEdc');
	  var valid17 = validateSelect('programaResponsable','errProgRespEdc');
	  var valid18 = validateSelect('docenteResponsable','errDocRespEdc');
	  console.log("validaciones");
	  console.log(valid1);
	  console.log(valid2);
	  console.log(valid3);
	  console.log(valid4);
	  console.log(valid5);
	  console.log(valid6);
	  console.log(valid7);
	  console.log(valid8);
	  console.log(valid9);
	  console.log(valid10);
	  console.log(valid11);
	  console.log(valid12);
	  console.log(valid13);
	  console.log(valid14);
	  console.log(valid15);
	  console.log(valid16);
	  console.log(valid17);
	  console.log(valid18);
	  if( !valid1 || !valid2 || !valid3 || !valid4 || !valid5 || !valid6 || !valid7 || !valid8 || !valid9
			  || !valid10 || !valid11 || !valid12 || !valid13 || !valid14 || !valid15 || !valid16 || !valid17 || !valid18){
		 
		  //hideLoader();
		  toastr
			.error(
					'Debes diligenciar el formulario correctamente',
					'Error!');
					return;
	  }
	  
	  var imagen = $('#inputImagenEvento')[0].files;
	  console.log(imagen[0]);
	  var formData = new FormData();
	  console.log("idddddddddddddddddddddd");
	  console.log(idEduContinua);
	  formData.append('imagen',imagen[0]);
	  formData.append('id',idEduContinua);
	  formData.append('nombre',nombre);
	  formData.append('fechaInicio',fechaInicio);
	  formData.append('fechaFin',fechaFin);
	  formData.append('duracion',duracion);
	  formData.append('fechaLimInscripcion',fechaLimInscripcion);
	  if(cantMaxParticipantes.trim() != ''){
		  formData.append('cantMaxParticipantes',cantMaxParticipantes);
	  }
	  if(costoInscripcion.trim() != ''){
		  formData.append('costoInscripcion',costoInscripcion);
	  }
	  
	  
	  formData.append('lugar',lugar);
	  formData.append('costoEducacionContinua',costoEducacionContinua);
	  formData.append('porcentajeAsistencia',porcentajeAsistencia);
	  formData.append('idTipoEduContinua',idTipoEduContinua);
	  formData.append('tipoEduContinua',tipoEduContinua);
	  formData.append('idProgramaResponsable',idProgramaResponsable);
	  formData.append('idDocenteResponsable',idDocenteResponsable);
	  formData.append('idClasificacionCine',idClasificacionCine);
	  formData.append('idTipoBeneficiarios',idTipoBeneficiarios);
	  formData.append('infoAdicional',infoAdicional);
	  if(consecutivo != undefined){
		  formData.append('consecutivo',consecutivo);
	  }
	  
	  console.log("analizando formData");
	  console.log(formData);
	  showSpinnerModal("btnGuardarEdC","btnSpinnerEdC");
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
			
			if(idEduContinua==0){
				window.setTimeout(function() {
					window.location.href = "/educacion-continua";
				}, 1000);
			}else{
				reloadDetalles();
				//hideLoader();
				hideSpinnerModal("btnGuardarEdC","btnSpinnerEdC");
				$('[href="#pills-detalles"]').tab('show');
			}
			
			
		},
		error: function(err) {
			hideSpinnerModal("btnGuardarEdC","btnSpinnerEdC");
			console.log(err);
			//hideLoader();
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

function findEducacionContinuaBase(nombre){
	console.log("id base");
	console.log(nombre);
	$.ajax({
		headers: {"X-CSRF-TOKEN": token},
		url: "/educacion-continua/search-base",
		type: "GET",
        data: {'nombreEdC':nombre},
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
				var newOption = new Option(element, element, false, false);
				$('#selectEdCBase').append(newOption);
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
	$('#infoAdicionalEdC').summernote('code', e.infoAdicional);
	$('#selectTipoContinua').val(e.idTipoEduContinua).trigger('change');
	$('#selectClasificacionCINE').val(e.idClasificacion).trigger('change');
	$('#programaResponsable').val(e.idProgramaResp).trigger('change');
	$('#docenteResponsable').val(e.idDocenteResp).trigger('change');
	console.log("idProgramaResp");
	console.log(e.idProgramaResp);
	console.log("idDocenteResp");
	console.log(e.idDocenteResp)
	var idsTB= [];
	console.log("tipo beneficiarios");
	console.log(e.tipoBeneficiarios);
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

function reloadDetalles(){
	var urlReload = '/educacion-continua/detalles/reload/'+eduContinua.idAcceso;
	$('#detallesEdC').load(urlReload);
}