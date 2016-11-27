package br.com.mp.model.hq.entity;

public enum TipoEditora {

	DC("DC Comics"),
	MARVEL("Marvel"),
	VERTIGO("Vertigo"),
	IMAGE("Image"),
	ICON("Icon Comics"),
	FICTION_HOUSE("Fiction House"),
	AMERICA("America's Best Comics");
	
	private final String label;
	
	private TipoEditora(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
}
