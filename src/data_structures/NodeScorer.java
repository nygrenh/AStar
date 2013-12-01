package data_structures;

import algorithms.AStar;

public class NodeScorer implements Scorer {
	private AStar aStar;

	public NodeScorer(AStar aStar) {
		this.aStar = aStar;
	}

	@Override
	public double getScore(Object o) {
		if (!(o instanceof Node)) {
			return Double.MAX_VALUE;
		}
		Node n = (Node) o;
		return aStar.getHelpNode(n).getToEnd();
	}

	public void updateHeapIndex(Node n, int index) {
		aStar.getHelpNode(n).heapindex = index;
	}

}
