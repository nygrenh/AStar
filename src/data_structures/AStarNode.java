package data_structures;

public class AStarNode extends Node {
	
	private int toStart, toEnd;
	public int heapindex;
	private AStarNode cameFrom;
	private boolean blocked, evaluated, inHeap;

	public AStarNode(Coordinates coordinates) {
		super(coordinates);
		toStart = Integer.MAX_VALUE;
		toEnd = Integer.MAX_VALUE;
		cameFrom = null;
		blocked = false;
		evaluated = false;
		inHeap = false;
		heapindex = -1;
	}
	
	
	public int getToEnd() {
		return toEnd;
	}

	public void setToEnd(int score) {
		this.toEnd = score;
	}

	public int getToStart() {
		return toStart;
	}
	
	public void setToStart(int score){
		this.toStart = score;
	}
	
	public Node cameFrom(){
		return cameFrom;
	}
	
	public void setCameFrom(AStarNode cameFrom) {
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

	public boolean isEvaluated() {
		return evaluated;
	}

	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}

	public boolean isInHeap() {
		return inHeap;
	}

	public void setIsInHeap(boolean beingEvaluated) {
		this.inHeap = beingEvaluated;
	}
	
	public void reset(){
		this.toStart = Integer.MAX_VALUE;
		this.toEnd = Integer.MAX_VALUE;
		cameFrom = null;
		evaluated = false;
		inHeap = false;
	}
}
