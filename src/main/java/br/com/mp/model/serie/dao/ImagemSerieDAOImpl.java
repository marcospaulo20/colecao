package br.com.mp.model.serie.dao;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.mp.model.serie.entity.ImagemSerie;
import br.com.mp.util.dao.AbstractDAO;

public class ImagemSerieDAOImpl extends AbstractDAO<ImagemSerie, Long> implements ImagemSerieDAO, Serializable {

	private static final long serialVersionUID = 7382210071105601889L;

	@Override
	public ImagemSerie findBySerie(Long serieID) {
		try {
			String hql = "SELECT i FROM ImagemSerie i where i.codigoSerie = :serieID";
			
			TypedQuery<ImagemSerie> query = getEntityManager().createQuery(hql, ImagemSerie.class);
			query.setParameter("serieID", serieID);
			return query.getSingleResult();
		} catch(NoResultException nre) {
			return null;
		}
	}


}
