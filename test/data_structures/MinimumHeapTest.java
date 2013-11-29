package data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MinimumHeapTest {

	private MinimumHeap heap;

	@Before
	public void setUp() throws Exception {
		heap = new MinimumHeap(10);
	}

	@Test
	public void heapGivesAStarNodesInRightOrder() {
		AStarNode node1 = new AStarNode(new Coordinates(0, 0));
		node1.setToEnd(4);
		AStarNode node2 = new AStarNode(new Coordinates(0, 0));
		node2.setToEnd(55);
		AStarNode node3 = new AStarNode(new Coordinates(0, 0));
		node3.setToEnd(1);
		AStarNode node4 = new AStarNode(new Coordinates(0, 0));
		node4.setToEnd(4);
		AStarNode node5 = new AStarNode(new Coordinates(0, 0));
		node5.setToEnd(3);

		heap.insert(node1);
		heap.insert(node2);
		heap.insert(node3);
		heap.insert(node4);
		heap.insert(node5);

		assertEquals(node3, heap.delete());
		assertEquals(node5, heap.delete());
		assertEquals(4, heap.delete().getToEnd(), 0.1);
		assertEquals(4, heap.delete().getToEnd(), 0.1);
		assertEquals(node2, heap.delete());
	}

	@Test
	public void anEmptyHeapGivesNull() {
		assertNull(heap.delete());
		assertEquals("Trying to delete from empty heap reduces heap size", 0, heap.getSize());
	}

	@Test
	public void deletingWorksWhenHeapIsFull() {
		AStarNode n = new AStarNode(new Coordinates(0, 0));
		heap.insert(n);
		heap.insert(new AStarNode(new Coordinates(0, 0)));
		heap.insert(new AStarNode(new Coordinates(0, 0)));
		heap.insert(new AStarNode(new Coordinates(0, 0)));
		heap.insert(new AStarNode(new Coordinates(0, 0)));
		heap.insert(new AStarNode(new Coordinates(0, 0)));
		heap.insert(new AStarNode(new Coordinates(0, 0)));
		heap.insert(new AStarNode(new Coordinates(0, 0)));
		heap.insert(new AStarNode(new Coordinates(0, 0)));
		heap.insert(new AStarNode(new Coordinates(0, 0)));

		assertEquals(n, heap.delete());
	}

}
