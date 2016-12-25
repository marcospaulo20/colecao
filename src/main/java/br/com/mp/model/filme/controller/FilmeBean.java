package br.com.mp.model.filme.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.service.spi.ServiceException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.google.common.io.ByteStreams;

import br.com.mp.model.filme.entity.Filme;
import br.com.mp.model.filme.entity.ImagemFilme;
import br.com.mp.model.filme.entity.TipoClassificacao;
import br.com.mp.model.filme.service.FilmeService;
import br.com.mp.model.filme.service.ImagemFilmeService;

@Named
@ViewScoped
public class FilmeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FilmeService filmeService;
	
	@Inject
	private ImagemFilmeService imagemFilmeService;

	private Filme filme;
	private List<Filme> filmes;

	private TipoClassificacao tipoClassificacao;
	@SuppressWarnings("unused")
	private TipoClassificacao[] tipoClassificacaos;
	
	private Set<String> generosSelecionados;
	private List<String> generos;
	
	@Inject
	private ImagesFilme imageFilmeBean;
	
	private ImagemFilme imagem;

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public TipoClassificacao getTipoClassificacao() {
		return tipoClassificacao;
	}

	public void setTipoClassificacao(TipoClassificacao tipoClassificacao) {
		this.tipoClassificacao = tipoClassificacao;
	}

	public TipoClassificacao[] getTipoClassificacaos() {
		return TipoClassificacao.values();
	}

	public void setTipoClassificacaos(TipoClassificacao[] tipoClassificacaos) {
		this.tipoClassificacaos = tipoClassificacaos;
	}
	
	public Set<String> getGenerosSelecionados() {
		return generosSelecionados;
	}
	
	public void setGenerosSelecionados(Set<String> generosSelecionados) {
		this.generosSelecionados = generosSelecionados;
	}
	
	public List<String> getGeneros() {
		return generos;
	}	
	
	public ImagemFilme getImagem() {
		return imagem;
	}

	@PostConstruct
	public void init() {
		atualizar();
		generos();
		this.generosSelecionados = new HashSet<String>();
		this.imagem = new ImagemFilme();
	}

	public void save() {
		try {
			this.filme.setGeneros(this.generosSelecionados);
			this.filmeService.save(this.filme);
			if(this.filme.getId() != null) {
				RequestContext.getCurrentInstance().execute("PF('dlgFilme').hide();");
				return;
			}
			this.atualizar();
			this.generos();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		try {
			this.filmeService.remove(this.filme);
			atualizar();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		this.filme = new Filme();		
	}

	public void uploadImagem(FileUploadEvent event) throws IOException {
		InputStream is = event.getFile().getInputstream();
		byte[] imagemBytes = ByteStreams.toByteArray(is);

		Long codigoFilme = this.filme.getId();
		
		this.imagem = new ImagemFilme();

		if (imageFilmeBean.get(codigoFilme) == null) {
			this.imagem.setCodigoFilme(codigoFilme);
			this.imagem.setUltimaModificacao(obterDataHoraAtual());

			this.imagem.setImagem(imagemBytes);
			this.imagemFilmeService.save(imagem);
		} else {
			this.imagem = imagemFilmeService.findByFilmeId(codigoFilme);

			this.imagem.setUltimaModificacao(obterDataHoraAtual());
			this.imagem.setImagem(imagemBytes);
			this.imagemFilmeService.save(imagem);

			this.imageFilmeBean.get(codigoFilme);
			this.imagem = new ImagemFilme();
		}
		RequestContext.getCurrentInstance().update("form:tabela-filme");
		RequestContext.getCurrentInstance().execute("PF('dlgImagem').hide();");
	}
	
	public void atualizarCheck(Filme filme, boolean checkSelection) {
		if(filme != null) {
			filme.setTem(checkSelection);
			this.filmeService.save(filme);
		}	
	}
	
	public void atualizarAssistiuCheck(Filme filme, boolean checkSelection) {
		if(filme != null) {
			filme.setAssistiu(checkSelection);
			this.filmeService.save(filme);
		}	
	}
	
	public Date getUltimaModificaoFoto(Long id) { 
		try {
			Date dataHoraFoto = imagemFilmeService.findByFilmeId(id).getUltimaModificacao();
			return dataHoraFoto == null ? obterDataHoraAtual() : dataHoraFoto;
		} catch(Exception e) {
			return null;
		}
	}
	
	public int quantidadeTotalFilme() {
		return this.filmes.size();
	}
	
	public Long quantidadeTotalFilmeTem() {
		return this.filmes.stream().filter(f->f.getTem()==true).count();
	}
	
	public Long quantidadeTotalFilmeNaoTem() {
		return this.filmes.stream().filter(f->f.getTem()==false).count();
	}

	public Long quantidadeTotalFilmeAssistido() {
		return this.filmes.stream().filter(f->f.getAssistiu()==true).count();
	}
	
	public Long quantidadeTotalFilmeNaoAssistido() {
		return this.filmes.stream().filter(f->f.getAssistiu()==false).count();
	}
	
	public void selecioneGeneros() {
		this.generosSelecionados = new HashSet<String>(); 
		if(!this.filme.getGeneros().isEmpty())
			for(String g : this.filme.getGeneros())
				this.generosSelecionados.add(g);
	}
	
	private void atualizar() {
		this.filmes = this.filmeService.list();
		this.filme = new Filme();
		this.generosSelecionados = new HashSet<String>();
	}
	
	private Date obterDataHoraAtual() {
		return new Date();
	}

	private void generos() {
		this.generos = new ArrayList<String>();		
		this.generos.add("Ação");
		this.generos.add("Animação");
		this.generos.add("Aventura");
		this.generos.add("Biografia");
		this.generos.add("Cómedia");
		this.generos.add("Crime");
		this.generos.add("Drama");
		this.generos.add("Família");
		this.generos.add("Fantasia");		
		this.generos.add("Guerra");
		this.generos.add("História");
		this.generos.add("Horror");
		this.generos.add("Músical");
		this.generos.add("Mistério");
		this.generos.add("Sci-Fi");
		this.generos.add("Thriller");
		this.generos.add("Romance");
	}
}
