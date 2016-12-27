package br.com.mp.model.serie.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.serie.dao.EpisodioDAO;
import br.com.mp.model.serie.entity.Episodio;
import br.com.mp.model.serie.entity.Temporada;
import br.com.mp.util.service.ServiceAbstract;

public class EpisodioServiceImpl extends ServiceAbstract<Episodio>
		implements EpisodioService, Serializable {

	private static final long serialVersionUID = 1L;

	private EpisodioDAO episodioSerieDAO;

	@Inject
	public EpisodioServiceImpl(EpisodioDAO dao) {
		super(dao);
		this.episodioSerieDAO = dao;
	}

	@Override
	public List<Episodio> list() {
		return episodioSerieDAO.list();
	}

	@Override
	public List<Episodio> listByTemporada(Temporada temporada) {
		return episodioSerieDAO.listByTemporada(temporada);
	}
	
}
