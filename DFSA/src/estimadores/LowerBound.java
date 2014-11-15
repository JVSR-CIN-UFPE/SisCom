package estimadores;

public class LowerBound extends Estimador {

	@Override
	public int backlog(double e, double s, double c) {
		this.n_til = (int)(s + 2*c);
		return this.n_til;
	}

}
