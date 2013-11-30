package data_structures;

public class Node {
	
	private Coordinates coordinates;
	
	private NodeType type;
	
	public Node(Coordinates coordinates) {
		this(coordinates, 0);
	}
	
	public Node(Coordinates coordinates, double movementCost){
		this.coordinates = coordinates;
		this.type = NodeType.normal;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public double getMovementPenalty() {
		return type.movementPenalty;
	}

	public void setType(NodeType t) {
		this.type = t;
	}
}
