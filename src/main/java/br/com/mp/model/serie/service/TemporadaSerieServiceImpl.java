package br.com.mp.model.serie.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.serie.dao.TemporadaSerieDAO;
import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.TemporadaSerie;
import br.com.mp.util.service.ServiceAbstract;

public class TemporadaSerieServiceImpl extends ServiceAbstract<TemporadaSerie> implements TemporadaSerieService, Serializable {

	private static final long serialVersionUID = 1L;

	private TemporadaSerieDAO temporadaSerieDAO;
	
	@Inject
	public TemporadaSerieServiceImpl(TemporadaSerieDAO dao) {
		super(dao);
		this.temporadaSerieDAO = dao;
	}

	@Override
	public List<TemporadaSerie> list() {
		return temporadaSerieDAO.list();
	}

	@Override
	public List<TemporadaSerie> listBySerie(Serie serie) {
		return temporadaSerieDAO.listBySerie(serie);
	}
}
