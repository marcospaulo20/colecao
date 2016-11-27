package br.com.mp.model.hq.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.mp.model.hq.dao.ImagemHQDAO;
import br.com.mp.model.hq.entity.ImagemHQ;
import br.com.mp.util.service.ServiceAbstract;

public class ImagemHQServiceImpl extends ServiceAbstract<ImagemHQ> implements ImagemHQService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private ImagemHQDAO imagemHqDAO; 
	
	@Inject
	public ImagemHQServiceImpl(ImagemHQDAO dao) {
		super(dao);
		this.imagemHqDAO = dao;
	}

	@Override
	public ImagemHQ getImageBytes(Long capituloID) {
		return imagemHqDAO.findByCapitulo(capituloID);
	}

	@Override
	public ImagemHQ findByCapituloId(Long codigoCapitulo) {
		return imagemHqDAO.findByCapitulo(codigoCapitulo);
	}

}
