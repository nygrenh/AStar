package data_structures;

public enum NodeType {
	normal(0), sand(1), wall(Double.MAX_VALUE), glue(5);
	
	public final double movementPenalty;
	
	private NodeType(double movementPenalty) {
		this.movementPenalty = movementPenalty;
	}
	
}
