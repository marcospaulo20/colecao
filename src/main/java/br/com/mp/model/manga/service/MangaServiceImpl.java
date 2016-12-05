package br.com.mp.model.manga.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.manga.dao.MangaDAO;
import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Tipo;
import br.com.mp.util.service.ServiceAbstract;

public class MangaServiceImpl extends ServiceAbstract<Manga> implements MangaService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private MangaDAO mangaDAO; 
	
	@Inject
	public MangaServiceImpl(MangaDAO dao) {
		super(dao);
		this.mangaDAO = dao;
	}

	@Override
	public List<Manga> list() {
		return mangaDAO.list();
	}

	@Override
	public List<Manga> listFilter(Tipo tipo) {
		return mangaDAO.listFilter(tipo);
	}
	
}
