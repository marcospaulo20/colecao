package br.com.mp.model.hq.service;

import java.util.List;

import br.com.mp.model.hq.entity.Edicao;
import br.com.mp.model.hq.entity.HQ;
import br.com.mp.util.service.Service;

public interface EdicaoService extends Service<Edicao> {

	List<Edicao> list();
	
	List<Edicao> listByHQ(HQ hq);
	
	Edicao find(Long id);
}
