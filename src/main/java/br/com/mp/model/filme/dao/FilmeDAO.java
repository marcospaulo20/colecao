package br.com.mp.model.filme.dao;

import java.util.List;

import br.com.mp.model.filme.entity.Filme;
import br.com.mp.util.dao.DAO;

public interface FilmeDAO extends DAO<Filme, Long> {

	List<Filme> list();
}
