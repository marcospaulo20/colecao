package br.com.mp.model.hq.dao;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.mp.model.hq.entity.ImagemHQ;
import br.com.mp.util.dao.AbstractDAO;

public class ImagemHQDAOImpl extends AbstractDAO<ImagemHQ, Long> implements ImagemHQDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public ImagemHQ findByCapitulo(Long capituloID) {
		try {
			String hql = "SELECT i FROM ImagemHQ i where i.codigoCapitulo = :capituloID";
			
			TypedQuery<ImagemHQ> query = getEntityManager().createQuery(hql, ImagemHQ.class);
			query.setParameter("capituloID", capituloID);
			return query.getSingleResult();
		} catch(NoResultException nre) {
			return null;
		}
	}
}
