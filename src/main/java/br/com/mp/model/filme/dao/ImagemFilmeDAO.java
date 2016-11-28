package br.com.mp.model.filme.dao;

import br.com.mp.model.filme.entity.ImagemFilme;
import br.com.mp.util.dao.DAO;

public interface ImagemFilmeDAO extends DAO<ImagemFilme, Long> {

	ImagemFilme findByFilme(Long filmeID);
}
