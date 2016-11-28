package br.com.mp.model.filme.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.mp.model.filme.dao.ImagemFilmeDAO;
import br.com.mp.model.filme.entity.ImagemFilme;
import br.com.mp.util.service.ServiceAbstract;

public class ImagemFilmeServiceImpl extends ServiceAbstract<ImagemFilme> implements ImagemFilmeService, Serializable {

	private static final long serialVersionUID = 1L;

	private ImagemFilmeDAO imagemFilmeDAO;

	@Inject
	public ImagemFilmeServiceImpl(ImagemFilmeDAO dao) {
		super(dao);
		this.imagemFilmeDAO = dao;
	}

	@Override
	public ImagemFilme getImageBytes(Long volumeID) {
		return imagemFilmeDAO.findByFilme(volumeID);
	}

	@Override
	public ImagemFilme findByFilmeId(Long codigoFilme) {
		return imagemFilmeDAO.findByFilme(codigoFilme);
	}
}
