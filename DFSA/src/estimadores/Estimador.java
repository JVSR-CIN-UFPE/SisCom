package estimadores;

public abstract class Estimador {
	
	protected int n_til;
	
	public abstract int backlog( int e, int s, int c);
	
	public int frameLenght(int s) {
		return this.n_til - s;
	}
	
}
