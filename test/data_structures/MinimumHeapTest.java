package data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithms.AStar;

public class MinimumHeapTest {

	private MinimumHeap heap;
	private AStarNode[][] scoreMap;

	@Before
	public void setUp() throws Exception {
		scoreMap = new AStarNode[3][3];
		for (int i = 0; i < scoreMap.length; i++) {
			for (int j = 0; j < scoreMap[0].length; j++) {
				scoreMap[i][j] = new AStarNode();
			}
		}
		heap = new MinimumHeap(10, new NodeScorer(scoreMap));
	}

	@Test
	public void heapGivesAStarNodesInRightOrder() {
		Node node1 = new Node(new Coordinates(0, 0));
		scoreMap[0][0].setToEnd(4);
		Node node2 = new Node(new Coordinates(0, 1));
		scoreMap[0][1].setToEnd(55);
		Node node3 = new Node(new Coordinates(0, 2));
		scoreMap[0][2].setToEnd(1);
		Node node4 = new Node(new Coordinates(1, 0));
		scoreMap[1][0].setToEnd(4);
		Node node5 = new Node(new Coordinates(1, 1));
		scoreMap[1][1].setToEnd(3);

		heap.insert(node1);
		heap.insert(node2);
		heap.insert(node3);
		heap.insert(node4);
		heap.insert(node5);

		
		assertEquals(node3, heap.delete());
		assertEquals(node5, heap.delete());
		Coordinates c = heap.delete().getCoordinates();
		assertEquals(4, scoreMap[c.x][c.y].getToEnd(), 0.1);
		c = heap.delete().getCoordinates();
		assertEquals(4, scoreMap[c.x][c.y].getToEnd(), 0.1);
		assertEquals(node2, heap.delete());
	}

	@Test
	public void anEmptyHeapGivesNull() {
		assertNull(heap.delete());
		assertEquals("Trying to delete from empty heap reduces heap size", 0, heap.getSize());
	}

	@Test
	public void deletingWorksWhenHeapIsFull() {
		Node n = new Node(new Coordinates(0, 0));
		heap.insert(n);
		heap.insert(new Node(new Coordinates(0, 0)));
		heap.insert(new Node(new Coordinates(0, 0)));
		heap.insert(new Node(new Coordinates(0, 0)));
		heap.insert(new Node(new Coordinates(0, 0)));
		heap.insert(new Node(new Coordinates(0, 0)));
		heap.insert(new Node(new Coordinates(0, 0)));
		heap.insert(new Node(new Coordinates(0, 0)));
		heap.insert(new Node(new Coordinates(0, 0)));
		heap.insert(new Node(new Coordinates(0, 0)));

		assertEquals(n, heap.delete());
	}

}
