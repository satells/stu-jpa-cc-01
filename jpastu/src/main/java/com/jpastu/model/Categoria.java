package com.jpastu.model;

public enum Categoria {
	CELULARES("A1"), INFORMATICA("A2"), LIVROS("A3");

	private String codigo;

	private Categoria(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
