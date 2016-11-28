package br.com.mp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.mp.model.filme.controller.FilmeBean;
import br.com.mp.model.hq.controller.HQBean;
import br.com.mp.model.manga.controller.MangaBean;

@Named
@ViewScoped
public class ChartView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PieChartModel pieLivroChartModel;
	
	private PieChartModel pieMangaChartModel;
	
	private PieChartModel pieHQChartModel;
	
	private PieChartModel pieFilmeChartModel;
	private PieChartModel pieFilmeAssistidoChartModel;
	
	@Inject
	private MangaBean mangaBean;
	
	@Inject
	private HQBean hqBean;
	
	@Inject
	private FilmeBean filmeBean;
	
	public PieChartModel getPieLivroChartModel() {
		return pieLivroChartModel;
	}
	
	public PieChartModel getPieMangaChartModel() {
		return pieMangaChartModel;
	}
	
	public PieChartModel getPieHQChartModel() {
		return pieHQChartModel;
	}
	
	public PieChartModel getPieFilmeChartModel() {
		return pieFilmeChartModel;
	}
	
	public PieChartModel getPieFilmeAssistidoChartModel() {
		return pieFilmeAssistidoChartModel;
	}
	
	@PostConstruct
	public void init() { 
		createPieModels();
	}

	private void createPieModels() {
		createPieLivroChartModel();
		createPieMangaChartModel();
		createPieHQChartModel();
		createPieFilmeChartModel();
		createPieFilmeAssistidoChartModel();
	}
	
	private void createPieLivroChartModel() {
		pieLivroChartModel = new PieChartModel();
		
		pieLivroChartModel.set("HQs", hqBean.quantidadeTotalHQ());
		pieLivroChartModel.set("Mangas", mangaBean.quantidadeTotalManga());
		
		pieLivroChartModel.setTitle("Livro");
		pieLivroChartModel.setLegendPosition("w");
		pieLivroChartModel.setShowDataLabels(true);
	}

	private void createPieMangaChartModel() {
		pieMangaChartModel = new PieChartModel();
		
		pieMangaChartModel.set("Fisico", mangaBean.quantidadeMangaFisico());
		pieMangaChartModel.set("Virtual", mangaBean.quantidadeMangaVirtual());
		
		pieMangaChartModel.setTitle("Mangas");
		pieMangaChartModel.setLegendPosition("w");
		pieMangaChartModel.setShowDataLabels(true);
	}
	
	private void createPieHQChartModel() {
		pieHQChartModel = new PieChartModel();
		
		pieHQChartModel.set("DC Comics", hqBean.quantidadeEditoraDC());
		pieHQChartModel.set("Marvel", hqBean.quantidadeEditoraMarvel());
		pieHQChartModel.set("Vertigo", hqBean.quantidadeEditoraVertigo());
		pieHQChartModel.set("Icon Comics", hqBean.quantidadeEditoraIcon());
		pieHQChartModel.set("Image", hqBean.quantidadeEditoraImage());
		
		pieHQChartModel.setTitle("HQs");
		pieHQChartModel.setLegendPosition("w");
		pieHQChartModel.setShowDataLabels(true);
	}

	private void createPieFilmeChartModel() {
		pieFilmeChartModel = new PieChartModel();
		
		pieFilmeChartModel.set("Tem", filmeBean.quantidadeTotalFilmeTem());
		pieFilmeChartModel.set("Não Tenho", filmeBean.quantidadeTotalFilmeNaoTem());
		
		pieFilmeChartModel.setTitle("Filmes");
		pieFilmeChartModel.setLegendPosition("w");
		pieFilmeChartModel.setShowDataLabels(true);
	}
	
	private void createPieFilmeAssistidoChartModel() {
		pieFilmeAssistidoChartModel = new PieChartModel();
		
		pieFilmeAssistidoChartModel.set("Assistir", filmeBean.quantidadeTotalFilmeAssistido());
		pieFilmeAssistidoChartModel.set("Não assistir", filmeBean.quantidadeTotalFilmeNaoAssistido());
		
		pieFilmeAssistidoChartModel.setTitle("Filmes");
		pieFilmeAssistidoChartModel.setLegendPosition("w");
		pieFilmeAssistidoChartModel.setShowDataLabels(true);
	}

}
