package br.com.mp.model.manga.dao;

import java.util.List;

import br.com.mp.model.manga.entity.Capitulo;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.util.dao.DAO;

public interface CapituloDAO extends DAO<Capitulo, Long> {

	List<Capitulo> list();
	
	List<Capitulo> listByVolume(Volume volume);

}
