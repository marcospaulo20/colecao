package br.com.mp.model.serie.service;

import br.com.mp.model.serie.entity.ImagemSerie;
import br.com.mp.util.service.Service;

public interface ImagemSerieService extends Service<ImagemSerie> {

	ImagemSerie getImageBytes(Long temporadaID);

	ImagemSerie findByTemporadaId(Long codigoTemporada);
}
