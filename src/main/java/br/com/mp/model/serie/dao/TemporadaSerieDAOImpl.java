package br.com.mp.model.serie.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.TemporadaSerie;
import br.com.mp.util.dao.AbstractDAO;

public class TemporadaSerieDAOImpl extends AbstractDAO<TemporadaSerie, Long> implements TemporadaSerieDAO, Serializable {

	private static final long serialVersionUID = 1L;	

	@Override
	public List<TemporadaSerie> list() {
		String hql = "SELECT ts FROM TemporadaSerie ts order by ts.anoLancamento";
		
		TypedQuery<TemporadaSerie> query = getEntityManager().createQuery(hql, TemporadaSerie.class);
		return query.getResultList();
	}

	@Override
	public List<TemporadaSerie> listBySerie(Serie id) {
		String hql = "SELECT ts FROM TemporadaSerie ts where ts.serie = :serieID order by ts.anoLancamento";
		
		TypedQuery<TemporadaSerie> query = getEntityManager().createQuery(hql, TemporadaSerie.class);
		query.setParameter("serieID", id);
		return query.getResultList();
	}

}
