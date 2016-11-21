package br.com.mp.util.service;

import org.hibernate.service.spi.ServiceException;

import br.com.mp.util.dao.DAO;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ServiceAbstract<T> implements Service<T> {

	private DAO dao;
	
	public ServiceAbstract(DAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void remove(T entity) throws ServiceException {
		dao.remove(entity);
	}

	@Override
	public T save(T entity) throws ServiceException {
		return (T) dao.save(entity);
	}

}
