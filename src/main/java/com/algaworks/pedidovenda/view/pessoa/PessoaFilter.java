package com.algaworks.pedidovenda.view.pessoa;

import lombok.Data;

@Data
public class PessoaFilter {
	private String nome;
	private String email;
	
	private int first;
	private int size;
	
	public static PessoaFilter empty() {
		return new PessoaFilter();
	}
}
