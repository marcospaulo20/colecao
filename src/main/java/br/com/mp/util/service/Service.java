package br.com.mp.util.service;

import org.hibernate.service.spi.ServiceException;

public interface Service<T> {

	public void remove(T entity) throws ServiceException;

	public T save(T entity) throws ServiceException;
}
