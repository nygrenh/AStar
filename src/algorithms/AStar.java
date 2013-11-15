package algorithms;

import data_structures.LinkedList;
import data_structures.Node;

public class AStar {

	public LinkedList reconstructPath(Node end) {
		LinkedList returnee = new LinkedList();
		Node backtrackNode = end;
		while (backtrackNode != null) {
			returnee.insertAtTheBeginning(backtrackNode);
			backtrackNode = backtrackNode.cameFrom();
		}
		return returnee;
	}
}
