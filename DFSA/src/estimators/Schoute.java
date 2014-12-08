package estimators;

public class Schoute extends Estimator {

	public Schoute() {
		super("Schoute");
	}
	
	@Override
	public int backlog(double e, double s, double c) {
		return (int)(s + 2.39*c);
	}

}
