package br.com.mp.model.manga.dao;

import br.com.mp.model.manga.entity.ImagemManga;
import br.com.mp.util.dao.DAO;

public interface ImagemMangaDAO extends DAO<ImagemManga, Long> {

	ImagemManga findByVolume(Long volumeID);
	
}
