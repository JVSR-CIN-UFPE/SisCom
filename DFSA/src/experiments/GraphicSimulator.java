package experiments;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class GraphicSimulator extends ApplicationFrame {

	private XYSeriesCollection data;
	
	public GraphicSimulator(final String title) {
		super(title);
		
		this.data = new XYSeriesCollection();
	}
	
	public void addSerie(String serieName, List<XYDataItem> serieItens) {
		
		XYSeries serie = new XYSeries(serieName);
		
		for(XYDataItem item : serieItens) {
			serie.add(item);
		}
		
		this.data.addSeries(serie);
	}
	
	public void exportGraphic(String filename, String xAxisLabel, String yAxisLabel, int xMax, int yMax) {
		
		try {
			JFreeChart chart = ChartFactory.createXYLineChart(this.getTitle(), xAxisLabel, yAxisLabel, this.data,
					PlotOrientation.VERTICAL, true, true, false);
	
			NumberAxis xAxis = new NumberAxis();
//			xAxis.setRange(100, xMax);
			xAxis.setLabel("Quant. Tags");
			
			NumberAxis yAxis = new NumberAxis();
//			yAxis.setTickUnit(new NumberTickUnit(10));
//			yAxis.setRange(0, yMax);
			yAxis.setLabel("Slots");
		
			XYPlot plot = (XYPlot) chart.getPlot();
			plot.setDomainAxis(xAxis);
			plot.setRangeAxis(yAxis);
			plot.setBackgroundPaint(Color.WHITE);
			
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
			renderer.setBaseShapesVisible(true);
			
			/* Step -3 : Write line chart to a file */               
			int width=640; /* Width of the image */
			int height=480; /* Height of the image */                
			File lineChart=new File("images/"+filename+".png");              
			ChartUtilities.saveChartAsPNG(lineChart,chart,width,height);
			
			System.out.println("Exported!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
