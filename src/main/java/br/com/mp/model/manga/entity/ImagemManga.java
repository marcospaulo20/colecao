package br.com.mp.model.manga.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "imagem_manga")
public class ImagemManga implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;
	
	@Column(name="cod_volume", unique = true)
	private Long codigoVolume;
	
	public Long getId() {
		return id;
	}
	
	public byte[] getImagem() {
		return imagem;
	}
	
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	public Long getCodigoVolume() {
		return codigoVolume;
	}
	
	public void setCodigoVolume(Long codigoVolume) {
		this.codigoVolume = codigoVolume;
	}
}
