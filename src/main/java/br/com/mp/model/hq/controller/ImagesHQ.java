package br.com.mp.model.hq.controller;

import javax.inject.Inject;

import org.omnifaces.cdi.GraphicImageBean;

import br.com.mp.model.hq.service.ImagemHQService;

@GraphicImageBean
public class ImagesHQ {

	@Inject
	private ImagemHQService imagemHQService;
	
	public byte[] get(Long id) {
		try {
			return imagemHQService.getImageBytes(id).getImagem();
		} catch(Exception e) {
			return null;
		}
	}
}
