package data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithms.AStar;

public class MinimumHeapTest {

	private MinimumHeap heap;
	private AStar aStar;

	@Before
	public void setUp() throws Exception {
		aStar = new AStar();
		heap = new MinimumHeap(10, new NodeScorer(aStar));
		Node[][] map = new Node[10][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				Coordinates coordinates = new Coordinates(i, j);
				map[i][j] = new Node(coordinates);
			}
		}
		aStar.findPath(new Node(new Coordinates(0, 0)), new Node(new Coordinates(0, 2)), map);
	}

	@Test
	public void heapGivesAStarNodesInRightOrder() {
		Node node1 = new Node(new Coordinates(0, 0));
		aStar.getHelpNode(node1).setToEnd(4);
		Node node2 = new Node(new Coordinates(0, 1));
		aStar.getHelpNode(node2).setToEnd(55);
		Node node3 = new Node(new Coordinates(0, 2));
		aStar.getHelpNode(node3).setToEnd(1);
		Node node4 = new Node(new Coordinates(1, 0));
		aStar.getHelpNode(node4).setToEnd(4);
		Node node5 = new Node(new Coordinates(1, 1));
		aStar.getHelpNode(node5).setToEnd(3);

		heap.insert(node1);
		heap.insert(node2);
		heap.insert(node3);
		heap.insert(node4);
		heap.insert(node5);

		
		assertEquals(node3, heap.delete());
		assertEquals(node5, heap.delete());
		assertEquals(4, aStar.getHelpNode(heap.delete()).getToEnd(), 0.1);
		assertEquals(4, aStar.getHelpNode(heap.delete()).getToEnd(), 0.1);
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
