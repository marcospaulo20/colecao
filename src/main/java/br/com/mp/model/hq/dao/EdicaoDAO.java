package br.com.mp.model.hq.dao;

import java.util.List;

import br.com.mp.model.hq.entity.Edicao;
import br.com.mp.model.hq.entity.HQ;
import br.com.mp.util.dao.DAO;

public interface EdicaoDAO extends DAO<Edicao, Long> {

	List<Edicao> list();
	
	List<Edicao> listByHQ(HQ id);
}
