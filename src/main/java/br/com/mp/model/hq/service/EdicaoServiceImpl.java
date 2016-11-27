package br.com.mp.model.hq.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.hq.dao.EdicaoDAO;
import br.com.mp.model.hq.entity.Edicao;
import br.com.mp.model.hq.entity.HQ;
import br.com.mp.util.service.ServiceAbstract;

public class EdicaoServiceImpl extends ServiceAbstract<Edicao> implements EdicaoService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private EdicaoDAO edicaoDAO; 
	
	@Inject
	public EdicaoServiceImpl(EdicaoDAO dao) {
		super(dao);
		this.edicaoDAO = dao;
	}

	@Override
	public List<Edicao> list() {
		return edicaoDAO.list();
	}
	
	@Override
	public List<Edicao> listByHQ(HQ hq) {
		return edicaoDAO.listByHQ(hq);
	}

	@Override
	public Edicao find(Long id) {
		return edicaoDAO.find(id);
	}


}
