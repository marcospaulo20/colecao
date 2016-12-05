package br.com.mp.model.hq.service;

import java.util.List;

import br.com.mp.model.hq.entity.HQ;
import br.com.mp.util.service.Service;

public interface HQService extends Service<HQ> {

	List<HQ> list();
}
