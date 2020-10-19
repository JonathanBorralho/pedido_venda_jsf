package com.algaworks.pedidovenda.view;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Pessoa;
import com.algaworks.pedidovenda.service.PessoaService;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 5843828546265799460L;
	
	@Inject
	private PessoaService pessoaService;
	
	@Getter
	private String nome;
	
	@Getter
	private List<Pessoa> pessoas;
	
	@PostConstruct
	private void init() {
		nome = "Jonathan";
		pessoas = pessoaService.findAll();
	}
	
	public String novaPessoa() {
		return "pessoaListView.xhtml?faces-redirect=true";
	}

}
