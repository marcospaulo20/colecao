package br.com.mp.model.filme.entity;

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
@Table(name = "imagem_filme")
public class ImagemFilme implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	@Column(name = "cod_filme", unique = true)
	private Long codigoFilme;

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

	public Long getCodigoFilme() {
		return codigoFilme;
	}

	public void setCodigoFilme(Long codigoFilme) {
		this.codigoFilme = codigoFilme;
	}

	public Date getUltimaModificacao() {
		return ultimaModificacao;
	}

	public void setUltimaModificacao(Date ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
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
		ImagemFilme other = (ImagemFilme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
