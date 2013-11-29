package algorithms;

import data_structures.LinkedList;
import data_structures.List;
import data_structures.MinimumHeap;
import data_structures.Node;

public class AStar {
	public static final boolean diagonalMovement = false;

	public List findPath(Node start, Node end, Node[][] map) {
		resetHelpVariables(map);
		MinimumHeap heap = new MinimumHeap(map.length * map[0].length);
		heap.insert(start);
		start.setToStart(0);
		start.setToEnd(Calculations.distanceBetween(start, end));
		while (heap.getSize() > 0) {
			Node current = heap.delete();
			current.setIsInHeap(false);
			if (current == end) {
				return reconstructPath(end);
			}
			current.setEvaluated(true); // for visualization purposes
			processNeighbour(1, 0, current, end, map, heap);
			processNeighbour(0, 1, current, end, map, heap);
			processNeighbour(-1, 0, current, end, map, heap);
			processNeighbour(0, -1, current, end, map, heap);
			
			if(diagonalMovement){
				processNeighbour(1, 1, current, end, map, heap);
				processNeighbour(-1, -1, current, end, map, heap);
				processNeighbour(-1, 1, current, end, map, heap);
				processNeighbour(1, -1, current, end, map, heap);
			}

		}
		System.out.println("Failure");
		return null;
	}

	private void resetHelpVariables(Node[][] map) {
		for (Node[] nodes : map) {
			for (Node node : nodes) {
				node.reset();
			}
		}
	}

	private void processNeighbour(int dx, int dy, Node current, Node end, Node[][] map, MinimumHeap heap) {
		int x = current.getCoordinates().x + dx;
		int y = current.getCoordinates().y + dy;
		try {
			Node neighbor = map[x][y];
			if(neighbor.blocked()){
				return;
			}
			int toStartCandidate = current.getToStart() + 1;
			int toEndCandidate = Calculations.distanceBetween(neighbor, end) + toStartCandidate;
			
			if(toEndCandidate < neighbor.getToEnd()){
				neighbor.setCameFrom(current);
				neighbor.setToStart(toStartCandidate);
				neighbor.setToEnd(toEndCandidate);
				if(!neighbor.isInHeap()){
					heap.insert(neighbor);
					neighbor.setIsInHeap(true);
				} else { // since this node's score has decreased, the heap property might be broken
					heap.travelUpwards(neighbor.heapindex);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	public List reconstructPath(Node end) {
		LinkedList returnee = new LinkedList();
		Node backtrackNode = end;
		while (backtrackNode != null) {
			returnee.insertAtTheBeginning(backtrackNode);
			backtrackNode = backtrackNode.cameFrom();
		}
		return returnee;
	}

}
