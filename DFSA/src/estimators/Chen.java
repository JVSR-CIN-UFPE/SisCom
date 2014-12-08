package estimators;

public class Chen extends Estimator {

	public Chen() {
		super("Chen");
	}
	
	@Override
	public int backlog(double e, double s, double c) {
		double L = e + s + c;
		double n = s + 2*c;
		double next = 0;
		double previous = -1;
		
		while(previous < next) {
			double pe = Math.pow(1-(1/L), n);
			double ps = (n/L)*Math.pow(1-(1/L), n-1);
			double pc = 1 - pe - ps;
			
			previous = next;
			
			next = combinatorialP(L, e, pe) * (combinatorialP((L-e), s, ps) * Math.pow(pc, c));
			
			n = n + 1;
		}
		
		return (int)(n-2);
	}

	private double combinatorialP(double n, double r, double p) {
		double result = 1;
		
		for (double i = 0; i < r; i++) {
			result = (result * p * (n-i)) / (i+1);
		}
		
		return result;
	}
}
