package br.com.mp.model.filme.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Filme implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(name = "nome_original")
	private String nomeOriginal;

	@Enumerated(EnumType.STRING)
	private TipoClassificacao tipoClassificacao;

	private Integer anoLancamneto;

	@Temporal(TemporalType.TIME)
	private Date duracao;

	private String sinopse;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "filme_genero", joinColumns = @JoinColumn(name = "id"))
	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private Collection<Genero> generos;
	
	private Boolean tem = Boolean.FALSE;

	private Boolean assistiu = Boolean.FALSE;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}

	public TipoClassificacao getTipoClassificacao() {
		return tipoClassificacao;
	}

	public void setTipoClassificacao(TipoClassificacao tipoClassificacao) {
		this.tipoClassificacao = tipoClassificacao;
	}

	public Integer getAnoLancamneto() {
		return anoLancamneto;
	}

	public void setAnoLancamneto(Integer anoLancamneto) {
		this.anoLancamneto = anoLancamneto;
	}

	public Date getDuracao() {
		return duracao;
	}

	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}
	
	public Collection<Genero> getGeneros() {
		return generos;
	}
	
	public void setGeneros(Collection<Genero> generos) {
		this.generos = generos;
	}

	public Boolean getTem() {
		return tem;
	}

	public void setTem(Boolean tem) {
		this.tem = tem;
	}

	public Boolean getAssistiu() {
		return assistiu;
	}

	public void setAssistiu(Boolean assistiu) {
		this.assistiu = assistiu;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
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
		Filme other = (Filme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
