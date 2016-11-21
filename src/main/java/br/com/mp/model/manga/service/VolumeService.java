package br.com.mp.model.manga.service;

import java.util.List;

import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.util.service.Service;

public interface VolumeService extends Service<Volume> {

	List<Volume> list();
	
	List<Volume> listByManga(Manga manga);
	
	Volume find(Long id);

}
