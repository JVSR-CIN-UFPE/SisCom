package estimadores;

public abstract class Estimador {
	
	protected int n_til;
	
	public abstract int backlog( double e, double s, double c);
	
	public int frameLenght(int s) {
		return this.n_til - s;
	}
	
}
