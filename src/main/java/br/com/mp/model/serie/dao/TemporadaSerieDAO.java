package br.com.mp.model.serie.dao;

import java.util.List;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.TemporadaSerie;
import br.com.mp.util.dao.DAO;

public interface TemporadaSerieDAO extends DAO<TemporadaSerie, Long> {

	List<TemporadaSerie> list();

	List<TemporadaSerie> listBySerie(Serie id);

	TemporadaSerie find(Long id);
}
