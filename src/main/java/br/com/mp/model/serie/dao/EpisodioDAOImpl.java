package br.com.mp.model.serie.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.serie.entity.Episodio;
import br.com.mp.model.serie.entity.Temporada;
import br.com.mp.util.dao.AbstractDAO;

public class EpisodioDAOImpl extends AbstractDAO<Episodio, Long> implements EpisodioDAO, Serializable {

	private static final long serialVersionUID = 1L;	

	@Override
	public List<Episodio> list() {
		String hql = "SELECT e FROM Episodio e order by e.numero";
		
		TypedQuery<Episodio> query = getEntityManager().createQuery(hql, Episodio.class);
		return query.getResultList();
	}

	@Override
	public List<Episodio> listByTemporada(Temporada id) {
		String hql = "SELECT e FROM Episodio e where e.temporada = :temporadaID order by e.numero";
		
		TypedQuery<Episodio> query = getEntityManager().createQuery(hql, Episodio.class);
		query.setParameter("temporadaID", id);
		return query.getResultList();
	}

}
