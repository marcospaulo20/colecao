package br.com.mp.model.filme.entity;

public enum TipoClassificacao {

	LIVRE("Livre"),
	DEZ_ANO("10 Anos"),
	DOZE_ANO("12 Anos"),
	CATORZE_ANO("14 Anos"),
	DEZESSEIS_ANO("16 Anos"),
	DEZOITO_ANO("18 Anos");
	
	private final String label;
	
	TipoClassificacao(String label) {
		this.label = label;				
	}
	
	public String getLabel() {
		return label;
	}
}
