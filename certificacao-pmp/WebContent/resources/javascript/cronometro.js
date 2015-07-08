//var Cronometro = $({
//	initialize : function(id, opciones) {
//
//		this.el = $(id);
//		this.id = this.el.identify();
//
//		this.opciones = Object.extend({
//			hiddenField : null
//		}, opciones || {});
//
//		this.t0 = new Date().getTime();
//
//		if (Cronometro.cronos[this.id])
//			Cronometro.cronos[this.id].parar();
//		Cronometro.cronos[this.id] = this;
//
//		this.pintaCrono();
//		this.temporizador = new PeriodicalExecuter(this.pintaCrono.bind(this),
//				1);
//	},
//
//	parar : function() {
//		this.temporizador.stop();
//	},
//
//	pintaCrono : function() {
//		if (this.opciones.hiddenField)
//			Form.Element.setValue(this.opciones.hiddenField, new Date()
//					.getTime()
//					- this.t0);
//		this.el.update(this.getCronoString());
//	},
//
//	getCronoString : function() {
//		var t = (new Date().getTime() - this.t0) / 1000;
//		var m = Math.floor(t / 60);
//		var s = Math.floor(t % 60);
//		var mm = m < 10 ? "0" + m.toString() : m.toString();
//		var ss = s < 10 ? "0" + s.toString() : s.toString();
//		return mm + ":" + ss;
//	}
//});
//
//var tempo = new Number();
//
//// Tempo em segundos
//
//tempo = 3000;
//
//function startCountdown() {
//
//	// Se o tempo não for zerado
//	if ((tempo - 1) >= 0) {
//
//		// Pega a parte inteira dos minutos
//		var min = parseInt(tempo / 60);
//
//		// Calcula os segundos restantes
//		var seg = tempo % 60;
//
//		// Formata o número menor que dez, ex: 08, 07, ...
//		if (min < 10) {
//			min = "0" + min;
//			min = min.substr(0, 2);
//		}
//
//		if (seg <= 9) {
//			seg = "0" + seg;
//		}
//
//		// Cria a variável para formatar no estilo hora/cronômetro
//		horaImprimivel = '00:' + min + ':' + seg;
//
//		// JQuery pra setar o valor
//		// $("#tempo").html(horaImprimivel);
//		var tempo = document.getElementById("tempo");
//		tempo.value = 'asdasdasdasd';
//
//		// Define que a função será executada novamente em 1000ms = 1 segundo
//		setTimeout('startCountdown()', 1000);
//
//		// diminui o tempo
//		tempo--;
//
//		// Quando o contador chegar a zero faz esta ação
//	} else {
//		// window.open('../controllers/logout.php', '_self');
//	}
//}

    
// 
var minutos = 20;
var seconds = 00;

//$(document).ready(function() {


	function startCountdown() {
		var campo = document.getElementById("cronometro");
		var campo_div = document.getElementById("cronometro_div");
		if (seconds <= 0) {
			seconds = 60;
			minutos -= 1;
		}
		if (minutos <= -1) {
			seconds = 0;
			seconds += 1;
			campo.innerHTML = "";
			campo_div.innerHTML = "Sessão expirada!";
		} else {
			seconds -= 1
			if (seconds < 10) {
				seconds = "0" + seconds;
			}
			campo.innerHTML = " " + minutos + "min" + seconds;
			setTimeout("startCountdown()", 1000);
		}
}

//});