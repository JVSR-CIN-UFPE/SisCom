package estimators;

public abstract class Estimator {
	
	protected String name;
	
	public Estimator(String name) {
		this.name = name;
	}
	
	public abstract int backlog( double e, double s, double c);
	
	public int frameLenght(int n_til, int s) {
		return n_til - s;
	}
	
	public String getName() {
		return name;
	}
}