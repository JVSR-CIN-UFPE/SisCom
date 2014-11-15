package estimadores;

public class EomLee extends Estimador {

	@Override
	public int backlog(int e, int s, int c) {
		return 0;
	}

	private double beta(double y_1, int s, int c, double L) {
		return L / (y_1 * c + s);
	}
	
	private double gama(double bk) {
		return (1 - Math.pow(Math.E, -1.0/bk)) / (bk * (1.0 - (1.0 + 1.0/bk)*(Math.pow(Math.E, -1.0/bk))) );
	}

}
