package br.com.mp.model.serie.service;

import java.util.List;

import br.com.mp.model.serie.entity.Episodio;
import br.com.mp.model.serie.entity.Temporada;
import br.com.mp.util.service.Service;

public interface EpisodioService extends Service<Episodio> {

	List<Episodio> list();
	
	List<Episodio> listByTemporada(Temporada temporada);
	
}
