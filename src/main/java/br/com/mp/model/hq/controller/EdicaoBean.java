package br.com.mp.model.hq.controller;

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

import br.com.mp.model.hq.entity.CapituloHQ;
import br.com.mp.model.hq.entity.Edicao;
import br.com.mp.model.hq.entity.HQ;
import br.com.mp.model.hq.entity.ImagemHQ;
import br.com.mp.model.hq.service.CapituloHQService;
import br.com.mp.model.hq.service.EdicaoService;
import br.com.mp.model.hq.service.HQService;
import br.com.mp.model.hq.service.ImagemHQService;

@Named
@ViewScoped
public class EdicaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EdicaoService edicaoService;
	@Inject
	private HQService hqService;
	@Inject
	private CapituloHQService capituloHQService;
	@Inject
	private ImagemHQService imagemHQService;

	private Edicao edicao;
	private List<Edicao> edicoes;

	private Edicao edicaoSelecionado;

	private HQ hq;

	private CapituloHQ capituloHQ;

	private ImagemHQ imagemHQ;
	
	@Inject
	private ImagesHQ imagesHQBean;

	public Edicao getEdicao() {
		return edicao;
	}

	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}

	public List<Edicao> getEdicoes() {
		return edicoes;
	}

	public Edicao getEdicaoSelecionado() {
		return edicaoSelecionado;
	}

	public void setEdicaoSelecionado(Edicao edicaoSelecionado) {
		this.edicaoSelecionado = edicaoSelecionado;
	}

	public HQ getHq() {
		if(this.hq == null) {
			this.hq = buscarHQPorUrl();
			return this.hq;
		}
		return this.hq;
	}

	public CapituloHQ getCapituloHQ() {
		return capituloHQ;
	}

	public void setCapituloHQ(CapituloHQ capituloHQ) {
		this.capituloHQ = capituloHQ;
	}

	public ImagemHQ getImagemHQ() {
		return imagemHQ;
	}

	public void setImagemHQ(ImagemHQ imagemHQ) {
		this.imagemHQ = imagemHQ;
	}
	
	@PostConstruct
	public void init() {
		atualizar();
		this.imagemHQ = new ImagemHQ();
	}
	
	public void save() {
		try {
			List<CapituloHQ> capitulos = new ArrayList<CapituloHQ>();
			
			this.edicao.setHq(hq);
			Edicao edicaoRetorno = this.edicaoService.save(this.edicao);
			this.edicoes = edicaoService.listByHQ(hq);

			if (this.edicao.equals(edicaoRetorno)) {
				RequestContext.getCurrentInstance().execute("PF('dlgEdicao').hide();");
				return;
			}

			if (this.edicao.getCapitulos() == null)
				this.edicao.setCapitulos(capitulos);
			
			this.edicao = new Edicao();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void saveCapitulo() {
		try {
			List<CapituloHQ> capitulos = new ArrayList<CapituloHQ>();

			this.capituloHQ.setEdicao(this.edicaoSelecionado);
			CapituloHQ capituloRetorno = this.capituloHQService.save(this.capituloHQ);

			if (this.capituloHQ.equals(capituloRetorno)) {
				RequestContext.getCurrentInstance().execute("PF('dlgCapitulo').hide();");
				return;
			}
			if (this.edicaoSelecionado.getCapitulos() == null)
				this.edicaoSelecionado.setCapitulos(capitulos);

			this.edicaoSelecionado.getCapitulos().add(capituloRetorno);
			this.capituloHQ = new CapituloHQ();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		try {
			if(this.edicao.getCapitulos().size() > 0) {
				MessageUtils.getExceptionMessage("Existem capitulo!");
				return;
			}
			this.hq.getEdicoes().remove(this.edicao);
			this.hqService.save(this.hq);
			
			this.edicaoService.remove(this.edicao);
			this.edicoes = edicaoService.listByHQ(hq);
			this.edicao = new Edicao();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void removeCapitulo() {
		try {
			this.capituloHQService.remove(this.capituloHQ);

			this.edicaoSelecionado.getCapitulos().remove(this.capituloHQ);

			this.capituloHQ = new CapituloHQ();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void atualizarCapitulo() {
		try {
			this.capituloHQService.save(this.capituloHQ);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		this.edicao = new Edicao();
		this.edicaoSelecionado = new Edicao();
	}

	public void novoCapitulo() {
		this.capituloHQ = new CapituloHQ();
	}
	
	public void atualizarCheck(Edicao edicao, boolean checkSelection) {
		if(edicao != null) {
			edicao.setTem(checkSelection);
			this.edicaoService.save(edicao);
		}	
	}
	
	public void atualizarCheckCapitulo(CapituloHQ capitulo, boolean checkSelection) {
		if(capitulo != null) {
			capitulo.setTem(checkSelection);
			this.capituloHQService.save(capitulo);
			this.edicaoService.listByHQ(hq);
		}				
	}
	
	public void atualizarCheckLiCapitulo(CapituloHQ capitulo, boolean checkSelection) {
		if(capitulo != null) {
			capitulo.setLeu(checkSelection);
			this.capituloHQService.save(capitulo);
			this.edicaoService.listByHQ(hq);
		}				
	}
	
	public void uploadImagem(FileUploadEvent event) throws IOException {
		InputStream is = event.getFile().getInputstream();
		byte[] imagemBytes = ByteStreams.toByteArray(is);
		
		Long codigoCapitulo = this.capituloHQ.getId();
		
		this.imagemHQ = new ImagemHQ();
		
		if(imagesHQBean.get(codigoCapitulo)==null){
			this.imagemHQ.setCodigoCapitulo(codigoCapitulo);
			this.imagemHQ.setUltimaModificacao(obterDataHoraAtual());
			
			this.imagemHQ.setImagem(imagemBytes);
			this.imagemHQService.save(imagemHQ);			
		} else {
			this.imagemHQ = imagemHQService.findByCapituloId(codigoCapitulo);
			this.imagemHQ.setUltimaModificacao(obterDataHoraAtual());
			
			this.imagemHQ.setImagem(imagemBytes);
			this.imagemHQService.save(imagemHQ);			
		}
		
		RequestContext.getCurrentInstance().update("form-capitulo:tabela-capitulo");
	}
	
	public Date getUltimaModificaoFoto(Long id) { 
		try {
			Date dataHoraFoto = imagemHQService.findByCapituloId(id).getUltimaModificacao();
			return dataHoraFoto == null ? obterDataHoraAtual() : dataHoraFoto;
		} catch(Exception e) {
			return null;
		}
	}

	private void atualizar() {
		this.edicoes = this.edicaoService.listByHQ(buscarHQPorUrl());
		this.edicao = new Edicao();
		this.edicaoSelecionado = new Edicao();
	}
	
	private HQ buscarHQPorUrl() {
		String codigoHQ = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("hqID");
		if (codigoHQ != null)
			return hqService.find(Long.parseLong(codigoHQ));
		return null;
	}
	
	private Date obterDataHoraAtual() {
		return new Date();
	}
}
