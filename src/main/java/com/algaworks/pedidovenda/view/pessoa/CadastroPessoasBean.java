package com.algaworks.pedidovenda.view.pessoa;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Pessoa;
import com.algaworks.pedidovenda.service.PessoaRepository;
import com.algaworks.pedidovenda.util.MessagesUtil;

import lombok.Getter;

@Named
@ViewScoped
public class CadastroPessoasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private Pessoa pessoa;

	@Inject
	private PessoaRepository pessoaRepository;

	@PostConstruct
	private void init() {
		pessoa = new Pessoa();
	}

	public String salvar() {
		pessoaRepository.save(pessoa);
		limpar();
		MessagesUtil.addMessage("Cadastro de Pessoa", "Pessoa cadastrada com sucesso!");
		return "/pessoa/PesquisaPessoas.xhtml?faces-redirect=true";
	}

	private void limpar() {
		pessoa = new Pessoa();
	}

	public boolean isEdicao() {
		return pessoa != null && pessoa.getId() != null;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		if (pessoa == null) {
			this.pessoa = new Pessoa();
		}
	}

}
