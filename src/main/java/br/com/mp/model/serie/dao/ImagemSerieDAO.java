package br.com.mp.model.serie.dao;

import br.com.mp.model.serie.entity.ImagemSerie;
import br.com.mp.util.dao.DAO;

public interface ImagemSerieDAO extends DAO<ImagemSerie, Long> {

	ImagemSerie findBySerie(Long serieID);
}
