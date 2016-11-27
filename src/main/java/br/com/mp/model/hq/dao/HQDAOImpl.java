package br.com.mp.model.hq.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.hq.entity.HQ;
import br.com.mp.util.dao.AbstractDAO;

public class HQDAOImpl extends AbstractDAO<HQ, Long> implements HQDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<HQ> list() {
		String hql = "SELECT hq FROM HQ hq order by hq.nome";
		
		TypedQuery<HQ> query = getEntityManager().createQuery(hql, HQ.class);
		return query.getResultList();
	}
	
	@Override
	public HQ find(Long id) {		
		return (HQ) getSession().get(HQ.class, id);
	}
}
