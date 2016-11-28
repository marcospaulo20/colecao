package br.com.mp.model.serie.controller;

import javax.inject.Inject;

import org.omnifaces.cdi.GraphicImageBean;

import br.com.mp.model.serie.service.ImagemSerieService;

@GraphicImageBean
public class ImagesSerie {

	@Inject
	private ImagemSerieService imagemSerieService;
	
	public byte[] get(Long id) {
		try {
			return imagemSerieService.getImageBytes(id).getImagem();
		} catch(Exception e) {
			return null;
		}
	}
}
