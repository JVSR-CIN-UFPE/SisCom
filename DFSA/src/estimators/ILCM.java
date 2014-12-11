package estimators;

public class ILCM extends Estimator {

	public ILCM() {
		 super("ILCM");
	}
	
	@Override
	public int backlog(double e, double s, double c) {
		
		double L = e + s + c;
		
		double k = givemeK(c, L);
		double l = (1.2592 + 1.513*L) * (Math.tan(1.234 * Math.pow(L, -0.9907) * c));
		
		if(k < 0) {
			k = 0;
		}
		
		double n_til = k*s + l;
		
		if(c == 0) {
			n_til = s;
		}
		
		return (int)(Math.ceil(n_til));
	}
	
	private double givemeK(double c, double L) {
		
		double term1 = (4.344 * L) - 16.28;
		double term2 = L / ( -2.282 - 0.273*L ) ;
		double term3 = 0.2407 * ( Math.log(L + 42.56) );
		
		double k = ( c / (term1 + term2*c) ) + term3 ;
		
		return k;
	}
}
