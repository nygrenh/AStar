package data_structures;

public class AStarNode {
	
	private double toStart, toEnd;
	public int heapindex;
	private Node cameFrom, node;
	private boolean evaluated, inHeap;

	public AStarNode(Node node) {
		toStart = Double.MAX_VALUE;
		toEnd = Double.MAX_VALUE;
		cameFrom = null;
		this.node = node;
		evaluated = false;
		inHeap = false;
		heapindex = -1;
	}
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public double getToEnd() {
		return toEnd;
	}

	public void setToEnd(double score) {
		this.toEnd = score;
	}

	public double getToStart() {
		return toStart;
	}
	
	public void setToStart(double score){
		this.toStart = score;
	}
	
	public Node cameFrom(){
		return cameFrom;
	}
	
	public void setCameFrom(Node cameFrom) {
		this.cameFrom = cameFrom;
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
