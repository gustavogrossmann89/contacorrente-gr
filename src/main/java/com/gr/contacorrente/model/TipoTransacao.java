package com.gr.contacorrente.model;

import java.util.Arrays;
import java.util.List;

public enum TipoTransacao {
	SAQUE("Saque de valor em conta","saque"),
	DEPOSITO("Depósito de valor em conta ","deposito");
	
	private String descricao;
	private String codigo;
	
	private TipoTransacao(String descricao, String codigo) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoTransacao getByName(String name) {
		return TipoTransacao.valueOf(TipoTransacao.class, name);
	}

	public static List<TipoTransacao> getList() {
		return Arrays.asList(TipoTransacao.values());
	}
}


