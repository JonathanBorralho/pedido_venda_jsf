package com.algaworks.pedidovenda.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessagesUtil {
	
	private static final Severity sucess = FacesMessage.SEVERITY_INFO;
	private static final Severity error = FacesMessage.SEVERITY_ERROR;
	private static final Severity warn = FacesMessage.SEVERITY_WARN;

	public MessagesUtil() {
		// TODO Auto-generated constructor stub
	}

	private static void message(Severity facesMessage, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(facesMessage, message, detail));
	}
	
	public static void sucess(String message, String detail) {
		message(sucess, message, detail);
	}
	
	public static void error(String message, String detail) {
		message(error, message, detail);
	}
	
	public static void warn(String message, String detail) {
		message(warn, message, detail);
	}
	
	public static void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
