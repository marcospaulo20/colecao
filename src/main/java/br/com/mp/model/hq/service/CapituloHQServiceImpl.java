package br.com.mp.model.hq.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.hq.dao.CapituloHQDAO;
import br.com.mp.model.hq.entity.CapituloHQ;
import br.com.mp.model.hq.entity.Edicao;
import br.com.mp.util.service.ServiceAbstract;

public class CapituloHQServiceImpl extends ServiceAbstract<CapituloHQ> implements CapituloHQService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private CapituloHQDAO capituloHQDAO; 
	
	@Inject
	public CapituloHQServiceImpl(CapituloHQDAO dao) {
		super(dao);
		this.capituloHQDAO = dao;
	}

	@Override
	public List<CapituloHQ> list() {
		return capituloHQDAO.list();
	}

	@Override
	public List<CapituloHQ> listByEdicao(Edicao edicao) {
		return capituloHQDAO.listByEdicao(edicao);
	}

}
