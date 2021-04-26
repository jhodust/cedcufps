var token = $("meta[name='_csrf']").attr("content");
$(document).ready(function ()
		{

	$('#selectProgramaStatistics').on("change", function (e) { 
		e.preventDefault();
		validateGenerarStatistics();
		
	});
	
$("#fechaInicioReporteDashboard").flatpickr({
	dateFormat: "d/m/Y",
	onChange: function(selectedDates, dateStr, instance) {
			document.getElementById('errFechaInicioDashboard').innerText='';
	    	$("#fechaFinReporteDashboard").flatpickr({
       		dateFormat: "d/m/Y",
       	    minDate: selectedDates[0].toLocaleDateString(),
       	    onChange: function(selectedDates, dateStr, instance) {
       	    	document.getElementById('errFechaFinDashboard').innerText='';
       	    	validateGenerarStatistics();
       	    }
       	});
	}
});


function validateGenerarStatistics(){
	var fechaInicio=$("#fechaInicioReporteDashboard").val();
	var fechaFin=$("#fechaFinReporteDashboard").val();
	var generar = true;
	if(fechaInicio == ""){
		document.getElementById('errFechaInicioDashboard').innerText="Debes ingresar la fecha inicio del reporte";
		generar=false;
	}
	
	if(fechaFin == ""){
		document.getElementById('errFechaFinDashboard').innerText="Debes ingresar la fecha fin del reporte";
		generar=false;
	}
	if(generar){
		
		createStatistics();
	}
}

function createStatistics(){
	showLoader();
	// Load the Visualization API and the controls package.
    google.charts.load('current', {'packages':['corechart', 'controls'],'language': 'es'});
    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawDashboardGeneral);

    google.charts.load('current', {'packages':['corechart', 'controls'],'language': 'es'});
    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawDashboardGeneralPersonas);
    
    
    
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChartGeneralGender);
    
    hideLoader();
}


function drawDashboardGeneral() {

	var fechaInicio=$("#fechaInicioReporteDashboard").val();
	var fechaFin=$("#fechaFinReporteDashboard").val();
	var idPrograma=$('#selectProgramaStatistics').val();
	var data={};
	  data.fechaInicio=fechaInicio;
	  data.fechaFin=fechaFin;
	if(idPrograma != '0'){
		data.idPrograma=idPrograma;
	}
	
  // Create our data table.
  var jsonData = $.ajax({
    url: "/reportes-SNIES/dashboard/totalGeneralEduContinua",
    data: data,
    dataType: "json",
    async: false
    }).responseText;
  var data = google.visualization.arrayToDataTable($.parseJSON(jsonData));

	      var options = {
	        title: 'Total Educaciones Continuas realizadas',
	        colors: ['#b0120a', '#ffab91'],
	        hAxis: {
	          title: 'Total',
	          minValue: 0
	        },
	        vAxis: {
	          title: 'Educación Continua'
	        }
	      };

  // Create a dashboard.
  var dashboard = new google.visualization.Dashboard(
      document.getElementById('dashboard_div'));

  // Create a range slider, passing some options
  var donutRangeSlider = new google.visualization.ControlWrapper({
    'controlType': 'CategoryFilter',
    'containerId': 'filter_div',
    'options': {
      'filterColumnLabel': 'Educación Continua'
    }
  });

  // Create a pie chart, passing some options
  var pieChart = new google.visualization.ChartWrapper({
    'chartType': 'BarChart',
    'containerId': 'chart_div',
    'options': options
  });

  // Establish dependencies, declaring that 'filter' drives 'pieChart',
  // so that the pie chart will only display entries that are let through
  // given the chosen slider range.
  dashboard.bind(donutRangeSlider, pieChart);

  // Draw the dashboard.
  dashboard.draw(data);
}



function drawDashboardGeneralPersonas() {
	var fechaInicio=$("#fechaInicioReporteDashboard").val();
	var fechaFin=$("#fechaFinReporteDashboard").val();
	var idPrograma=$('#selectProgramaStatistics').val();
	var data={};
	  data.fechaInicio=fechaInicio;
	  data.fechaFin=fechaFin;
	if(idPrograma != '0'){
		data.idPrograma=idPrograma;
	}
	
	
  var jsonData = $.ajax({
      url: "/reportes-SNIES/dashboard/totalGeneralEduContinuaPersonas",
      data: data,
      dataType: "json",
      async: false
      }).responseText;
    var data2 = google.visualization.arrayToDataTable($.parseJSON(jsonData));
  
 var donutRangeSlider2 = new google.visualization.ControlWrapper({
      'controlType': 'CategoryFilter',
      'containerId': 'filter_div2',
      'options': {
        'filterColumnLabel': 'Educación Continua'
      }
    });
  
  var options2 = {
          title : 'Participación en Educaciones Continuas',
          vAxis: {title: 'Total'},
          hAxis: {title: 'Educación Continua'},
          seriesType: 'bars',
          series: {5: {type: 'line'}},
          legend: {position: 'top'}
        };
  
  var dashboard2 = new google.visualization.Dashboard(
          document.getElementById('dashboard_div2'));
  
  var comboChart = new google.visualization.ChartWrapper({
      'chartType': 'ComboChart',
      'containerId': 'chart_div_2',
      'options':options2
    });

  
  
  dashboard2.bind(donutRangeSlider2, comboChart);

  // Draw the dashboard.
  dashboard2.draw(data2);
}


function drawChartGeneralGender() {
	var fechaInicio=$("#fechaInicioReporteDashboard").val();
	var fechaFin=$("#fechaFinReporteDashboard").val();
	var idPrograma=$('#selectProgramaStatistics').val();
	var data={};
	  data.fechaInicio=fechaInicio;
	  data.fechaFin=fechaFin;
	if(idPrograma != '0'){
		data.idPrograma=idPrograma;
	}
	  var jsonData = $.ajax({
          url: "/reportes-SNIES/dashboard/totalGeneralEduContinuaGenero",
          data: data,
          dataType: "json",
          async: false
          }).responseText;
	  var data = google.visualization.arrayToDataTable($.parseJSON(jsonData));
   

    var options = {
      title: 'Participación en educación continua según género',
      legend: {position: 'bottom'}
    };

    var chart = new google.visualization.PieChart(document.getElementById('chart_div_general_gender'));

    chart.draw(data, options);
  }

function showLoader(){
	document.getElementById("divLoader").style.display='flex';
	document.getElementById("statistics").style.display='none';
	document.getElementById("textLoader").innerText='Cargando...';
}

function hideLoader(){
	document.getElementById("divLoader").style.display='none';
	document.getElementById("statistics").style.display='block';
}

		})
		
