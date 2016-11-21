package br.com.mp.model.manga.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.service.spi.ServiceException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.sun.faces.util.MessageUtils;

import br.com.mp.model.manga.entity.Capitulo;
import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.model.manga.service.CapituloService;
import br.com.mp.model.manga.service.MangaService;
import br.com.mp.model.manga.service.VolumeService;

@Named
@ViewScoped
public class VolumeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VolumeService volumeService;

	@Inject
	private MangaService mangaService;

	@Inject
	private CapituloService capituloService;

	private Volume volume;
	private List<Volume> volumes;

	private Volume volumeSelecionado;

	private Manga manga;

	private Capitulo capitulo;

	private Double somaTotal;

	public Volume getVolume() {
		return volume;
	}

	public void setVolume(Volume volume) {
		this.volume = volume;
	}

	public List<Volume> getVolumes() {
		return volumes;
	}

	public Volume getVolumeSelecionado() {
		return volumeSelecionado;
	}

	public void setVolumeSelecionado(Volume volumeSelecionado) {
		this.volumeSelecionado = volumeSelecionado;
	}

	public Manga getManga() {
		if (this.manga == null) {
			this.manga = buscarMangaPorUrl();
			return manga;
		}
		return this.manga;
	}

	public Capitulo getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}

	@PostConstruct
	public void init() {
		atualizar();
	}

	public void save() {
		try {
			List<Capitulo> capitulos = new ArrayList<Capitulo>();
			
			this.volume.setManga(manga);
			Volume volumeRetorno = this.volumeService.save(this.volume);
			this.volumes = volumeService.listByManga(manga);

			if (this.volume.equals(volumeRetorno)) {
				RequestContext.getCurrentInstance().execute("PF('dlgVolume').hide();");
				return;
			}

			if (this.volume.getCapitulos() == null)
				this.volume.setCapitulos(capitulos);
			
			this.volume = new Volume();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void saveCapitulo() {
		try {
			List<Capitulo> capitulos = new ArrayList<Capitulo>();

			this.capitulo.setVolume(this.volumeSelecionado);
			Capitulo capituloRetorno = this.capituloService.save(this.capitulo);

			if (this.capitulo.equals(capituloRetorno)) {
				RequestContext.getCurrentInstance().execute("PF('dlgCapitulo').hide();");
				return;
			}
			if (this.volumeSelecionado.getCapitulos() == null)
				this.volumeSelecionado.setCapitulos(capitulos);

			this.volumeSelecionado.getCapitulos().add(capituloRetorno);
			this.capitulo = new Capitulo();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		try {
			if(this.volume.getCapitulos().size() > 0) {
				MessageUtils.getExceptionMessage("Existem capitulo!");
				return;
			}
			this.manga.getVolumes().remove(this.volume);
			this.mangaService.save(this.manga);
			
			this.volumeService.remove(this.volume);
			this.volumes = volumeService.listByManga(manga);
			this.volume = new Volume();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void removeCapitulo() {
		try {
			this.capituloService.remove(this.capitulo);

			this.volumeSelecionado.getCapitulos().remove(this.capitulo);

			this.capitulo = new Capitulo();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void atualizarCapitulo() {
		try {
			this.capituloService.save(this.capitulo);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		this.volume = new Volume();
		this.volumeSelecionado = new Volume();
	}

	public void novoCapitulo() {
		this.capitulo = new Capitulo();
	}
	
	public Double precoTotal() {
		this.somaTotal = 0.0;
		this.volumes.stream().filter(v->v.getPreco() != null).forEach(v->{
			this.somaTotal += v.getPreco();
		});
		return somaTotal;
	}
	
	public Double precoTotalTem() {
		this.somaTotal = 0.0;
		this.volumes.stream().filter(v->v.getTem()==true).forEach(v->{
			this.somaTotal += v.getPreco();
		});
		return somaTotal;
	}
	
	public Double precoTotalFatam() {
		this.somaTotal = 0.0;
		this.volumes.stream().filter(v->v.getTem()==false).forEach(v->{
			this.somaTotal += v.getPreco();
		});
		return somaTotal;
	}	
	
	
	public void onCheck(AjaxBehaviorEvent e) {		
		Volume volume = (Volume) e.getComponent().getAttributes().get("volume");
		if(volume != null)
			this.volumeService.save(volume);
	}

	private void atualizar() {
		this.volumes = volumeService.listByManga(buscarMangaPorUrl());	
		this.volume = new Volume();
		this.volumeSelecionado = new Volume();
	}

	private Manga buscarMangaPorUrl() {
		String codigoManga = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("mangaID");
		if (codigoManga != null)
			return mangaService.find(Long.parseLong(codigoManga));
		return null;
	}

}
