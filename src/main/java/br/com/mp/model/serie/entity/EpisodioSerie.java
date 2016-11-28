package br.com.mp.model.serie.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "episodio_serie")
public class EpisodioSerie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Integer numero;

	private Boolean tem = Boolean.FALSE;

	private Boolean assitiu = Boolean.FALSE;

	@ManyToOne
	@JoinColumn(name = "temporada_id")
	private TemporadaSerie temporada;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getTem() {
		return tem;
	}

	public void setTem(Boolean tem) {
		this.tem = tem;
	}

	public Boolean getAssitiu() {
		return assitiu;
	}

	public void setAssitiu(Boolean assitiu) {
		this.assitiu = assitiu;
	}

	public TemporadaSerie getTemporada() {
		return temporada;
	}

	public void setTemporada(TemporadaSerie temporada) {
		this.temporada = temporada;
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
		EpisodioSerie other = (EpisodioSerie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
