package br.com.mp.model.manga.dao;

import java.util.List;

import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Tipo;
import br.com.mp.util.dao.DAO;

public interface MangaDAO extends DAO<Manga, Long> {

	List<Manga> list();
	
	List<Manga> listFilter(Tipo tipo);	
}
