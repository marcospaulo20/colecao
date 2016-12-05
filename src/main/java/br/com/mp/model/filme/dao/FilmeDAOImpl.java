package br.com.mp.model.filme.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.filme.entity.Filme;
import br.com.mp.util.dao.AbstractDAO;

public class FilmeDAOImpl extends AbstractDAO<Filme, Long> implements FilmeDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Filme> list() {
		String hql = "SELECT f FROM Filme f order by f.nome";
		
		TypedQuery<Filme> query = getEntityManager().createQuery(hql, Filme.class);
		return query.getResultList();
	}
}
