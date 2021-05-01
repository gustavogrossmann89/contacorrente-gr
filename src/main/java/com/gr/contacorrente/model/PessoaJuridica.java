package com.gr.contacorrente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

@Entity
@Table(name = "PessoaJuridica")
public class PessoaJuridica extends Pessoa {

	private String cnpj;
	
	@NotNull
	@Column(name = "cnpj", length = 14)
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Transient
	public String getTipo() {
		return "PessoaJuridica";
	}
}
