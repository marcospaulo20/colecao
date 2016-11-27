package br.com.mp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.mp.model.hq.controller.HQBean;
import br.com.mp.model.manga.controller.MangaBean;

@Named
@ViewScoped
public class ChartView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PieChartModel pieLivroChartModel;
	
	private PieChartModel pieMangaChartModel;
	
	private PieChartModel pieHQChartModel;
	
	@Inject
	private MangaBean mangaBean;
	
	@Inject
	private HQBean hqBean;
	
	public PieChartModel getPieLivroChartModel() {
		return pieLivroChartModel;
	}
	
	public PieChartModel getPieMangaChartModel() {
		return pieMangaChartModel;
	}
	
	public PieChartModel getPieHQChartModel() {
		return pieHQChartModel;
	}
	
	@PostConstruct
	public void init() { 
		createPieModels();
	}

	private void createPieModels() {
		createPieLivroChartModel();
		createPieMangaChartModel();
		createPieHQChartModel();
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
		
		pieHQChartModel.setTitle("HQs");
		pieHQChartModel.setLegendPosition("w");
		pieHQChartModel.setShowDataLabels(true);
	}		

}
