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
		Node node1 = new Node();
		node1.setToEnd(4);
		Node node2 = new Node();
		node2.setToEnd(55);
		Node node3 = new Node();
		node3.setToEnd(1);
		Node node4 = new Node();
		node4.setToEnd(4);
		Node node5 = new Node();
		node5.setToEnd(3);

		heap.insert(node1);
		heap.insert(node2);
		heap.insert(node3);
		heap.insert(node4);
		heap.insert(node5);

		assertEquals(node3, heap.delete());
		assertEquals(node5, heap.delete());
		assertEquals(4, heap.delete().getToEnd());
		assertEquals(4, heap.delete().getToEnd());
		assertEquals(node2, heap.delete());
	}

	@Test
	public void anEmptyHeapGivesNull() {
		assertNull(heap.delete());
		assertEquals("Trying to delete from empty heap reduces heap size", 0, heap.getSize());
	}

	@Test
	public void deletingWorksWhenHeapIsFull() {
		Node n = new Node();
		heap.insert(n);
		heap.insert(new Node());
		heap.insert(new Node());
		heap.insert(new Node());
		heap.insert(new Node());
		heap.insert(new Node());
		heap.insert(new Node());
		heap.insert(new Node());
		heap.insert(new Node());
		heap.insert(new Node());

		assertEquals(n, heap.delete());
	}

}
