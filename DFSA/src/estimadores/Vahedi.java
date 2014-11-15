package estimadores;

public class Vahedi extends Estimador {

	private int n_til;
	
	@Override
	public int backlog(double e, double s, double c) {
		
		double L = e + s + c;
		double n = s + 2*c;
		
		double next = 0;
		double previous = -1;
		
		while(previous < next) {
			double p1 = Math.pow(1 - (e/L), n);

			// P2 Normal
			double p2 = combinatorial(n, s) * (Math.pow(L-e-s, n-s) / Math.pow(L-e, n)) * fatorial(s);

			// TODO: P2 Melhorado 1
			// TODO: P2 Melhorado 2
			
			double p3 = 0.0;
			
			for(double k = 0; k <= c; k++) {
				for(double v = 0; v <= (c-k); v++) {
					// TODO: Melhorar urgente
					p3 = p3 + (Math.pow(-1, k+v) * combinatorial(c, k) * combinatorial(c-k, v) 
							* (fatorial(n-s) / fatorial(n-s-k)) * (Math.pow(c-k-v, n-s-k) / Math.pow(c, n-s)) );
				}
			}
			
			previous = next;
			
			// next normal
//			next = (fatorial(L)/(fatorial(e)*fatorial(s)*fatorial(c))) * p1 * p2 * p3;
			
			// next melhorado 1
//			next = combinatorial(L, e) * combinatorial((L-e), s) * p1 * p2 * p3;
			
			// next melhorado 2
			next = combinatorial(p1, L, e) * combinatorial(p2, (L-e), s) * p3;
			
			n++;
		}
		
		this.n_til = (int)(n - 2);
		return this.n_til;
	}
	
	private double combinatorial(double init, double n, double r) {
		double result = init;
		
		for (double i = 0; i < r; i++) {
			result = (result * (n-i)) / (i+1);
		}
		
		return result;
	}
	
	private double combinatorial(double n, double r) {
		return combinatorial(1, n, r);
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
//		Vahedi vahedi = new Vahedi();
//		double s = 30*124.5;
//		double e = 107*124.5;
//		double c = 300*124.5;
//		double L = e+s+c;
//		
//		double p1 = Math.pow(1.0 - (e/L), (s + 2*c));
//		System.out.println(p1);
//		System.out.println("\n------ NORMAL ------\n");
//		System.out.println(vahedi.combinatorial(L, e) * p1);
//		System.out.println("\n----- IMPROEVED -----\n");
//		System.out.println(vahedi.combinatorial(p1, L, e));
//	}
}
