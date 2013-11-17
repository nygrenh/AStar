package data_structures;

public class Node {
	private int score;
	private Node cameFrom;
	private boolean blocked;
	
	public Node() {
		this(0);
		cameFrom = null;
		blocked = false;
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
	
	public boolean blocked() {
		return blocked;
	}
	
	public void toggleBlocked() {
		blocked = !blocked;
	}
	
	public void setBlocked(){
		blocked = true;
	}
	
	public void setUnblocked(){
		blocked = false;
	}
}
