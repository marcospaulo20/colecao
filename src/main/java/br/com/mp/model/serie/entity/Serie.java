package br.com.mp.model.serie.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Serie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@Column(name="ano_lancamento")
	private Integer anoLancamento;
	
	@Enumerated(EnumType.STRING)
	private TipoClassificacao tipoClassificacao;
	
	@OneToMany(mappedBy = "serie", targetEntity = TemporadaSerie.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<TemporadaSerie> temporadas;
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getAnoLancamento() {
		return anoLancamento;
	}
	
	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	
	public TipoClassificacao getTipoClassificacao() {
		return tipoClassificacao;
	}
	
	public void setTipoClassificacao(TipoClassificacao tipoClassificacao) {
		this.tipoClassificacao = tipoClassificacao;
	}
	
	public List<TemporadaSerie> getTemporadas() {
		return temporadas;
	}
	
	public void setTemporadas(List<TemporadaSerie> temporadas) {
		this.temporadas = temporadas;
	}

	public String quantidadeTemporada() {
		return this.temporadas.stream().filter(v->v.getTem() == true).count() + " de " + this.temporadas.size();
	}
}
