package algorithms;

import data_structures.LinkedList;
import data_structures.List;
import data_structures.MinimumHeap;
import data_structures.AStarNode;

public class AStar {

	public List findPath(AStarNode start, AStarNode end, AStarNode[][] map) {
		resetHelpVariables(map);
		MinimumHeap heap = new MinimumHeap(map.length * map[0].length);
		heap.insert(start);
		start.setToStart(0);
		start.setToEnd(Calculations.distanceBetween(start, end));
		while (heap.getSize() > 0) {
			AStarNode current = heap.delete();
			current.setIsInHeap(false);
			if (current == end) {
				return reconstructPath(end);
			}
			current.setEvaluated(true); // for visualization purposes
			List neighbours = getNeighbours(current, map);

			do {
				AStarNode neighbor = neighbours.delete();
				if (neighbor.blocked()) {
					continue;
				}
				int toStartCandidate = current.getToStart() + movementCost(current, neighbor);
				int toEndCandidate = Calculations.distanceBetween(neighbor, end) + toStartCandidate;

				if (toEndCandidate < neighbor.getToEnd()) {
					neighbor.setCameFrom(current);
					neighbor.setToStart(toStartCandidate);
					neighbor.setToEnd(toEndCandidate);
					if (!neighbor.isInHeap()) {
						heap.insert(neighbor);
						neighbor.setIsInHeap(true);
					} else { // since this node's score has decreased, the heap
								// property might be broken
						heap.travelUpwards(neighbor.heapindex);
					}
				}
			} while (neighbours.getSize() != 0);

		}
		System.out.println("Failure");
		return null;
	}

	private int movementCost(AStarNode current, AStarNode neighbor) {
		if (Calculations.distanceBetween(current, neighbor) == 2) {
			return 14; // square root(10^2 + 10^2) ≈ 14
		}
		return 10;
	}

	private List getNeighbours(AStarNode n, AStarNode[][] map) {
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

	private void resetHelpVariables(AStarNode[][] map) {
		for (AStarNode[] nodes : map) {
			for (AStarNode node : nodes) {
				node.reset();
			}
		}
	}

	public List reconstructPath(AStarNode end) {
		LinkedList returnee = new LinkedList();
		AStarNode backtrackAStarNode = end;
		while (backtrackAStarNode != null) {
			returnee.insertAtTheBeginning(backtrackAStarNode);
			backtrackAStarNode = (AStarNode) backtrackAStarNode.cameFrom();
		}
		return returnee;
	}

}
