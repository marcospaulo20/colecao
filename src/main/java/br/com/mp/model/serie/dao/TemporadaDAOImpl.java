package br.com.mp.model.serie.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.model.serie.entity.Temporada;
import br.com.mp.util.dao.AbstractDAO;

public class TemporadaDAOImpl extends AbstractDAO<Temporada, Long> implements TemporadaDAO, Serializable {

	private static final long serialVersionUID = 1L;	

	@Override
	public List<Temporada> list() {
		String hql = "SELECT ts FROM Temporada ts order by ts.anoLancamento";
		
		TypedQuery<Temporada> query = getEntityManager().createQuery(hql, Temporada.class);
		return query.getResultList();
	}

	@Override
	public List<Temporada> listBySerie(Serie id) {
		String hql = "SELECT ts FROM Temporada ts where ts.serie = :serieID order by ts.anoLancamento";
		
		TypedQuery<Temporada> query = getEntityManager().createQuery(hql, Temporada.class);
		query.setParameter("serieID", id);
		return query.getResultList();
	}

}
