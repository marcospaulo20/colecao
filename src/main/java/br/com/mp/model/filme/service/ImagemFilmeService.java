package br.com.mp.model.filme.service;

import br.com.mp.model.filme.entity.ImagemFilme;
import br.com.mp.util.service.Service;

public interface ImagemFilmeService extends Service<ImagemFilme> {

	ImagemFilme getImageBytes(Long volumeID);

	ImagemFilme findByFilmeId(Long codigoFilme);

}
