package br.com.mp.model.filme.controller;

import javax.inject.Inject;

import org.omnifaces.cdi.GraphicImageBean;

import br.com.mp.model.filme.service.ImagemFilmeService;

@GraphicImageBean
public class ImagesFilme {

	@Inject
	private ImagemFilmeService imagemFilmeService;
	
	public byte[] get(Long id) {
		try {
			return imagemFilmeService.getImageBytes(id).getImagem();
		} catch(Exception e) {
			return null;
		}
	}
}
