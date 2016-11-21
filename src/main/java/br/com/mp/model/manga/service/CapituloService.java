package br.com.mp.model.manga.service;

import java.util.List;

import br.com.mp.model.manga.entity.Capitulo;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.util.service.Service;

public interface CapituloService extends Service<Capitulo> {

	List<Capitulo> list();
	
	List<Capitulo> listByVolume(Volume volume);

}
