package br.com.mp.model.hq.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Edicao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_publicacao")
	private Date dataPublicacao;

	private Boolean tem = Boolean.FALSE;
	
	@OneToMany(mappedBy = "edicao", targetEntity = CapituloHQ.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CapituloHQ> capitulos;

	@ManyToOne
	@JoinColumn(name = "hq_id")
	private HQ hq;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Boolean getTem() {
		return tem;
	}

	public void setTem(Boolean tem) {
		this.tem = tem;
	}

	public List<CapituloHQ> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<CapituloHQ> capitulos) {
		this.capitulos = capitulos;
	}

	public HQ getHq() {
		return hq;
	}

	public void setHq(HQ hq) {
		this.hq = hq;
	}
	
	public String quantidadeCapituloTem() {
		return this.capitulos.stream().filter(c -> c.getTem() == true).count() + " de " + this.capitulos.size();
	}
	
	public String quantidadeCapituloQueLeu() {
		return this.capitulos.stream().filter(c -> c.getLeu() == true).count() + " de " + this.capitulos.size();
	}
	
	public Long quantidadeCapituloQueNaoLeu() {
		return this.capitulos.stream().filter(c -> c.getLeu() == false).count();
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
		Edicao other = (Edicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
