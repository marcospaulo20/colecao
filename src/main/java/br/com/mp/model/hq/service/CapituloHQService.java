package br.com.mp.model.hq.service;

import java.util.List;

import br.com.mp.model.hq.entity.CapituloHQ;
import br.com.mp.model.hq.entity.Edicao;
import br.com.mp.util.service.Service;

public interface CapituloHQService extends Service<CapituloHQ> {

	List<CapituloHQ> list();
	
	List<CapituloHQ> listByEdicao(Edicao edicao);
}
