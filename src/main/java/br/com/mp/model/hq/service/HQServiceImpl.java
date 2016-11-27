package br.com.mp.model.hq.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.hq.dao.HQDAO;
import br.com.mp.model.hq.entity.HQ;
import br.com.mp.util.service.ServiceAbstract;

public class HQServiceImpl extends ServiceAbstract<HQ> implements HQService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private HQDAO hqDAO; 
	
	@Inject
	public HQServiceImpl(HQDAO dao) {
		super(dao);
		this.hqDAO = dao;
	}

	@Override
	public List<HQ> list() {
		return hqDAO.list();
	}

	@Override
	public HQ find(Long id) {
		return hqDAO.find(id);
	}


}
