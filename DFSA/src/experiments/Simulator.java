package experiments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.data.xy.XYDataItem;

import estimators.EnumEstimador;
import estimators.Estimator;

public class Simulator {
	
	private int L;
	
	public Simulator(int L) {
		this.L = L;
	}
	
	public int[] randomTags(int L, int n) {
		int[] slots = new int[L];

		for(int i = 0; i < n; i ++) slots[(int)(Math.random()*L)]++;

		int[] status = new int[3];

		for(int i : slots) {
			if(i == 0) status[0]++;
			else if( i == 1) status[1]++;
			else status[2]++;
		}

		return status;
	}
	
	private List<XYDataItem> simulateTotal(int L, Estimator estimator) {
		List<XYDataItem> itens = new ArrayList<XYDataItem>();
		
		int experiments = 1000;
		
		// 100 to 1000 tags
		for(int tags_scenarios = 1; tags_scenarios <= 10; tags_scenarios++) {
			itens.add(new XYDataItem(tags_scenarios*100, 0));
			
			// Make experiments
			for(int i = 1; i <= experiments ; i++) {
				int tags = tags_scenarios*100;
				
				double sum_total = 0;
				
				int[] status = this.randomTags(L, tags);
				
				int e = status[0];
				int s = status[1];
				int c = status[2];
				
				while(c != 0){
					tags -= s;
					
					sum_total += L;
					
					int n_estimado = estimator.backlog(e, s, c);
					L = n_estimado - s;
					
					status = this.randomTags(L, tags);
					
					e = status[0];
					s = status[1];
					c = status[2];
				}
				
				XYDataItem item = itens.get(tags_scenarios-1);
				item.setY(item.getY().doubleValue() + sum_total/experiments);
			}
		}
		
		return itens;
	}
	
	private List<XYDataItem> simulateEmpty(int L, Estimator estimator) {
		List<XYDataItem> itens = new ArrayList<XYDataItem>();
		
		int experiments = 1000;
		
		// 100 to 1000 tags
		for(int tags_scenarios = 1; tags_scenarios <= 10; tags_scenarios++) {
			itens.add(new XYDataItem(tags_scenarios*100, 0));
			
			// Make experiments
			for(int i = 1; i <= experiments ; i++) {
				int tags = tags_scenarios*100;
				
				double sum_empty = 0;
				
				int[] status = this.randomTags(L, tags);
				
				int e = status[0];
				int s = status[1];
				int c = status[2];
				
				while(c != 0){
					tags -= s;
					
					sum_empty += e;
					
					int n_estimado = estimator.backlog(e, s, c);
					L = n_estimado - s;
					
					status = this.randomTags(L, tags);
					
					e = status[0];
					s = status[1];
					c = status[2];
				}
				
				XYDataItem item = itens.get(tags_scenarios-1);
				item.setY(item.getY().doubleValue() + sum_empty/experiments);
			}
		}
		
		return itens;
	}
	
	private List<XYDataItem> simulateCollision(int L, Estimator estimator) {
		List<XYDataItem> itens = new ArrayList<XYDataItem>();
		
		int experiments = 1000;
		
		// 100 to 1000 tags
		for(int tags_scenarios = 1; tags_scenarios <= 10; tags_scenarios++) {
			itens.add(new XYDataItem(tags_scenarios*100, 0));
			
			// Make experiments
			for(int i = 1; i <= experiments ; i++) {
				int tags = tags_scenarios*100;
				
				double sum_collision = 0;
				
				int[] status = this.randomTags(L, tags);
				
				int e = status[0];
				int s = status[1];
				int c = status[2];
				
				while(c != 0){
					tags -= s;
					
					sum_collision += c;
					
					int n_estimado = estimator.backlog(e, s, c);
					L = n_estimado - s;
					
					status = this.randomTags(L, tags);
					
					e = status[0];
					s = status[1];
					c = status[2];
				}
				
				XYDataItem item = itens.get(tags_scenarios-1);
				item.setY(item.getY().doubleValue() + sum_collision/experiments);
			}
		}
		
		return itens;
	}
	
	private List<XYDataItem> simulateError(Estimator estimator) {
		System.out.println("\n" + estimator.getName());
		List<XYDataItem> itens = new ArrayList<XYDataItem>();
		
		int experiments = 10;
		
		// 100 to 1000 tags
		for(int tags = 100; tags <= 100*10; tags+=100) {
			
			System.out.println("\nTags: " + tags);
			
			itens.add(new XYDataItem(tags, 0));
			
			// Make experiments
			for(int i = 1; i <= experiments ; i++) {
				
				int[] status = this.randomTags(this.L, tags);
				
				int e = status[0];
				int s = status[1];
				int c = status[2];
				
				double n = estimator.backlog(e, s, c);
				
				double error = 100*Math.abs(tags-n)/tags;
				
				System.out.println(e +", "+ s +", "+ c + " --> " + n + " >> Error: " + error);
				
				XYDataItem item = itens.get((tags/100)-1);
				item.setY(item.getY().doubleValue() + error/experiments);
			}
		}
		
		return itens;
	}
	
	public void simulateMultiples(String filename, List<Estimator> estimators) {
		
		GraphicSimulator graphicSimulatorCollision = new GraphicSimulator("Quant. Collision");
		
		for(Estimator estimator : estimators) {
			graphicSimulatorCollision.addSerie(estimator.getName(), this.simulateCollision(128, estimator));
		}
		
		graphicSimulatorCollision.exportGraphic(filename+"-collision", "Tags", "Quant", 1000, 2000);
		
		GraphicSimulator graphicSimulatorEmpty = new GraphicSimulator("Quant. Empty");
		
		for(Estimator estimator : estimators) {
			graphicSimulatorEmpty.addSerie(estimator.getName(), this.simulateEmpty(128, estimator));
		}
		
		graphicSimulatorEmpty.exportGraphic(filename+"-empty", "Tags", "Quant", 1000, 2000);
		
		GraphicSimulator graphicSimulatorTotal = new GraphicSimulator("Quant. Total");
		
		for(Estimator estimator : estimators) {
			graphicSimulatorTotal.addSerie(estimator.getName(), this.simulateTotal(128, estimator));
		}
		
		graphicSimulatorTotal.exportGraphic(filename+"-total", "Tags", "Quant", 1000, 4000);
	}
	
	public static void main(String[] args) {
		int L = 128;
		
//		new Simulator(L).simulateMultiples("just-lower-bound", Arrays.asList(EnumEstimador.LOWER_BOUND.getEstimator()));
//		new Simulator(L).simulateMultiples("just-schoute", Arrays.asList(EnumEstimador.SCHOUTE.getEstimator()));
//		new Simulator(L).simulateMultiples("just-vogt", Arrays.asList(EnumEstimador.VOGT.getEstimator()));
//		new Simulator(L).simulateMultiples("just-chen", Arrays.asList(EnumEstimador.CHEN.getEstimator()));
//		new Simulator(L).simulateMultiples("just-vahedi", Arrays.asList(EnumEstimador.VAHEDI.getEstimator()));
		new Simulator(L).simulateMultiples("all-estimators-for-1000", Arrays.asList(
				EnumEstimador.LOWER_BOUND.getEstimator(), 
				EnumEstimador.SCHOUTE.getEstimator(),
				EnumEstimador.CHEN.getEstimator()));
	}
}
