package data_structures;

public class Node {
	private Coordinates coordinates;
	
	public Node(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
}
