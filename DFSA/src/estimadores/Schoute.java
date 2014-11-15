package estimadores;

public class Schoute extends Estimador {

	@Override
	public int backlog(double e, double s, double c) {
		this.n_til = (int)(s + 2.39*c);
		return this.n_til;
	}

}
