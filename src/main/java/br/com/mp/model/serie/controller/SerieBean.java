package br.com.mp.model.serie.controller;

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

import br.com.mp.model.serie.entity.ImagemSerie;
import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.TipoClassificacao;
import br.com.mp.model.serie.service.ImagemSerieService;
import br.com.mp.model.serie.service.SerieService;

@Named
@ViewScoped
public class SerieBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SerieService serieService;

	@Inject
	private ImagemSerieService imagemSerieService;

	private Serie serie;
	private List<Serie> series;

	private TipoClassificacao tipoClassificacao;
	@SuppressWarnings("unused")
	private TipoClassificacao[] tipoClassificacaos;

	private Set<String> generosSelecionados;
	private List<String> generos;
	
	private ImagemSerie imagemSerie;

	@Inject
	private ImagesSerie imagesSerieBean;

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public List<Serie> getSeries() {
		return series;
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
	
	public Set<String> getGenerosSelecionados() {
		return generosSelecionados;
	}
	
	public void setGenerosSelecionados(Set<String> generosSelecionados) {
		this.generosSelecionados = generosSelecionados;
	}
	
	public List<String> getGeneros() {
		return generos;
	}	


	public ImagemSerie getImagemSerie() {
		return imagemSerie;
	}

	@PostConstruct
	public void init() {
		atualizar();
		generos();
		this.generosSelecionados = new HashSet<String>();
		this.imagemSerie = new ImagemSerie();
	}

	public void save() {
		try {
			this.serie.setGeneros(this.generosSelecionados);
			this.serieService.save(this.serie);
			if(this.serie.getId() != null) {
				RequestContext.getCurrentInstance().execute("PF('dlgSerie').hide();");
				return;
			}
			atualizar();
			generos();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		try {
			this.serieService.remove(this.serie);
			atualizar();			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		this.serie = new Serie();
	}

	public Date getUltimaModificaoFoto(Long id) {
		try {
			Date dataHoraFoto = imagemSerieService.findBySerieId(id).getUltimaModificacao();
			return dataHoraFoto == null ? obterDataHoraAtual() : dataHoraFoto;
		} catch (Exception e) {
			return null;
		}
	}

	public void uploadImagem(FileUploadEvent event) throws IOException {
		InputStream is = event.getFile().getInputstream();
		byte[] imagemBytes = ByteStreams.toByteArray(is);

		Long codigoSerie = this.serie.getId();

		this.imagemSerie = new ImagemSerie();

		if (imagesSerieBean.get(codigoSerie) == null) {
			this.imagemSerie.setCodigoSerie(codigoSerie);
			this.imagemSerie.setUltimaModificacao(obterDataHoraAtual());

			this.imagemSerie.setImagem(imagemBytes);
			this.imagemSerieService.save(imagemSerie);
		} else {
			this.imagemSerie = imagemSerieService.findBySerieId(codigoSerie);

			this.imagemSerie.setUltimaModificacao(obterDataHoraAtual());
			this.imagemSerie.setImagem(imagemBytes);
			this.imagemSerieService.save(imagemSerie);
		}

		this.imagesSerieBean.get(codigoSerie);

		RequestContext.getCurrentInstance().update("form:tabela-serie");
		RequestContext.getCurrentInstance().execute("PF('dlgImagem').hide();");
	}

	public void selecioneGeneros() {
		this.generosSelecionados = new HashSet<String>(); 
		if(!this.serie.getGeneros().isEmpty())
			for(String g : this.serie.getGeneros())
				this.generosSelecionados.add(g);
	}
	
	private void atualizar() {
		this.series = this.serieService.list();
		this.serie = new Serie();
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
		this.generos.add("Documentário");
		this.generos.add("Drama");
		this.generos.add("Família");
		this.generos.add("Fantasia");
		this.generos.add("Faroeste");
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