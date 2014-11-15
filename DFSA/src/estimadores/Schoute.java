package estimadores;

public class Schoute extends Estimador {

	@Override
	public int backlog(int e, int s, int c) {
		return (int)(s + 2.39*c);
	}

}
