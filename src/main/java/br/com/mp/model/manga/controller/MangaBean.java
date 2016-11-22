package br.com.mp.model.manga.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.service.spi.ServiceException;

import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Tipo;
import br.com.mp.model.manga.service.MangaService;

@Named
@ViewScoped
public class MangaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MangaService mangaService;

	private List<Manga> mangas;
	private Manga manga;
	
	private Tipo tipo;
	@SuppressWarnings("unused")
	private Tipo[] tipos;

	public List<Manga> getMangas() {
		return mangas;
	}

	public Manga getManga() {
		return manga;
	}

	public void setManga(Manga manga) {
		this.manga = manga;
	}

	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public Tipo[] getTipos() {
		return Tipo.values();
	}
	
	public void setTipos(Tipo[] tipos) {
		this.tipos = tipos;
	}

	@PostConstruct
	public void init() {
		atualizar();
	}

	public void save() {
		try {
			this.mangaService.save(this.manga);
			atualizar();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		try {
			this.mangaService.remove(this.manga);
			atualizar();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar() {
		this.mangas = mangaService.listFilter(Tipo.FISICO);
		this.manga = new Manga();
	}
	
	public void novo() {
		this.manga = new Manga();
	}

	public void onTipoChange() {
		if(this.tipo != null)
			this.mangas = this.mangaService.listFilter(tipo);
	}

	public Long quantidadeMangaFisico() {
		return this.mangaService.listFilter(Tipo.FISICO).stream().count();
	}
	
	public Long quantidadeMangaVirtual() {
		return this.mangaService.listFilter(Tipo.VIRTUAL).stream().count();
	}
}
