package br.com.mp.model.serie.dao;

import java.util.List;

import br.com.mp.model.serie.entity.EpisodioSerie;
import br.com.mp.model.serie.entity.TemporadaSerie;
import br.com.mp.util.dao.DAO;

public interface EpisodioSerieDAO extends DAO<EpisodioSerie, Long> {

	List<EpisodioSerie> list();
	
	List<EpisodioSerie> listByTemporada(TemporadaSerie id);
	
	EpisodioSerie find(Long id);
}
