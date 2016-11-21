package br.com.mp.model.manga.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Tipo;
import br.com.mp.util.dao.AbstractDAO;

public class MangaDAOImpl extends AbstractDAO<Manga, Long> implements MangaDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Manga> list() {
		String hql = "SELECT m FROM Manga m order by m.nome";
		
		TypedQuery<Manga> query = getEntityManager().createQuery(hql, Manga.class);
		return query.getResultList();
	}

	@Override
	public List<Manga> listFilter(Tipo tipo) {
		
		String hql = "SELECT m FROM Manga m where m.tipo = :tipo order by m.nome";	
		
		TypedQuery<Manga> query = getEntityManager().createQuery(hql, Manga.class);
		query.setParameter("tipo", tipo);
		
		return query.getResultList();
	}

	@Override
	public Manga find(Long id) {		
		return (Manga) getSession().get(Manga.class, id);
	}

}
