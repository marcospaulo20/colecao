package br.com.mp.model.serie.service;

import java.util.List;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.util.service.Service;

public interface SerieService extends Service<Serie> {

	List<Serie> list();
}