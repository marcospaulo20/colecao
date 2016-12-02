package br.com.mp.model.serie.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.mp.model.serie.dao.ImagemSerieDAO;
import br.com.mp.model.serie.entity.ImagemSerie;
import br.com.mp.util.service.ServiceAbstract;

public class ImagemSerieServiceImpl extends ServiceAbstract<ImagemSerie> implements ImagemSerieService, Serializable {

	private static final long serialVersionUID = 1L;

	private ImagemSerieDAO imagemSerieDAO;

	@Inject
	public ImagemSerieServiceImpl(ImagemSerieDAO dao) {
		super(dao);
		this.imagemSerieDAO = dao;
	}

	@Override
	public ImagemSerie getImageBytes(Long temporadaID) {
		return imagemSerieDAO.findBySerie(temporadaID);
	}

	@Override
	public ImagemSerie findBySerieId(Long codigoSerie) {
		return imagemSerieDAO.findBySerie(codigoSerie);
	}

}
