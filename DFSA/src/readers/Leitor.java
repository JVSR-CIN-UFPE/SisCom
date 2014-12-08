package readers;

import java.util.ArrayList;
import java.util.List;

import estimators.Estimator;
import estimators.LowerBound;

public class Leitor {
	
	private Estimator estimador;
	private int L;
	
	public Leitor() {
		this.estimador = new LowerBound();
		this.L = 32;
	}
	
	public void identify(List<Tag> tags) {
		List<Integer> slots = new ArrayList<>();
		int v, s, c;
		
		for(Tag t : tags) slots.set(t.startRound(this.L), slots.get(t.startRound(this.L))+1);
		
		for(int i : slots) {
//			if();
		}
		
		boolean collision = true;
		
		while(collision) {
			
		}
	}
}
