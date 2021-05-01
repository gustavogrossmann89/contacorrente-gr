package com.gr.contacorrente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

@Entity
@Table(name = "PessoaFisica")
public class PessoaFisica extends Pessoa {

	private String cpf;
	
	@NotNull
	@Column(name = "cpf", length = 11)
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Transient
	public String getTipo() {
		return "PessoaFisica";
	}
}
