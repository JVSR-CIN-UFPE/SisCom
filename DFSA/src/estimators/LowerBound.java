package estimators;

public class LowerBound extends Estimator {

	public LowerBound() {
		super("Lower Bound");
	}
	
	@Override
	public int backlog(double e, double s, double c) {
		return (int)(s + 2*c);
	}
}
