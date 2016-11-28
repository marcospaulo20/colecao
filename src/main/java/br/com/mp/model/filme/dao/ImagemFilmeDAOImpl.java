package br.com.mp.model.filme.dao;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.mp.model.filme.entity.ImagemFilme;
import br.com.mp.util.dao.AbstractDAO;

public class ImagemFilmeDAOImpl extends AbstractDAO<ImagemFilme, Long> implements ImagemFilmeDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public ImagemFilme findByFilme(Long filmeID) {
		try {
			String hql = "SELECT i FROM ImagemFilme i where i.codigoFilme = :filmeID";
			
			TypedQuery<ImagemFilme> query = getEntityManager().createQuery(hql, ImagemFilme.class);
			query.setParameter("filmeID", filmeID);
			return query.getSingleResult();
		} catch(NoResultException nre) {
			return null;
		}
	}
}
