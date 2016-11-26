package br.com.mp.model.manga.service;

import br.com.mp.model.manga.entity.ImagemManga;
import br.com.mp.util.service.Service;

public interface ImagemMangaService extends Service<ImagemManga> {

	ImagemManga getImageBytes(Long volumeID);

	ImagemManga findByVolumeId(Long codigoVolume);
}
