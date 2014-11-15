package estimadores;

public class Vogt extends Estimador {
	
	private int n_til;
	
	@Override
	public int backlog(double e, double s, double c) {
		double L = e + s + c;
		double n = s + 2*c;
		double dist_n = -1;
		double dist_p = 0;
		
		while(dist_n < dist_p) {
			double pe = B(0, n, L);
			double ps = B(1, n, L);
			double pc = 1 - pe - ps;
			
			double ae = pe*L;
			double as = ps*L;
			double ac = pc*L;
			
			dist_p = dist_n;
			dist_n = (ae - e)*(ae - e) + (as - s)*(as - s) + (ac - c)*(ac - c);
			
			if(n == (s+2*c)) {
				dist_p = dist_n + 1;
			}
			
			n = n + 1;
		}
		
		this.n_til = (int)(n-1);
		
		return this.n_til;
	}

	private double combinatorial(double n, double r) {
		double result = 1;
		
		for (double i = 0; i < n-r; i++) {
			result = (result * (n-i)) / (i+1);
		}
		
		return result;
	}
	
	private double B(double r, double n, double L) {
		return combinatorial(n, r) * Math.pow( (1/L), r ) * Math.pow( (L-1.0)/L, (n-r) );
	}
	
}
