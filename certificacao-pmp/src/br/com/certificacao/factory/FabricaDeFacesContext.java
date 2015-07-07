package br.com.certificacao.factory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FabricaDeFacesContext {
	
	public FabricaDeFacesContext() {
	}
	
	@Produces @RequestScoped
	public FacesContext criarFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
}
