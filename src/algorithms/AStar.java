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
		helpMap = new AStarNode[map.length][map[0].length];
		MinimumHeap heap = new MinimumHeap(map.length * map[0].length, new NodeScorer(helpMap));
		heap.insert(start);
		getHelpNode(start).setToStart(0);
		getHelpNode(start).setToEnd(Calculations.distanceBetween(start, end));
		while (heap.getSize() > 0) {
			Node current = heap.delete();
			AStarNode currentHelp = getHelpNode(current);
			currentHelp.setIsInHeap(false);
			if (current == end) {
				return reconstructPath(end);
			}
			currentHelp.setEvaluated(true); // for visualization purposes
			List neighbours = getNeighbours(current, map);

			while (neighbours.getSize() > 0) {
				Node neighbor = neighbours.delete();
				if (neighbor.getMovementPenalty() == Double.MAX_VALUE) {
					continue;
				}
				double toStartCandidate = currentHelp.getToStart() + movementCost(current, neighbor);
				double toEndCandidate = Calculations.distanceBetween(neighbor, end) + toStartCandidate;

				AStarNode neighbourHelp = getHelpNode(neighbor);
				if (toEndCandidate < neighbourHelp.getToEnd()) {
					neighbourHelp.setCameFrom(current);
					neighbourHelp.setToStart(toStartCandidate);
					neighbourHelp.setToEnd(toEndCandidate);
					if (!neighbourHelp.isInHeap()) {
						heap.insert(neighbor);
						neighbourHelp.setIsInHeap(true);
					} else { // since this node's score has decreased, the heap
								// property might be broken
						heap.travelUpwards(neighbourHelp.heapindex);
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

	/**
	 * Returns the appropriate help variable container for node n. Containers
	 * will be created on demand.
	 * 
	 * @return AStarNode, if helpMap has been initialized, null otherwise
	 */
	public AStarNode getHelpNode(Node n) {
		if (helpMap == null) {
			return null;
		}
		int x = n.getCoordinates().x;
		int y = n.getCoordinates().y;
		if (helpMap[x][y] == null) {
			helpMap[x][y] = new AStarNode();
		}
		return helpMap[x][y];
	}

	private List reconstructPath(Node end) {
		LinkedList returnee = new LinkedList();
		Node backTrackNode = end;
		while (backTrackNode != null) {
			returnee.insertAtTheBeginning(backTrackNode);
			backTrackNode = getHelpNode(backTrackNode).cameFrom();
		}
		return returnee;
	}

}
