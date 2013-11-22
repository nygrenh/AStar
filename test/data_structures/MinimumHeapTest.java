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
		Node node1 = new Node(4);
		Node node2 = new Node(55);
		Node node3 = new Node(1);
		Node node4 = new Node(4);
		Node node5 = new Node(3);
		
		heap.insert(node1);
		heap.insert(node2);
		heap.insert(node3);
		heap.insert(node4);
		heap.insert(node5);

		assertEquals(node3, heap.delete());
		assertEquals(node5, heap.delete());
		assertEquals(4, heap.delete().getToStart());
		assertEquals(4, heap.delete().getToStart());
		assertEquals(node2, heap.delete());
	}
	
	@Test
	public void anEmptyHeapGivesNull(){
		assertNull(heap.delete());
		assertEquals("Trying to delete from empty heap reduces heap size", 0, heap.getSize());
	}
	
	@Test
	public void deletingWorksWhenHeapIsFull(){
		heap.insert(new Node(14));
		heap.insert(new Node(55));
		heap.insert(new Node(21));
		heap.insert(new Node(54));
		heap.insert(new Node(13));
		heap.insert(new Node(43));
		heap.insert(new Node(55));
		heap.insert(new Node(3));
		heap.insert(new Node(45));
		heap.insert(new Node(553));
		
		assertEquals(3, heap.delete().getToStart());
	}

}
