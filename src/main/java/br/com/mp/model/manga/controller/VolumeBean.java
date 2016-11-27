package br.com.mp.model.manga.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.service.spi.ServiceException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.google.common.io.ByteStreams;
import com.sun.faces.util.MessageUtils;

import br.com.mp.model.manga.entity.Capitulo;
import br.com.mp.model.manga.entity.ImagemManga;
import br.com.mp.model.manga.entity.Manga;
import br.com.mp.model.manga.entity.Volume;
import br.com.mp.model.manga.service.CapituloService;
import br.com.mp.model.manga.service.ImagemMangaService;
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
	
	@Inject
	private ImagemMangaService imagemMangaService;

	private Volume volume;
	private List<Volume> volumes;

	private Volume volumeSelecionado;

	private Manga manga;

	private Capitulo capitulo;
	
	private ImagemManga imagem;
	@Inject
	private ImagesManga imageMangaBean;

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
	
	public ImagemManga getImagem() {
		return imagem;
	}

	@PostConstruct
	public void init() {
		atualizar();
		this.imagem = new ImagemManga();
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
	
	
	public void atualizarCheck(Volume volume, boolean checkSelection) {
		if(volume != null) {
			volume.setTem(checkSelection);
			this.volumeService.save(volume);
		}	
	}
	
	public void atualizarCheckCapitulo(Capitulo capitulo, boolean checkSelection) {
		if(capitulo != null) {
			capitulo.setTem(checkSelection);
			this.capituloService.save(capitulo);
			this.volumeService.listByManga(manga);
		}	
	}
	
	public void atualizarCheckLeuCapitulo(Capitulo capitulo, boolean checkSelection) {
		if(capitulo != null) {
			capitulo.setLeu(checkSelection);
			this.capituloService.save(capitulo);
			this.volumeService.listByManga(manga);
		}	
	}

	public void uploadImagem(FileUploadEvent event) throws IOException {
		InputStream is = event.getFile().getInputstream();
		byte[] imagemBytes = ByteStreams.toByteArray(is);
		
		Long codigoVolume = this.volume.getId(); 
		
		if(imageMangaBean.get(codigoVolume)==null){
			this.imagem.setCodigoVolume(codigoVolume);
			this.imagem.setUltimaModificacao(obterDataHoraAtual());
			
			this.imagem.setImagem(imagemBytes);
			this.imagemMangaService.save(imagem);
		} else {
			this.imagem = imagemMangaService.findByVolumeId(codigoVolume);
			
			this.imagem.setUltimaModificacao(obterDataHoraAtual());
			this.imagem.setImagem(imagemBytes);
			this.imagemMangaService.save(imagem);
			
			this.imageMangaBean.get(codigoVolume);
			this.imagem = new ImagemManga();
		}
		RequestContext.getCurrentInstance().update("form:tabela-volume");
	}
	
	private void atualizar() {
		this.volumes = volumeService.listByManga(buscarMangaPorUrl());	
		this.volume = new Volume();
		this.volumeSelecionado = new Volume();
	}
	
	public Date getUltimaModificaoFoto(Long id) { 
		try {
			Date dataHoraFoto = imagemMangaService.findByVolumeId(id).getUltimaModificacao();
			return dataHoraFoto == null ? obterDataHoraAtual() : dataHoraFoto;
		} catch(Exception e) {
			return null;
		}
	}

	private Manga buscarMangaPorUrl() {
		String codigoManga = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("mangaID");
		if (codigoManga != null)
			return mangaService.find(Long.parseLong(codigoManga));
		return null;
	}
	
	private Date obterDataHoraAtual() {
		return new Date();
	}

}