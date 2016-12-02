package br.com.mp.util.dao;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.mp.util.jpa.Transactional;

public abstract class AbstractDAO<T, ID> implements DAO<T, ID> {

	private final Class<T> persistentClass;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) ((Class<T>) getClass().getGenericSuperclass())
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public AbstractDAO(final Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	protected Class<T> getEntityClass() {
		return persistentClass;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.manager;
	}

	@Override
	public Session getSession() {
		return (Session) manager.getDelegate();
	}

	@Override
	@Transactional
	public T save(T entity) {
		return manager.merge(entity);
	}

	@Override
	@Transactional
	public void remove(T entity) {
		Object o = manager.merge(entity);
		manager.remove(o);
	}
	
	@SuppressWarnings("unchecked")
	public T find(Long id) {
		return (T) getSession().get(persistentClass, id);
	}

}
