package br.com.mp.model.serie.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.serie.entity.EpisodioSerie;
import br.com.mp.model.serie.entity.TemporadaSerie;
import br.com.mp.util.dao.AbstractDAO;

public class EpisodioSerieDAOImpl extends AbstractDAO<EpisodioSerie, Long> implements EpisodioSerieDAO, Serializable {

	private static final long serialVersionUID = 1L;	

	@Override
	public List<EpisodioSerie> list() {
		String hql = "SELECT e FROM EpisodioSerie e order by e.nome";
		
		TypedQuery<EpisodioSerie> query = getEntityManager().createQuery(hql, EpisodioSerie.class);
		return query.getResultList();
	}

	@Override
	public List<EpisodioSerie> listByTemporada(TemporadaSerie id) {
		String hql = "SELECT e FROM EpisodioSerie e where e.temporada = :temporadaID order by e.nome";
		
		TypedQuery<EpisodioSerie> query = getEntityManager().createQuery(hql, EpisodioSerie.class);
		query.setParameter("temporadaID", id);
		return query.getResultList();
	}

	@Override
	public EpisodioSerie find(Long id) {
		return (EpisodioSerie) getSession().get(EpisodioSerie.class, id);
	}


}
