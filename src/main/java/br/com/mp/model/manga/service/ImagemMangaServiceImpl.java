package br.com.mp.model.manga.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.mp.model.manga.dao.ImagemMangaDAO;
import br.com.mp.model.manga.entity.ImagemManga;
import br.com.mp.util.service.ServiceAbstract;

public class ImagemMangaServiceImpl extends ServiceAbstract<ImagemManga> implements ImagemMangaService, Serializable {

	private static final long serialVersionUID = 1L;

	private ImagemMangaDAO imagemMangaDAO;
	
	@Inject
	public ImagemMangaServiceImpl(ImagemMangaDAO dao) {
		super(dao);
		this.imagemMangaDAO = dao;
	}

	@Override
	public ImagemManga getImageBytes(Long volumeID) {
		return imagemMangaDAO.findByVolume(volumeID);
	}
}
