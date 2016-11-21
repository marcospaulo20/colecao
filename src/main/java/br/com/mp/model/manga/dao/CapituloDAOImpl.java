package br.com.mp.model.manga.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.manga.entity.Capitulo;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.util.dao.AbstractDAO;

public class CapituloDAOImpl  extends AbstractDAO<Capitulo, Long> implements CapituloDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Capitulo> list() {
		String hql = "SELECT c FROM Capitulo c order by m.nome";
		
		TypedQuery<Capitulo> query = getEntityManager().createQuery(hql, Capitulo.class);
		return query.getResultList();
	}

	@Override
	public List<Capitulo> listByVolume(Volume volume) {
		String hql = "SELECT c FROM Capitulo c where c.volume = :volume order by m.nome";
		
		TypedQuery<Capitulo> query = getEntityManager().createQuery(hql, Capitulo.class);
		query.setParameter("volume", volume);
		return query.getResultList();
	}


}
