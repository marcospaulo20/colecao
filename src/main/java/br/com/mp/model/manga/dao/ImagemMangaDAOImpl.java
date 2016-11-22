package br.com.mp.model.manga.dao;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.mp.model.manga.entity.ImagemManga;
import br.com.mp.util.dao.AbstractDAO;

public class ImagemMangaDAOImpl extends AbstractDAO<ImagemManga, Long> implements ImagemMangaDAO, Serializable {

	private static final long serialVersionUID = 7382210071105601889L;

	@Override
	public ImagemManga findByVolume(Long volumeID) {
		try {
			String hql = "SELECT i FROM ImagemManga i where i.codigoVolume = :volumeID";
			
			TypedQuery<ImagemManga> query = getEntityManager().createQuery(hql, ImagemManga.class);
			query.setParameter("volumeID", volumeID);
			return query.getSingleResult();
		} catch(NoResultException nre) {
			return null;
		}
	}
	
}
