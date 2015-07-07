package br.com.certificacao.listeners;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.certificacao.util.Navegador;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;
	@Inject
	FacesContext context;
	@Inject
	private Navegador navegador;

	@Override
	public void afterPhase(PhaseEvent event) {

		navegador.redirecionar("principal");
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// não é necessário fazer nada antes da fase Restore View
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}