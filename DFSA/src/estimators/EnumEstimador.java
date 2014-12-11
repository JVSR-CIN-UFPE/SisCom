package estimators;

public enum EnumEstimador {
	
	CHEN,
	EOM_LEE,
	LOWER_BOUND,
	SCHOUTE,
	VAHEDI,
	VOGT,
	ILCM;
	
	public Estimator getEstimator() {
		switch (this) {
		case CHEN:
			return new Chen();
		case EOM_LEE:
			return null; // TODO: Eom-Lee incomplete!!!
		case LOWER_BOUND:
			return new LowerBound();
		case SCHOUTE:
			return new Schoute();
		case VAHEDI:
			return new Vahedi();
		case VOGT:
			return new Vogt();
		case ILCM:
			return new ILCM();
		default:
			return null;
		}
	}
}
