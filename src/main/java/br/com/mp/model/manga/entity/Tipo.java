package br.com.mp.model.manga.entity;

public enum Tipo {
	FISICO("Fisico"),
	VIRTUAL("Virtual");
	
	private final String label;
	
	private Tipo(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
}
