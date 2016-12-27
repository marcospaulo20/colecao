package br.com.mp.model.serie.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.serie.dao.TemporadaDAO;
import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.Temporada;
import br.com.mp.util.service.ServiceAbstract;

public class TemporadaServiceImpl extends ServiceAbstract<Temporada> implements TemporadaService, Serializable {

	private static final long serialVersionUID = 1L;

	private TemporadaDAO temporadaSerieDAO;
	
	@Inject
	public TemporadaServiceImpl(TemporadaDAO dao) {
		super(dao);
		this.temporadaSerieDAO = dao;
	}

	@Override
	public List<Temporada> list() {
		return temporadaSerieDAO.list();
	}

	@Override
	public List<Temporada> listBySerie(Serie serie) {
		return temporadaSerieDAO.listBySerie(serie);
	}
}
