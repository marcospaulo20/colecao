package br.com.mp.model.hq.entity;

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
@Table(name = "imagem_hq")
public class ImagemHQ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;
	
	@Column(name="cod_capitulo", unique = true)
	private Long codigoCapitulo;
	
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
	
	public Long getCodigoCapitulo() {
		return codigoCapitulo;
	}
	
	public void setCodigoCapitulo(Long codigoCapitulo) {
		this.codigoCapitulo = codigoCapitulo;
	}
	
	public Date getUltimaModificacao() {
		return ultimaModificacao;
	}
	
	public void setUltimaModificacao(Date ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
	}
}