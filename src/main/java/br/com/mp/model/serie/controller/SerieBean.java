package br.com.mp.model.serie.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.service.spi.ServiceException;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.TipoClassificacao;
import br.com.mp.model.serie.service.SerieService;

@Named
@ViewScoped
public class SerieBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SerieService serieService;

	private Serie serie;
	private List<Serie> series;
	
	private TipoClassificacao tipoClassificacao;
	@SuppressWarnings("unused")
	private TipoClassificacao[] tipoClassificacaos;
	
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
	
	@PostConstruct
	public void init() {
		atualizar();
	}

	public void save() {
		try {
			this.serieService.save(this.serie);
			atualizar();
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

	
	private void atualizar() {
		this.series = this.serieService.list();
		this.serie = new Serie();
	}
}