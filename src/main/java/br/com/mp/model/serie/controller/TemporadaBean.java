package br.com.mp.model.serie.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.service.spi.ServiceException;
import org.primefaces.context.RequestContext;

import com.sun.faces.util.MessageUtils;

import br.com.mp.model.serie.entity.Episodio;
import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.Temporada;
import br.com.mp.model.serie.service.EpisodioService;
import br.com.mp.model.serie.service.SerieService;
import br.com.mp.model.serie.service.TemporadaService;

@Named
@ViewScoped
public class TemporadaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TemporadaService temporadaService;

	@Inject
	private SerieService serieService;

	@Inject
	private EpisodioService episodioService;

	private Temporada temporada;
	private Temporada temporadaSelecionado;
	private List<Temporada> temporadas;

	private Serie serie;

	private Episodio episodio;

	public Temporada getTemporada() {
		return temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

	public Temporada getTemporadaSelecionado() {
		return temporadaSelecionado;
	}

	public void setTemporadaSelecionado(Temporada temporadaSelecionado) {
		this.temporadaSelecionado = temporadaSelecionado;
	}

	public List<Temporada> getTemporadas() {
		return temporadas;
	}

	public Serie getSerie() {
		if (this.serie == null) {
			this.serie = buscarSeriePorUrl();
			return this.serie;
		}
		return serie;
	}

	public Episodio getEpisodio() {
		return episodio;
	}

	public void setEpisodio(Episodio episodio) {
		this.episodio = episodio;
	}

	@PostConstruct
	public void init() {
		atualizar();
	}

	public void save() {
		try {
			List<Episodio> episodios = new ArrayList<Episodio>();

			this.temporada.setSerie(serie);
			Temporada temporadaRetorno = this.temporadaService.save(this.temporada);
			this.temporadas = temporadaService.listBySerie(serie);

			if (this.temporada.equals(temporadaRetorno)) {
				RequestContext.getCurrentInstance().execute("PF('dlgTemporada').hide();");
				return;
			}

			if (this.temporada.getEpisodios() == null)
				this.temporada.setEpisodios(episodios);

			this.temporada = new Temporada();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void saveEpisodio() {
		try {
			List<Episodio> episodios = new ArrayList<Episodio>();

			this.episodio.setTemporada(this.temporadaSelecionado);
			Episodio episodioRetorno = this.episodioService.save(this.episodio);

			if (this.episodio.equals(episodioRetorno)) {
				RequestContext.getCurrentInstance().execute("PF('dlgEpisodio').hide();");
				return;
			}
			if (this.temporadaSelecionado.getEpisodios() == null)
				this.temporadaSelecionado.setEpisodios(episodios);

			this.temporadaSelecionado.adicionaEpisodio(episodioRetorno);
			this.episodio = new Episodio();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		try {
			if (this.temporada.getEpisodios().size() > 0) {
				MessageUtils.getExceptionMessage("Existem temporada!");
				return;
			}
			this.serie.getTemporadas().remove(this.temporada);
			this.serieService.save(this.serie);

			this.temporadaService.remove(this.temporada);
			this.temporadas = temporadaService.listBySerie(serie);
			this.temporada = new Temporada();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void removeEpisodio() {
		try {
			this.episodioService.remove(this.episodio);

			this.temporadaSelecionado.getEpisodios().remove(this.episodio);

			this.episodio = new Episodio();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void atualizarEpisodio() {
		try {
			this.episodioService.save(this.episodio);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		this.temporada = new Temporada();
		this.temporadaSelecionado = new Temporada();
	}

	public void novoEpisodio() {
		this.episodio = new Episodio();
	}

	public void atualizarCheckTemporada(Temporada temporadaSerie, boolean checkSelection) {
		if (temporadaSerie != null) {
			temporadaSerie.setTem(checkSelection);
			this.temporadaService.save(temporadaSerie);
			this.temporadaService.listBySerie(serie);
		}
	}

	public void atualizarCheckEpisodioTem(Episodio episodioSerie, boolean checkSelection) {
		if (episodioSerie != null) {
			episodioSerie.setTem(checkSelection);
			this.episodioService.save(episodioSerie);
			this.temporadaService.listBySerie(serie);
		}
	}

	public void atualizarCheckEpisodio(Episodio episodioSerie, boolean checkSelection) {
		if (episodioSerie != null) {
			episodioSerie.setAssitiu(checkSelection);
			this.episodioService.save(episodioSerie);
			this.temporadaService.listBySerie(serie);
		}
	}

	private void atualizar() {
		this.temporadas = temporadaService.listBySerie(buscarSeriePorUrl());
		this.temporada = new Temporada();
		this.temporadaSelecionado = new Temporada();
	}

	private Serie buscarSeriePorUrl() {
		String codigoSerie = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("serieID");
		if (codigoSerie != null)
			return serieService.find(Long.parseLong(codigoSerie));
		return null;
	}

}