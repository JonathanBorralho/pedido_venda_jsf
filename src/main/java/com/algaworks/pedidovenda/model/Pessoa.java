package com.algaworks.pedidovenda.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Pessoa extends AbstractEntity {
	
	private static final long serialVersionUID = -4511458314273147489L;
	
	private String nome;
	
	private String cpf;
	
	private String email;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
}
