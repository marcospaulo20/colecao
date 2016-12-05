package br.com.mp.model.filme.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.filme.dao.FilmeDAO;
import br.com.mp.model.filme.entity.Filme;
import br.com.mp.util.service.ServiceAbstract;

public class FilmeServiceImpl extends ServiceAbstract<Filme> implements FilmeService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private FilmeDAO filmeDAO; 
	
	@Inject
	public FilmeServiceImpl(FilmeDAO dao) {
		super(dao);
		this.filmeDAO = dao;
	}

	@Override
	public List<Filme> list() {
		return filmeDAO.list();
	}

}
