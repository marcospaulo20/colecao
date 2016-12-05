package br.com.mp.model.filme.service;

import java.util.List;

import br.com.mp.model.filme.entity.Filme;
import br.com.mp.util.service.Service;

public interface FilmeService extends Service<Filme> {

	List<Filme> list();
}
