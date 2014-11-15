package leitores;

public class Tag {
	
	private int counter;
	
	public int startRound(int L) {
		this.counter = (int)(Math.random()*L);
		return this.counter;
	}
}
