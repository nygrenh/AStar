package data_structures;

public class Node {
	private int score;
	
	public Node() {
		this(0);
	}
	
	public Node(int score){
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
}
