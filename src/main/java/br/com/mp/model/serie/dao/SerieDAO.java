package br.com.mp.model.serie.dao;

import java.util.List;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.util.dao.DAO;

public interface SerieDAO extends DAO<Serie, Long> {

	List<Serie> list();
}
