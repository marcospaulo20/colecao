package br.com.mp.model.filme.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
