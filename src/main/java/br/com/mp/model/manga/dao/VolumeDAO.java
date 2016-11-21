package br.com.mp.model.manga.dao;

import java.util.List;

import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.util.dao.DAO;

public interface VolumeDAO extends DAO<Volume, Long> {

	List<Volume> list();
	
	List<Volume> listByManga(Manga id);

	Volume find(Long id);
}
