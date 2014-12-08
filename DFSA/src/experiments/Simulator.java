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
	
	public int[] randomTags(int n) {
		int[] slots = new int[this.L];

		for(int i = 0; i < n; i ++) slots[(int)(Math.random()*L)]++;

		int[] status = new int[3];

		for(int i : slots) {
			if(i == 0) status[0]++;
			else if( i == 1) status[1]++;
			else status[2]++;
		}

		return status;
	}

	private List<XYDataItem> simulateSingle(Estimator estimator) {
		System.out.println("\n" + estimator.getName());
		List<XYDataItem> itens = new ArrayList<XYDataItem>();
		
		int experiments = 10;
		
		// 100 to 1000 tags
		for(int tags = 100; tags <= 100*10; tags+=100) {
			
			System.out.println("\nTags: " + tags);
			
			itens.add(new XYDataItem(tags, 0));
			
			// Make experiments
			for(int i = 1; i <= experiments ; i++) {
				
				int[] status = this.randomTags(tags);
				
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
		
		GraphicSimulator graphicSimulator = new GraphicSimulator("Error Estimative");
		
		for(Estimator estimator : estimators) {
			graphicSimulator.addSerie(estimator.getName(), this.simulateSingle(estimator));
		}
		
		graphicSimulator.exportGraphic(filename, "Tags", "Error (%)", 1000, 140);
	}
	
	public static void main(String[] args) {
		int L = 128;
		
//		new Simulator(L).simulateMultiples("just-lower-bound", Arrays.asList(EnumEstimador.LOWER_BOUND.getEstimator()));
//		new Simulator(L).simulateMultiples("just-schoute", Arrays.asList(EnumEstimador.SCHOUTE.getEstimator()));
//		new Simulator(L).simulateMultiples("just-vogt", Arrays.asList(EnumEstimador.VOGT.getEstimator()));
		new Simulator(L).simulateMultiples("just-chen", Arrays.asList(EnumEstimador.CHEN.getEstimator()));
		new Simulator(L).simulateMultiples("just-vahedi", Arrays.asList(EnumEstimador.VAHEDI.getEstimator()));
	}
}
