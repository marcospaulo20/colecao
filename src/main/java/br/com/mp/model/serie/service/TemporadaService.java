package br.com.mp.model.serie.service;

import java.util.List;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.Temporada;
import br.com.mp.util.service.Service;

public interface TemporadaService extends Service<Temporada> {

	List<Temporada> list();
	
	List<Temporada> listBySerie(Serie serie);
	
}
