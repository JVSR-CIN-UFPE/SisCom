package estimadores;

public class Vahedi extends Estimador {

	private int n_til;
	
	@Override
	public int backlog(int e, int s, int c) {
		int L = e + s + c;
		int n = s + 2*c;
		double next = 0;
		double previous = -1;
		
		while(previous < next) {
			// TODO belong metohd
			/*
				while previous < next do
					P1 = (1 − (E/L))^n
					P2 = (n!/(S!*(n − S)!)*(( (L − E − S)^(n−S) )/((L − E)^n) ))*S!
					P3 = 0
					for k = 0, C do
						for v = 0, (C − k) do
							P3 = P3+(−1)k+v (C!/(k!(C−k)!)((C−k)!/(v!(C−k−v)!)((n−S)!/(n−S−k)!)((C−k−v)n−S−k /Cn−S)
						end for
					end for
					previous = next
					next = (L!/(E!S!C!))*P1*P2*P3
					n = n + 1
				end while
			 */
		}
		
		this.n_til = n - 2;
		return this.n_til;
	}

}
