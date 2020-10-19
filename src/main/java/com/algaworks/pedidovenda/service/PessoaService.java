package com.algaworks.pedidovenda.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.pedidovenda.model.Pessoa;

@Stateless
public class PessoaService {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Pessoa> findAll() {
		return em.createQuery("from Pessoa p", Pessoa.class).getResultList();
	}
	
	public List<Pessoa> findAll(int first, int size) {
		return em.createQuery("from Pessoa p", Pessoa.class)
				.setFirstResult(first)
				.setMaxResults(size)
				.getResultList();
	}
	
	public Long count() {
		return em.createQuery("select count(p) from Pessoa p", Long.class).getSingleResult();
	}
}
