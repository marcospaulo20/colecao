package br.com.mp.model.filme.entity;

public enum Genero {
	ACAO("Ação"), ANIMACAO("Animação"), AVENTURA("Aventura"), 
	BIOGRAFIA("Biografia"),
	COMEDIA("Cómedia"), CRIME("Crime"), DRAMA("Drama"), 
	FANTASIA("Fantasia"),
	GUERRA("Guerra"),
	HISTORIA("História"),
	HORROR("Horror"), 
	MUSICAL("Músical"), MISTERIO("Mistério"),
	SCI_FI("Sci-Fi"),
	THRILLER("Thriller"),
	ROMANCE("Romance");

	private String label;

	public String getLabel() {
		return label;
	}

	private Genero(String label) {
		this.label = label;
	}
}
