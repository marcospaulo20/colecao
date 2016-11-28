package br.com.mp.model.serie.service;

import java.util.List;

import br.com.mp.model.serie.entity.EpisodioSerie;
import br.com.mp.model.serie.entity.TemporadaSerie;
import br.com.mp.util.service.Service;

public interface EpisodioSerieService extends Service<EpisodioSerie> {

	List<EpisodioSerie> list();
	
	List<EpisodioSerie> listByTemporada(TemporadaSerie temporada);
	
	EpisodioSerie find(Long id);
}
