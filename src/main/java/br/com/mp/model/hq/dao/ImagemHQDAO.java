package br.com.mp.model.hq.dao;

import br.com.mp.model.hq.entity.ImagemHQ;
import br.com.mp.util.dao.DAO;

public interface ImagemHQDAO extends DAO<ImagemHQ, Long> {

	ImagemHQ findByCapitulo(Long capituloID);
	
}
