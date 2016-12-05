package br.com.mp.model.hq.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.hq.entity.Edicao;
import br.com.mp.model.hq.entity.HQ;
import br.com.mp.util.dao.AbstractDAO;

public class EdicaoDAOImpl extends AbstractDAO<Edicao, Long> implements EdicaoDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Edicao> list() {
		String hql = "SELECT e FROM Edicao e order by e.dataPublicacao";
		
		TypedQuery<Edicao> query = getEntityManager().createQuery(hql, Edicao.class);
		return query.getResultList();
	}

	@Override
	public List<Edicao> listByHQ(HQ id) {
		String hql = "SELECT e FROM Edicao e where e.hq = :hqID order by e.dataPublicacao";
		
		TypedQuery<Edicao> query = getEntityManager().createQuery(hql, Edicao.class);
		query.setParameter("hqID", id);
		return query.getResultList();
	}

}
