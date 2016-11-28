package br.com.mp.model.hq.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.service.spi.ServiceException;

import br.com.mp.model.hq.entity.HQ;
import br.com.mp.model.hq.entity.TipoEditora;
import br.com.mp.model.hq.service.HQService;

@Named(value = "hqBean")
@ViewScoped
public class HQBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HQService hqService;

	private HQ hq;
	private List<HQ> hqs;

	private TipoEditora tipoEditora;
	@SuppressWarnings("unused")
	private TipoEditora[] tipoEditoras;

	public HQ getHq() {
		return hq;
	}

	public void setHq(HQ hq) {
		this.hq = hq;
	}

	public List<HQ> getHqs() {
		return hqs;
	}

	public TipoEditora getTipoEditora() {
		return tipoEditora;
	}

	public void setTipoEditora(TipoEditora tipoEditora) {
		this.tipoEditora = tipoEditora;
	}

	public TipoEditora[] getTipoEditoras() {
		return TipoEditora.values();
	}

	public void setTipoEditoras(TipoEditora[] tipoEditoras) {
		this.tipoEditoras = tipoEditoras;
	}

	@PostConstruct
	public void init() {
		atualizar();
	}

	public void save() {
		try {
			this.hqService.save(this.hq);
			atualizar();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		try {
			this.hqService.remove(this.hq);
			atualizar();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		this.hq = new HQ();
	}
	
	public Long quantidadeEditoraDC() {
		return this.hqs.stream().filter(h->h.getEditora().equals(TipoEditora.DC)).count();
	}
	
	public Long quantidadeEditoraMarvel() {
		return this.hqs.stream().filter(h->h.getEditora().equals(TipoEditora.MARVEL)).count();
	}
	
	public Number quantidadeEditoraVertigo() {
		return this.hqs.stream().filter(h->h.getEditora().equals(TipoEditora.VERTIGO)).count();	
	}

	public Number quantidadeEditoraImage() {
		return this.hqs.stream().filter(h->h.getEditora().equals(TipoEditora.IMAGE)).count();	
	}
	
	public Number quantidadeEditoraIcon() {
		return this.hqs.stream().filter(h->h.getEditora().equals(TipoEditora.ICON)).count();	
	}
	
	public int quantidadeTotalHQ() {
		return this.hqs.size();
	}

	private void atualizar() {
		this.hqs = this.hqService.list();
		this.hq = new HQ();
	}

}
