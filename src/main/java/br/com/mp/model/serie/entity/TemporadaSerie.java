package br.com.mp.model.serie.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "temporada_serie")
public class TemporadaSerie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(name = "ano_lancamento")
	private Integer anoLancamento;

	private Boolean tem = Boolean.FALSE;

	@OneToMany(mappedBy = "temporada", targetEntity = EpisodioSerie.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<EpisodioSerie> episodios;

	@ManyToOne
	@JoinColumn(name = "serie_id")
	private Serie serie;

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

	public Boolean getTem() {
		return tem;
	}

	public void setTem(Boolean tem) {
		this.tem = tem;
	}

	public List<EpisodioSerie> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<EpisodioSerie> episodios) {
		this.episodios = episodios;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public String quantidadeEpisodioTem() {
		return this.episodios.stream().filter(e -> e.getAssitiu() == true).count() + " de " + this.episodios.size();
	}

	public String quantidadeEpisodioQueAssistido() {
		return this.episodios.stream().filter(e -> e.getAssitiu() == true).count() + " de " + this.episodios.size();
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TemporadaSerie other = (TemporadaSerie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}