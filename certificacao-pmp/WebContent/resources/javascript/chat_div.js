$(document).ready(function() {

	var window_bottom = $('.window_bottom');

	$('#minimizeWindow').click(function() {
		var vButtom = $(this).val();

		if (vButtom == '0') {
			window_bottom.animate({
				bottom : -280 + 'px'
			});
			$('#minimizeWindow').attr('value', 1);
			$('#minimizeWindow').text('Atendimento Online');
		} else {
			window_bottom.animate({
				bottom : 0 + 'px'
			});
			$('#minimizeWindow').attr('value', 0);
			$('#minimizeWindow').text('Bem vindo!');
		}
		return false;
	});

	setTimeout(function() {
		$('#minimizeWindow').click();
	}, 1000)
});