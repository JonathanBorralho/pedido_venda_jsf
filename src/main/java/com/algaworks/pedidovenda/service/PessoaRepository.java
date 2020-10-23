package com.algaworks.pedidovenda.service;

import static com.algaworks.pedidovenda.model.QPessoa.pessoa;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;

import javax.ejb.Stateless;

import com.algaworks.pedidovenda.model.Pessoa;
import com.algaworks.pedidovenda.querydsl.AbstractRepository;
import com.algaworks.pedidovenda.view.pessoa.PessoaFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Stateless
public class PessoaRepository extends AbstractRepository<Pessoa, Long> {

	public PessoaRepository() {
		super(Pessoa.class);
	}
	
	public void deletar(Long id) {
		final Pessoa pessoa = findById(id);
		delete(pessoa);
	}
	
	public List<Pessoa> filtrar(PessoaFilter filter) {
		return findAll(filter.getFirst(), filter.getSize(), buildPessoaFilter(filter));
	}
	
	public Long count(PessoaFilter filter) {
		return count(buildPessoaFilter(filter));
	}
	
	private Predicate buildPessoaFilter(PessoaFilter filter) {
		final BooleanBuilder builder = new BooleanBuilder();
		
		if (isNotBlank(filter.getNome())) {
			builder.and(pessoa.nome.containsIgnoreCase(filter.getNome()));
		}
		
		if (isNotBlank(filter.getEmail())) {
			builder.and(pessoa.email.containsIgnoreCase(filter.getEmail()));
		}

		return builder;
	}
	
}
