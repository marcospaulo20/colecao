package br.com.mp.model.manga.service;

import java.util.List;

import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Tipo;
import br.com.mp.util.service.Service;

public interface MangaService extends Service<Manga> {

	List<Manga> list();
	
	List<Manga> listFilter(Tipo tipo);
	
}
