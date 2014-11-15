package estimadores;

public class LowerBound extends Estimador {

	@Override
	public int backlog(int e, int s, int c) {
		return s + 2*c;
	}

}
