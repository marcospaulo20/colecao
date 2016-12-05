package br.com.mp.model.serie.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.serie.entity.Serie;
import br.com.mp.util.dao.AbstractDAO;

public class SerieDAOImpl extends AbstractDAO<Serie, Long> implements SerieDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Serie> list() {
		String hql = "SELECT s FROM Serie s order by s.nome";

		TypedQuery<Serie> query = getEntityManager().createQuery(hql, Serie.class);
		return query.getResultList();
	}

}
