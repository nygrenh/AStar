package algorithms;

import data_structures.LinkedList;
import data_structures.List;
import data_structures.MinimumHeap;
import data_structures.AStarNode;
import data_structures.Node;
import data_structures.NodeScorer;

public class AStar {

	private AStarNode[][] helpMap;

	public List findPath(Node start, Node end, Node[][] map) {
		resetHelpVariables(map);
		MinimumHeap heap = new MinimumHeap(map.length * map[0].length, new NodeScorer(helpMap));
		heap.insert(start);
		getHelpNode(start).setToStart(0);
		getHelpNode(start).setToEnd(Calculations.distanceBetween(start, end));
		while (heap.getSize() > 0) {
			Node current = heap.delete();
			getHelpNode(current).setIsInHeap(false);
			if (current == end) {
				return reconstructPath(end);
			}
			getHelpNode(current).setEvaluated(true); // for visualization
														// purposes
			List neighbours = getNeighbours(current, map);

			while (neighbours.getSize() > 0) {
				Node neighbor = neighbours.delete();
				if (neighbor.getMovementPenalty() == Double.MAX_VALUE) {
					continue;
				}
				double toStartCandidate = getHelpNode(current).getToStart() + movementCost(current, neighbor);
				double toEndCandidate = Calculations.distanceBetween(neighbor, end) + toStartCandidate;

				if (toEndCandidate < getHelpNode(neighbor).getToEnd()) {
					getHelpNode(neighbor).setCameFrom(current);
					getHelpNode(neighbor).setToStart(toStartCandidate);
					getHelpNode(neighbor).setToEnd(toEndCandidate);
					if (!getHelpNode(neighbor).isInHeap()) {
						heap.insert(neighbor);
						getHelpNode(neighbor).setIsInHeap(true);
					} else { // since this node's score has decreased, the heap
								// property might be broken
						heap.travelUpwards(getHelpNode(neighbor).heapindex);
					}
				}
			}

		}
		return null;
	}

	private double movementCost(Node current, Node neighbor) {
		double cost = 1;
		cost += current.getMovementPenalty() / 2;
		cost += neighbor.getMovementPenalty() / 2;
		if (Calculations.distanceBetween(current, neighbor) == 2)
			cost *= 1.414214;
		return cost;
	}

	private List getNeighbours(Node n, Node[][] map) {
		List returnee = new LinkedList();
		int x = n.getCoordinates().x;
		int y = n.getCoordinates().y;
		if (x != 0)
			returnee.insert(map[x - 1][y]);
		if (y != 0)
			returnee.insert(map[x][y - 1]);
		if (x != map.length - 1)
			returnee.insert(map[x + 1][y]);
		if (y != map[0].length - 1)
			returnee.insert(map[x][y + 1]);
		if (x != map.length - 1 && y != map[0].length - 1)
			returnee.insert(map[x + 1][y + 1]);
		if (x != 0 && y != 0)
			returnee.insert(map[x - 1][y - 1]);
		if (x != map.length - 1 && y != 0)
			returnee.insert(map[x + 1][y - 1]);
		if (x != 0 && y != map[0].length - 1)
			returnee.insert(map[x - 1][y + 1]);
		return returnee;
	}

	public AStarNode getHelpNode(Node n) throws ArrayIndexOutOfBoundsException {
		if (helpMap == null) {
			return null;
		}
		int x = n.getCoordinates().x;
		int y = n.getCoordinates().y;
		return helpMap[x][y];
	}

	private void resetHelpVariables(Node[][] map) {
		helpMap = new AStarNode[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				helpMap[i][j] = new AStarNode();
			}
		}
	}

	public List reconstructPath(Node end) {
		LinkedList returnee = new LinkedList();
		Node backTrackNode = end;
		while (backTrackNode != null) {
			returnee.insertAtTheBeginning(backTrackNode);
			backTrackNode = getHelpNode(backTrackNode).cameFrom();
		}
		return returnee;
	}

}
