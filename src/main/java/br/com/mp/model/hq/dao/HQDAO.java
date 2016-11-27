package br.com.mp.model.hq.dao;

import java.util.List;

import br.com.mp.model.hq.entity.HQ;
import br.com.mp.util.dao.DAO;

public interface HQDAO extends DAO<HQ, Long> {

	List<HQ> list();
	
	HQ find(Long id);
}
