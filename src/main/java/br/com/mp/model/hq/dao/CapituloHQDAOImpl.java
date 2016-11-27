package br.com.mp.model.hq.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.hq.entity.CapituloHQ;
import br.com.mp.model.hq.entity.Edicao;
import br.com.mp.util.dao.AbstractDAO;

public class CapituloHQDAOImpl extends AbstractDAO<CapituloHQ, Long> implements CapituloHQDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<CapituloHQ> list() {
		String hql = "SELECT c FROM CapituloHQ c order by c.numero";
		
		TypedQuery<CapituloHQ> query = getEntityManager().createQuery(hql, CapituloHQ.class);
		return query.getResultList();
	}
	
	@Override
	public List<CapituloHQ> listByEdicao(Edicao edicao) {
		String hql = "SELECT c FROM CapituloHQ c where c.edicao = :edicao order by c.numero";

		TypedQuery<CapituloHQ> query = getEntityManager().createQuery(hql, CapituloHQ.class);
		query.setParameter("edicao", edicao);
		return query.getResultList();
	}

	
	@Override
	public CapituloHQ find(Long id) {		
		return (CapituloHQ) getSession().get(CapituloHQ.class, id);
	}
}
