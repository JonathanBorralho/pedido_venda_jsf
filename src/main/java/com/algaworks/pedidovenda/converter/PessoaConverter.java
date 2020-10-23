package com.algaworks.pedidovenda.converter;

import static java.lang.Long.valueOf;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Pessoa;
import com.algaworks.pedidovenda.service.PessoaRepository;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter<Pessoa> {

	private PessoaRepository pessoaRepository;
	
	public PessoaConverter() {
		pessoaRepository = CDI.current().select(PessoaRepository.class).get();
	}
	
	@Override
	public Pessoa getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa pessoa = null;
		
		if (isNotBlank(value)) {
			pessoa = pessoaRepository.findById(valueOf(value));
		}

		return pessoa;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Pessoa pessoa) {
		if (pessoa != null) {
			return pessoa.getId() != null ? pessoa.getId().toString() : null;
		}
		return "";
	}

}
