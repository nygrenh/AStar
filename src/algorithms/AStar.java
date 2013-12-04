package algorithms;

import data_structures.LinkedList;
import data_structures.MinimumHeap;
import data_structures.AStarNode;
import data_structures.Node;

import java.util.List;

public class AStar {

	private AStarNode[][] helpMap;

	/**
	 * Finds a path between start and end
	 * 
	 * @param start
	 * @param end
	 * @param map
	 * @return List, that contains the path
	 */
	public List<Node> findPath(Node start, Node end, Node[][] map) {
		generateHelpMap(map);
		MinimumHeap heap = new MinimumHeap(map.length * map[0].length);
		AStarNode aStart = new AStarNode(start);
		heap.insert(aStart);
		aStart.setToStart(0);
		aStart.setToEnd(Calculations.distanceBetween(start, end));
		while (heap.getSize() > 0) {
			AStarNode current = heap.delete();
			current.setIsInHeap(false);
			if (current.getNode() == end) {
				return reconstructPath(end);
			}
			current.setEvaluated(true); // for visualization purposes
			List<AStarNode> neighbours = getNeighbours(current);
			for (AStarNode neighbour : neighbours) {
				if (neighbour.getNode().getMovementPenalty() == Double.MAX_VALUE) {
					continue;
				}
				double toStartCandidate = current.getToStart() + movementCost(current, neighbour);
				double toEndCandidate = Calculations.distanceBetween(neighbour, end) + toStartCandidate;

				if (toEndCandidate < neighbour.getToEnd()) {
					neighbour.setCameFrom(current);
					neighbour.setToStart(toStartCandidate);
					neighbour.setToEnd(toEndCandidate);
					if (!neighbour.isInHeap()) {
						heap.insert(neighbour);
						neighbour.setIsInHeap(true);
					} else { // since this node's score has decreased, the heap
								// property might be broken
						heap.travelUpwards(neighbour.heapindex);
					}
				}
			}
		}
		return null;
	}

	private void generateHelpMap(Node[][] map) {
		helpMap = new AStarNode[map.length][map[0].length];
		for (Node[] nodes : map) {
			for (Node n : nodes) {
				int x = n.getCoordinates().x;
				int y = n.getCoordinates().y;
				helpMap[x][y] = new AStarNode(n);
			}
		}
	}

	/**
	 * Calculates the moving cost between two Nodes
	 * 
	 * @param current
	 *            .getNode()
	 * @param neighbour
	 * @return Double
	 */
	private double movementCost(AStarNode current, AStarNode neighbour) {
		double cost = 1;
		cost += current.getNode().getMovementPenalty() / 2;
		cost += neighbour.getNode().getMovementPenalty() / 2;
		if (Calculations.distanceBetween(current, neighbour) == 2)
			cost *= 1.414214;
		return cost;
	}

	/**
	 * Returns node n's neighbors
	 * 
	 * @param n
	 * @param map
	 *            Graph
	 * @return
	 */
	private List<AStarNode> getNeighbours(AStarNode an) {
		List<AStarNode> returnee = new LinkedList<AStarNode>();
		Node n = an.getNode();
		int x = n.getCoordinates().x;
		int y = n.getCoordinates().y;
		if (x != 0)
			returnee.add(helpMap[x - 1][y]);
		if (y != 0)
			returnee.add(helpMap[x][y - 1]);
		if (x != helpMap.length - 1)
			returnee.add(helpMap[x + 1][y]);
		if (y != helpMap[0].length - 1)
			returnee.add(helpMap[x][y + 1]);
		if (x != helpMap.length - 1 && y != helpMap[0].length - 1)
			returnee.add(helpMap[x + 1][y + 1]);
		if (x != 0 && y != 0)
			returnee.add(helpMap[x - 1][y - 1]);
		if (x != helpMap.length - 1 && y != 0)
			returnee.add(helpMap[x + 1][y - 1]);
		if (x != 0 && y != helpMap[0].length - 1)
			returnee.add(helpMap[x - 1][y + 1]);
		return returnee;
	}

	/**
	 * Returns the appropriate help variable container for node n.
	 * 
	 * @return AStarNode, if helpMap has been initialized, null otherwise
	 */
	public AStarNode getHelpNode(Node n) {
		if (helpMap == null) {
			return null;
		}
		int x = n.getCoordinates().x;
		int y = n.getCoordinates().y;
		return helpMap[x][y];
	}

	/**
	 * Reconstructs the path we've found
	 * 
	 * @param end
	 *            Destination node
	 * @return List, that contains our path from beginning to end
	 */
	private List<Node> reconstructPath(Node end) {
		LinkedList<Node> returnee = new LinkedList<Node>();
		AStarNode backTrackNode = getHelpNode(end);
		while (backTrackNode != null) {
			returnee.addAtTheBeginning(backTrackNode.getNode());
			backTrackNode = backTrackNode.cameFrom();
		}
		return returnee;
	}

}
