package br.com.mp.model.serie.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.serie.dao.SerieDAO;
import br.com.mp.model.serie.entity.Serie;
import br.com.mp.util.service.ServiceAbstract;

public class SerieServiceImpl extends ServiceAbstract<Serie> implements SerieService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private SerieDAO serieDAO; 
	
	@Inject
	public SerieServiceImpl(SerieDAO dao) {
		super(dao);
		this.serieDAO = dao;
	}

	@Override
	public List<Serie> list() {
		return serieDAO.list();
	}

	@Override
	public Serie find(Long id) {
		return serieDAO.find(id);
	}


}
