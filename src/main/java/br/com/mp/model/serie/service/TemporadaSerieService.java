package br.com.mp.model.serie.service;

import java.util.List;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.TemporadaSerie;
import br.com.mp.util.service.Service;

public interface TemporadaSerieService extends Service<TemporadaSerie> {

	List<TemporadaSerie> list();
	
	List<TemporadaSerie> listBySerie(Serie serie);
	
}
