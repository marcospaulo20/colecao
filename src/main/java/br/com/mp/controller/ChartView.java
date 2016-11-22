package br.com.mp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.mp.model.manga.controller.MangaBean;

@Named
@ViewScoped
public class ChartView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PieChartModel pieMangaChartModel;
	
	@Inject
	private MangaBean mangaBean;
	
	public PieChartModel getPieMangaChartModel() {
		return pieMangaChartModel;
	}
	
	@PostConstruct
	public void init() { 
		createPieModels();
	}

	private void createPieModels() {
		createPieMangaChartModel();
	}

	private void createPieMangaChartModel() {
		pieMangaChartModel = new PieChartModel();
		
		pieMangaChartModel.set("Fisico", mangaBean.quantidadeMangaFisico());
		pieMangaChartModel.set("Virtual", mangaBean.quantidadeMangaVirtual());
		
		pieMangaChartModel.setTitle("Mangas");
		pieMangaChartModel.setLegendPosition("w");
		pieMangaChartModel.setShowDataLabels(true);
	}

}
