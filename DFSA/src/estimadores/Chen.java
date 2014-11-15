package estimadores;

public class Chen extends Estimador {

	private int n_til;
	
	@Override
	public int backlog(int e, int s, int c) {
		int L = e + s + c;
		int n = s + 2*c;
		double next = 0;
		double previous = -1;
		
		while(previous < next) {
			double pe = B(0, n, L);
			double ps = B(1, n, L);
			double pc = 1 - pe - ps;
			
			previous = next;
			next = combinatorial(L, e) * combinatorial((L-e), s) * Math.pow(pe, e) * Math.pow(ps, s) * Math.pow(pc, c);
			n = n + 1;
		}
		
		this.n_til = n - 2;
		return this.n_til;
	}

	private double combinatorial(double n, double r) {
		double result = 1;
		
		for (double i = 0; i < n-r; i++) {
			result = (result * (n-i)) / (i+1);
		}
		
		return result;
	}
	
	private double B(int r, int n, double L) {
		return combinatorial(n, r) * Math.pow( (1/L), r ) * Math.pow( (L-1.0)/L, (n-r) );
	}
}
