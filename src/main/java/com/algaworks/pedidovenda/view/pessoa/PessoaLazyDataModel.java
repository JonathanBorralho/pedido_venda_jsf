package com.algaworks.pedidovenda.view.pessoa;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.algaworks.pedidovenda.model.Pessoa;
import com.algaworks.pedidovenda.service.PessoaService;

@Stateless
public class PessoaLazyDataModel extends LazyDataModel<Pessoa> {

	private static final long serialVersionUID = -704641395412010935L;
	
	@Inject
	private PessoaService pessoaService;
	
	@Override
	public List<Pessoa> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		setRowCount(pessoaService.count().intValue());
		return pessoaService.findAll(first, pageSize);
	}
}
