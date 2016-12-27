package br.com.mp.model.serie.dao;

import java.util.List;

import br.com.mp.model.serie.entity.Episodio;
import br.com.mp.model.serie.entity.Temporada;
import br.com.mp.util.dao.DAO;

public interface EpisodioDAO extends DAO<Episodio, Long> {

	List<Episodio> list();
	
	List<Episodio> listByTemporada(Temporada id);
	
}
