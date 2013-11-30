package data_structures;

public class NodeScorer implements Scorer {
	private AStarNode[][] scoreMap;

	public NodeScorer(AStarNode[][] scoreMap) {
		this.scoreMap = scoreMap;
	}

	@Override
	public double getScore(Object o) {
		if(!(o instanceof Node)){
			return Double.MAX_VALUE;
		}
		Node n = (Node)o;
		int x = n.getCoordinates().x;
		int y = n.getCoordinates().y;
		return scoreMap[x][y].getToEnd();
	}

}
