package br.com.mp.model.hq.dao;

import java.util.List;

import br.com.mp.model.hq.entity.CapituloHQ;
import br.com.mp.model.hq.entity.Edicao;
import br.com.mp.util.dao.DAO;

public interface CapituloHQDAO extends DAO<CapituloHQ, Long> {

	List<CapituloHQ> list();
	
	List<CapituloHQ> listByEdicao(Edicao edicao);
	
	CapituloHQ find(Long id);
}
