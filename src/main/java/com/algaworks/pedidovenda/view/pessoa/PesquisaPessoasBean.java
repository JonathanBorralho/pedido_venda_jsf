package com.algaworks.pedidovenda.view.pessoa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.pedidovenda.model.Pessoa;
import com.algaworks.pedidovenda.service.PessoaRepository;
import com.algaworks.pedidovenda.util.MessagesUtil;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PesquisaPessoasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private LazyDataModel<Pessoa> model;

	@Inject
	private PessoaRepository pessoaRepository;

	@Getter
	@Setter
	private PessoaFilter filter;

	@PostConstruct
	private void init() {
		filter = PessoaFilter.empty();
		model = getLazyDataModel();
	}

	public void clearSearch() {
		filter = PessoaFilter.empty();
	}

	public void deletar(Long id) {
		pessoaRepository.deletar(id);
		MessagesUtil.addMessage("Exclusão de Pessoa", "Pessoa excluída com sucesso!");
	}

	private LazyDataModel<Pessoa> getLazyDataModel() {
		return new LazyDataModel<Pessoa>() {
			private static final long serialVersionUID = -470156840517727613L;

			@Override
			public List<Pessoa> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, FilterMeta> filterBy) {

				filter.setFirst(first);
				filter.setSize(pageSize);
				setRowCount(pessoaRepository.count(filter).intValue());

				return pessoaRepository.filtrar(filter);
			}
		};
	}

}
