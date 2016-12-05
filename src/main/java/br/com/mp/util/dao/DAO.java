package br.com.mp.util.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;

public interface DAO<T, ID> {

	EntityManager getEntityManager();

	Session getSession();

	T save(T entity);
	
	void remove(T entity);
	
	T find(Long id);
}
