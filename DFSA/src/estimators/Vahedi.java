package estimators;

public class Vahedi extends Estimator {

	public Vahedi() {
		super("Vahedi");
	}
	
	@Override
	public int backlog(double e, double s, double c) {
		double L = e + s + c;
		double n = s + 2*c;
		
		double next = 0;
		double previous = -1;
		
		while(previous < next) {
			
			double p1 = Math.pow(1 - (e/L), n);
			
			
			double p2 = Math.pow((L-e-s) / (L-e), (n-s));
			
			for(int i = 0; i < s; i++) {
				double calc = (n - i) / (L-e);
				p2 = p2 * calc;
			}
			
			double p3 = 0.0;
			
			for(double k = 0; k <= c; k++) {
				for(double v = 0; v <= (c-k); v++) {
					
					double sum = Math.pow(-1, k+v)*Math.pow((c-k-v) / (c), (n-s-k));
					
					for(int i = 0; i < k; i++) {
						sum = sum * (( (c-i)/(k-i) ) * ( (n-s-i)/c )) ;
					}
					
					p3 = p3 + sum;
				}
			}
			
			previous = next;
			
			next = combinatorial(p1, L, e) * combinatorial(p2, (L-e), s) * p3;
			
			n++;
		}
		
		return (int)(n - 2);
	}
	
	private double combinatorial(double init, double n, double r) {
		double result = init;
		
		for (double i = 0; i < r; i++) {
			result = (result * (n-i)) / (i+1);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Vahedi vahedi = new Vahedi();
		double s = 30;
		double e = 107;
		double c = 300;
		
		System.out.println(vahedi.backlog(e, s, c));
		
//		double p1 = Math.pow(1.0 - (e/L), (s + 2*c));
//		System.out.println(p1);
//		System.out.println("\n------ NORMAL ------\n");
//		System.out.println(vahedi.combinatorial(L, e) * p1);
//		System.out.println("\n----- IMPROEVED -----\n");
//		System.out.println(vahedi.combinatorial(p1, L, e));
	}
}
