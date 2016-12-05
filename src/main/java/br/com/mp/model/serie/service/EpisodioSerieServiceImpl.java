package br.com.mp.model.serie.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.serie.dao.EpisodioSerieDAO;
import br.com.mp.model.serie.entity.EpisodioSerie;
import br.com.mp.model.serie.entity.TemporadaSerie;
import br.com.mp.util.service.ServiceAbstract;

public class EpisodioSerieServiceImpl extends ServiceAbstract<EpisodioSerie>
		implements EpisodioSerieService, Serializable {

	private static final long serialVersionUID = 1L;

	private EpisodioSerieDAO episodioSerieDAO;

	@Inject
	public EpisodioSerieServiceImpl(EpisodioSerieDAO dao) {
		super(dao);
		this.episodioSerieDAO = dao;
	}

	@Override
	public List<EpisodioSerie> list() {
		return episodioSerieDAO.list();
	}

	@Override
	public List<EpisodioSerie> listByTemporada(TemporadaSerie temporada) {
		return episodioSerieDAO.listByTemporada(temporada);
	}
	
}
