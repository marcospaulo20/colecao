package br.com.mp.model.serie.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.service.spi.ServiceException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.google.common.io.ByteStreams;
import com.sun.faces.util.MessageUtils;

import br.com.mp.model.serie.entity.EpisodioSerie;
import br.com.mp.model.serie.entity.ImagemSerie;
import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.TemporadaSerie;
import br.com.mp.model.serie.service.EpisodioSerieService;
import br.com.mp.model.serie.service.ImagemSerieService;
import br.com.mp.model.serie.service.SerieService;
import br.com.mp.model.serie.service.TemporadaSerieService;

@Named(value="temporadaSerieBean")
@ViewScoped
public class TemporadaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TemporadaSerieService temporadaSerieService;

	@Inject
	private SerieService serieService;

	@Inject
	private EpisodioSerieService episodioSerieService;

	@Inject
	private ImagemSerieService imagemSerieService;

	private TemporadaSerie temporadaSerie;
	private TemporadaSerie temporadaSerieSelecionado;
	private List<TemporadaSerie> temporadaSeries;

	private Serie serie;

	private EpisodioSerie episodioSerie;

	private ImagemSerie imagemSerie;
	
	@Inject
	private ImagesSerie imagesSerieBean;

	public TemporadaSerie getTemporadaSerie() {
		return temporadaSerie;
	}

	public void setTemporadaSerie(TemporadaSerie temporadaSerie) {
		this.temporadaSerie = temporadaSerie;
	}

	public TemporadaSerie getTemporadaSerieSelecionado() {
		return temporadaSerieSelecionado;
	}

	public void setTemporadaSerieSelecionado(TemporadaSerie temporadaSerieSelecionado) {
		this.temporadaSerieSelecionado = temporadaSerieSelecionado;
	}

	public List<TemporadaSerie> getTemporadaSeries() {
		return temporadaSeries;
	}

	public ImagemSerie getImagemSerie() {
		return imagemSerie;
	}

	public Serie getSerie() {
		if (this.serie == null) {
			this.serie = buscarSeriePorUrl();
			return this.serie;
		}
		return serie;
	}

	public EpisodioSerie getEpisodioSerie() {
		return episodioSerie;
	}

	public void setEpisodioSerie(EpisodioSerie episodioSerie) {
		this.episodioSerie = episodioSerie;
	}

	@PostConstruct
	public void init() {
		atualizar();
		this.imagemSerie = new ImagemSerie();
	}

	public void save() {
		try {
			List<EpisodioSerie> episodioSeries = new ArrayList<EpisodioSerie>();

			this.temporadaSerie.setSerie(serie);
			TemporadaSerie temporadaRetorno = this.temporadaSerieService.save(this.temporadaSerie);
			this.temporadaSeries = temporadaSerieService.listBySerie(serie);

			if (this.temporadaSerie.equals(temporadaRetorno)) {
				RequestContext.getCurrentInstance().execute("PF('dlgTemporada').hide();");
				return;
			}

			if (this.temporadaSerie.getEpisodios() == null)
				this.temporadaSerie.setEpisodios(episodioSeries);

			this.temporadaSerie = new TemporadaSerie();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void saveEpisodio() {
		try {
			List<EpisodioSerie> episodioSeries = new ArrayList<EpisodioSerie>();

			this.episodioSerie.setTemporada(this.temporadaSerieSelecionado);
			EpisodioSerie episodioRetorno = this.episodioSerieService.save(this.episodioSerie);

			if (this.episodioSerie.equals(episodioRetorno)) {
				RequestContext.getCurrentInstance().execute("PF('dlgEpisodio').hide();");
				return;
			}
			if (this.temporadaSerieSelecionado.getEpisodios() == null)
				this.temporadaSerieSelecionado.setEpisodios(episodioSeries);

			this.temporadaSerieSelecionado.getEpisodios().add(episodioRetorno);
			this.episodioSerie = new EpisodioSerie();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		try {
			if (this.temporadaSerie.getEpisodios().size() > 0) {
				MessageUtils.getExceptionMessage("Existem temporada!");
				return;
			}
			this.serie.getTemporadas().remove(this.temporadaSerie);
			this.serieService.save(this.serie);

			this.temporadaSerieService.remove(this.temporadaSerie);
			this.temporadaSeries = temporadaSerieService.listBySerie(serie);
			this.temporadaSerie = new TemporadaSerie();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void removeEpisodio() {
		try {
			this.episodioSerieService.remove(this.episodioSerie);

			this.temporadaSerieSelecionado.getEpisodios().remove(this.episodioSerie);

			this.episodioSerie = new EpisodioSerie();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void atualizarEpisodio() {
		try {
			this.episodioSerieService.save(this.episodioSerie);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		this.temporadaSerie = new TemporadaSerie();
		this.temporadaSerieSelecionado = new TemporadaSerie();
	}

	public void novoEpisodio() {
		this.episodioSerie = new EpisodioSerie();
	}

	public Date getUltimaModificaoFoto(Long id) {
		try {
			Date dataHoraFoto = imagemSerieService.findByTemporadaId(id).getUltimaModificacao();
			return dataHoraFoto == null ? obterDataHoraAtual() : dataHoraFoto;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void atualizarCheckTemporada(TemporadaSerie temporadaSerie, boolean checkSelection) {
		if(temporadaSerie != null) {
			temporadaSerie.setTem(checkSelection);
			this.temporadaSerieService.save(temporadaSerie);
			this.temporadaSerieService.listBySerie(serie);
		}	
	}
	
	public void atualizarCheckEpisodioTem(EpisodioSerie episodioSerie, boolean checkSelection) {
		if(episodioSerie != null) {
			episodioSerie.setTem(checkSelection);
			this.episodioSerieService.save(episodioSerie);
			this.temporadaSerieService.listBySerie(serie);
		}	
	}
	
	public void atualizarCheckEpisodio(EpisodioSerie episodioSerie, boolean checkSelection) {
		if(episodioSerie != null) {
			episodioSerie.setAssitiu(checkSelection);
			this.episodioSerieService.save(episodioSerie);
			this.temporadaSerieService.listBySerie(serie);
		}	
	}

	public void uploadImagem(FileUploadEvent event) throws IOException {
		InputStream is = event.getFile().getInputstream();
		byte[] imagemBytes = ByteStreams.toByteArray(is);
		
		Long codigoTemporada = this.temporadaSerie.getId();
		
		this.imagemSerie = new ImagemSerie();
		
		if(imagesSerieBean.get(codigoTemporada)==null){
			this.imagemSerie.setCodigoTemporada(codigoTemporada);
			this.imagemSerie.setUltimaModificacao(obterDataHoraAtual());
			
			this.imagemSerie.setImagem(imagemBytes);
			this.imagemSerieService.save(imagemSerie);
		} else {
			this.imagemSerie = imagemSerieService.findByTemporadaId(codigoTemporada);
			
			this.imagemSerie.setUltimaModificacao(obterDataHoraAtual());
			this.imagemSerie.setImagem(imagemBytes);
			this.imagemSerieService.save(imagemSerie);
		}
		
		this.imagesSerieBean.get(codigoTemporada);
		
		RequestContext.getCurrentInstance().update("form:tabela-temporada");
		RequestContext.getCurrentInstance().execute("PF('dlgImagem').hide();");
	}


	private void atualizar() {
		this.temporadaSeries = temporadaSerieService.listBySerie(buscarSeriePorUrl());
		this.temporadaSerie = new TemporadaSerie();
		this.temporadaSerieSelecionado = new TemporadaSerie();
	}

	private Serie buscarSeriePorUrl() {
		String codigoSerie = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("serieID");
		if (codigoSerie != null)
			return serieService.find(Long.parseLong(codigoSerie));
		return null;
	}

	private Date obterDataHoraAtual() {
		return new Date();
	}

}