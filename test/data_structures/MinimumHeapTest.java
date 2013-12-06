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
	public void heapGivesNodesInRightOrder() {
		AStarNode node1 = new AStarNode(new Node(new Coordinates(0, 0)));
		node1.setToEnd(4);
		AStarNode node2 = new AStarNode(new Node(new Coordinates(0, 1)));
		node2.setToEnd(55);
		AStarNode node3 = new AStarNode(new Node(new Coordinates(0, 2)));
		node3.setToEnd(1);
		AStarNode node4 = new AStarNode(new Node(new Coordinates(1, 0)));
		node4.setToEnd(4);
		AStarNode node5 = new AStarNode(new Node(new Coordinates(1, 1)));
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
		AStarNode n = new AStarNode(new Node(new Coordinates(0, 0)));
		heap.insert(n);
		heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));

		assertEquals(n, heap.delete());
	}
	
	@Test
	public void addingTooManyNodesDoesntCauseAnyProblems(){
		for (int i = 0; i < 20; i++) {
			heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		}
	}
	
	@Test
	public void insertionTimeTest(){
		heap = new MinimumHeap(100003);
		long before = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		}
		long after = System.currentTimeMillis();
		long result = after - before;
		heap = new MinimumHeap(1000003);
		long before2 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) { // ten times larger
			heap.insert(new AStarNode(new Node(new Coordinates(0, 0))));
		}
		long after2 = System.currentTimeMillis();
		long result2 = after2 - before2;
		assertTrue((result/result2<0.4));
	}

}
