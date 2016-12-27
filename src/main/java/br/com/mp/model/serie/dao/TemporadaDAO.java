package br.com.mp.model.serie.dao;

import java.util.List;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.Temporada;
import br.com.mp.util.dao.DAO;

public interface TemporadaDAO extends DAO<Temporada, Long> {

	List<Temporada> list();

	List<Temporada> listBySerie(Serie id);

}
