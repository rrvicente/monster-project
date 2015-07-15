var chartLabels = null;
var chart_data1 = null;
var chart_data2 = null;
var lineChartData = null;
var dadosDoRadarChart = null;
var opcoesDoRadarChart = null;

function loadChartData() {

	var json_data = document.getElementById("chart_data").value;
	var jsonObj = JSON.parse(json_data);

	chartLabels = jsonObj.labels;
	chart_data1 = jsonObj.dataSet1
	chart_data2 = jsonObj.dataSet2

	lineChartData = {
		labels : chartLabels,
		datasets : [ {
			label : "My First dataset",
			fillColor : "rgba(220,220,220,0.2)",
			strokeColor : "rgba(220,220,220,1)",
			pointColor : "rgba(220,220,220,1)",
			pointStrokeColor : "#fff",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(220,220,220,1)",
			data : chart_data1
		}, {
			label : "My Second dataset",
			fillColor : "rgba(151,187,205,0.2)",
			strokeColor : "rgba(151,187,205,1)",
			pointColor : "rgba(151,187,205,1)",
			pointStrokeColor : "#fff",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(151,187,205,1)",
			data : chart_data2
		} ]

	}

	dadosDoRadarChart = {
		labels : chartLabels,
		datasets : [ {
			label : "Mulheres",
			fillColor : "rgba(220,220,220,0.2)",
			strokeColor : "rgba(220,220,220,1)",
			pointColor : "rgba(220,220,220,1)",
			pointStrokeColor : "#fff",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(220,220,220,1)",
			data : chart_data1
		}, {
			label : "Homens",
			fillColor : "rgba(151,187,205,0.2)",
			strokeColor : "rgba(151,187,205,1)",
			pointColor : "rgba(151,187,205,1)",
			pointStrokeColor : "#fff",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(151,187,205,1)",
			data : chart_data2
		} ]
	};

	opcoesDoRadarChart = {

		// /Boolean - Caso queira que as linhas da grade não seja exibidas
		scaleShowGridLines : true,

		// String - Cor das linhas de grade
		scaleGridLineColor : "rgba(0,0,0,.50)",

		// Number - Largura das linhas de grade
		scaleGridLineWidth : 0.1,

		// Boolean - Se a linha é curva entre os pontos
		bezierCurve : false,

		// Boolean - Se deve mostrar um ponto para cada ponto
		pointDot : true,

		responsive : true
	};
}

function gerarChart() {

	var ctx = document.getElementById("canvas").getContext("2d");
	window.myLine = new Chart(ctx).Line(lineChartData, opcoesDoRadarChart);

	var ctx = document.getElementById("canvas2").getContext("2d");
	window.myRadar = new Chart(ctx).Radar(dadosDoRadarChart, opcoesDoRadarChart);
}
