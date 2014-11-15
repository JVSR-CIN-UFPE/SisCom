package estimadores;

public class Chen extends Estimador {

	private int n_til;
	
	@Override
	public int backlog(double e, double s, double c) {
		double L = e + s + c;
		double n = s + 2*c;
		double next = 0;
		double previous = -1;
		
		while(previous < next) {
			double pe = BP(0, n, L);
			double ps = BP(1, n, L);
			double pc = 1 - pe - ps;
			
			previous = next;
			
			// Metodo normal
//			next = fatorial(next);
			
			// Metodo melhorado 1
//			next = combinatorial(L, e) * combinatorial((L-e), s) * Math.pow(pe, e) * Math.pow(ps, s) * Math.pow(pc, c);
			
			// Metodo melhorado 2
			next = combinatorialP(L, e, pe) * combinatorialP((L-e), s, ps) * Math.pow(pc, c);
			
			n = n + 1;
		}
		
		this.n_til = (int)(n - 2);
		return this.n_til;
	}

	private double combinatorialP(double n, double r, double p) {
		double result = 1;
		
		for (double i = 0; i < r; i++) {
			result = (result * p * (n-i)) / (i+1);
		}
		
		return result;
	}
	
	private double combinatorial(double n, double r) {
		double result = 1;
		
		for (double i = 0; i < r; i++) {
			result = (result * (n-i)) / (i+1);
		}
		
		return result;
	}

	private double BP(double r, double n, double L) {
		return combinatorialP(n, r, (1/L)) * Math.pow( (L-1.0)/L, (n-r) );
	}
	
	private double B(double r, double n, double L) {
		return combinatorial(n, r) * Math.pow( (1/L), r ) * Math.pow( (L-1.0)/L, (n-r) );
	}
	
	private double fatorial(double n) {
		double result = 1;
		
		while(n > 1) {
			result = result * n;
			n--;
		}
		
		return result;
	}
	
//	public static void main(String[] args) {
//		Chen chen = new Chen();
//		double p = 0.068712;
//		double L = 1100;
//		double s = L/4;
//		double e = L/2;
//		double c = L/4;
//		System.out.println("\n\n\n----- TESTS B -----\n");
//		System.out.println("\n-----   NORMAL  -----\n");
//		System.out.println(chen.B(1, 2*c+s, L));
//		System.out.println("\n----- IMPROEVED -----\n");
//		System.out.println(chen.BP(1, 2*c+s, L));
//		System.out.println("\n\n\n----- TESTS COMBINATORIAL -----\n");
//		System.out.println(chen.combinatorialP(L, e, p));
//		System.out.println("\n----- NORMAL -----\n");
//		System.out.println(chen.combinatorial(L, e));
//		System.out.println(Math.pow(p, e));
//		System.out.println(chen.combinatorial(L, e) * Math.pow(p, e));
//	}
}
