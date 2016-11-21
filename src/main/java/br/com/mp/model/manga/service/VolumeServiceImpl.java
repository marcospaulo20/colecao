package br.com.mp.model.manga.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.mp.model.manga.dao.VolumeDAO;
import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.util.service.ServiceAbstract;

public class VolumeServiceImpl extends ServiceAbstract<Volume> implements VolumeService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private VolumeDAO volumeDAO;
	
	@Inject
	public VolumeServiceImpl(VolumeDAO dao) {
		super(dao);
		this.volumeDAO = dao;
	}

	@Override
	public List<Volume> list() {
		return volumeDAO.list();
	}

	@Override
	public List<Volume> listByManga(Manga manga) {
		return volumeDAO.listByManga(manga);
	}

	@Override
	public Volume find(Long id) {
		return volumeDAO.find(id);
	}
	
}
