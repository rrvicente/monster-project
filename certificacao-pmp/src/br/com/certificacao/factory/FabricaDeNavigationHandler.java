package br.com.certificacao.factory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class FabricaDeNavigationHandler {
	
	@Inject
	FacesContext facesContext;
	
	@Produces @RequestScoped
	public NavigationHandler criarNavigationHandler() {
		
	    if (facesContext != null) {
	        Application application = facesContext.getApplication();
	        if (application != null) {
	            return application.getNavigationHandler();
	        }
	    }
	    return null;
	}
}
