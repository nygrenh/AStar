package data_structures;

public class Node {
	private int score;
	private Node cameFrom;
	
	public Node() {
		this(0);
		cameFrom = null;
	}
	
	public Node(int score){
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public Node cameFrom(){
		return cameFrom;
	}
	
	public void setCameFrom(Node cameFrom) {
		this.cameFrom = cameFrom;
	}
}
