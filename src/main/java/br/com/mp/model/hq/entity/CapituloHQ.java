package br.com.mp.model.hq.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "capitulo_hq")
public class CapituloHQ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Integer numero;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_capa")
	private Date dataCapa;

	private String escritor;

	private String desenhista;

	private Boolean tem = Boolean.FALSE;
	
	private Boolean leu = Boolean.FALSE;

	@ManyToOne
	@JoinColumn(name = "edicao_id")
	private Edicao edicao;

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

	public Date getDataCapa() {
		return dataCapa;
	}

	public void setDataCapa(Date dataCapa) {
		this.dataCapa = dataCapa;
	}

	public Boolean getTem() {
		return tem;
	}

	public void setTem(Boolean tem) {
		this.tem = tem;
	}

	public Boolean getLeu() {
		return leu;
	}
	
	public void setLeu(Boolean leu) {
		this.leu = leu;
	}
	
	public String getDesenhista() {
		return desenhista;
	}

	public void setDesenhista(String desenhista) {
		this.desenhista = desenhista;
	}

	public String getEscritor() {
		return escritor;
	}

	public void setEscritor(String escritor) {
		this.escritor = escritor;
	}

	public Edicao getEdicao() {
		return edicao;
	}

	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
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
		CapituloHQ other = (CapituloHQ) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
