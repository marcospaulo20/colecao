package br.com.mp.model.serie.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "imagem_serie")
public class ImagemSerie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	@Column(name = "cod_temporada", unique = true)
	private Long codigoTemporada;

	@Column(name = "ultima_modificacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaModificacao;

	public Long getId() {
		return id;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Long getCodigoTemporada() {
		return codigoTemporada;
	}

	public void setCodigoTemporada(Long codigoTemporada) {
		this.codigoTemporada = codigoTemporada;
	}

	public Date getUltimaModificacao() {
		return ultimaModificacao;
	}

	public void setUltimaModificacao(Date ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
	}

}
