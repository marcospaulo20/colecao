package br.com.mp.model.hq.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class HQ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private TipoEditora editora;
	
	@OneToMany(mappedBy = "hq", targetEntity = Edicao.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Edicao> edicoes;
	
	public HQ() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public TipoEditora getEditora() {
		return editora;
	}
	
	public void setEditora(TipoEditora editora) {
		this.editora = editora;
	}
		
	public List<Edicao> getEdicoes() {
		return edicoes;
	}
	public void setEdicoes(List<Edicao> edicoes) {
		this.edicoes = edicoes;
	}
	
	
	public String quantidadeEdicao() {
		return this.edicoes.stream().filter(v->v.getTem() == true).count() + " de " + this.edicoes.size();
	}
}
