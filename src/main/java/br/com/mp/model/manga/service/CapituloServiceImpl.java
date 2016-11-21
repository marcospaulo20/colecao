package br.com.mp.model.manga.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.manga.dao.CapituloDAO;
import br.com.mp.model.manga.entity.Capitulo;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.util.service.ServiceAbstract;

public class CapituloServiceImpl extends ServiceAbstract<Capitulo> implements CapituloService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private CapituloDAO capituloDAO; 
	
	@Inject
	public CapituloServiceImpl(CapituloDAO dao) {
		super(dao);
		this.capituloDAO = dao;
	}

	@Override
	public List<Capitulo> list() {
		return capituloDAO.list();
	}

	@Override
	public List<Capitulo> listByVolume(Volume volume) {
		return capituloDAO.listByVolume(volume);
	}

}
