package br.com.mp.model.manga.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.util.dao.AbstractDAO;

public class VolumeDAOImpl extends AbstractDAO<Volume, Long> implements VolumeDAO, Serializable {

	private static final long serialVersionUID = 1L;	

	@Override
	public List<Volume> list() {
		String hql = "SELECT v FROM Volume v order by v.dataLancamento";
		
		TypedQuery<Volume> query = getEntityManager().createQuery(hql, Volume.class);
		return query.getResultList();
	}

	@Override
	public List<Volume> listByManga(Manga id) {
		String hql = "SELECT v FROM Volume v where v.manga = :mangaID order by v.dataLancamento";
		
		TypedQuery<Volume> query = getEntityManager().createQuery(hql, Volume.class);
		query.setParameter("mangaID", id);
		return query.getResultList();
	}

}
