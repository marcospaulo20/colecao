package br.com.mp.controller;

import javax.inject.Inject;

import org.omnifaces.cdi.GraphicImageBean;

import br.com.mp.model.manga.service.ImagemMangaService;

@GraphicImageBean
public class Images {

	@Inject
	private ImagemMangaService imagemMangaService;
	
	public byte[] get(Long id) {
		try {
			return imagemMangaService.getImageBytes(id).getImagem();
		} catch(Exception e) {
			return null;
		}
	}
	
}
