package br.com.certificacao.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagemUtil {

	public static void addMensagemDeErro(String mensagemDeErro, String detalhes){
		addMensagem(FacesMessage.SEVERITY_ERROR, mensagemDeErro, detalhes);
	}
	
	public static void addMensagemDeAviso(String mensagemDeAviso, String detalhes){
		addMensagem(FacesMessage.SEVERITY_WARN, mensagemDeAviso, detalhes);
	}
	
	public static void addMensagemInformativa(String mensagemInformativa, String detalhes){
		addMensagem(FacesMessage.SEVERITY_INFO,	mensagemInformativa, detalhes);
	}
	
	private static void addMensagem(FacesMessage.Severity severidade, String mensagem, String detalhes){
		FacesMessage msg = new FacesMessage(severidade,	mensagem, detalhes);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
