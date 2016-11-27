package br.com.mp.model.hq.service;

import br.com.mp.model.hq.entity.ImagemHQ;
import br.com.mp.util.service.Service;

public interface ImagemHQService extends Service<ImagemHQ> {

	ImagemHQ getImageBytes(Long capituloID);

	ImagemHQ findByCapituloId(Long codigoCapitulo);
}
