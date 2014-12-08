package estimators;

public class Vogt extends Estimator {
	
	public Vogt() {
		super("Vogt");
	}
	
	@Override
	public int backlog(double e, double s, double c) {
		double L = e + s + c;
		double n = s + 2*c;
		double dist_n = -1;
		double dist_p = 0;
		
		while(dist_n < dist_p) {
			double pe = B(0, n, L); // = ( (L-1)/L )^(n)
			double ps = B(1, n, L); // = (n/L)*( ( (L-1)/L )^(n-1) )
			double pc = 1 - pe - ps;
			
//			System.out.println(pe == Math.pow((L-1)/L, n));
//			System.out.println(ps == Math.pow((L-1)/L, n-1)*(n/L));
			
//			System.out.println(ps == (n/(L-1))*pe);
//			System.out.println(pe == ((L-1)/n)*ps);
			
			double ae = pe*L; // = (L)*( ( (L-1)/L )^( n ) )
			double as = ps*L; // = (n)*( ( (L-1)/L )^(n-1) )
			double ac = pc*L; // = L - ae - as
			
//			System.out.println(ae == L*Math.pow((L-1)/L, n));
//			System.out.println(as == n*Math.pow((L-1)/L, n-1));
//			System.out.println(ac == (L-ae-as));
			
			dist_p = dist_n;
			dist_n = (ae - e)*(ae - e) + (as - s)*(as - s) + (ac - c)*(ac - c);
			
			if(n == (s+2*c)) {
				dist_p = dist_n + 1;
			}
			
			n = n + 1;
		}
		
		return (int)(n-1);
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
