package com.algaworks.pedidovenda.view.pessoa;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;

@Named
@ViewScoped
public class PessoaListViewBean implements Serializable {

	private static final long serialVersionUID = 7388190114966331415L;
	
	@Getter
	@Inject
	private PessoaLazyDataModel lazyModel;

	@PostConstruct
	private void init() {
		
	}
}
