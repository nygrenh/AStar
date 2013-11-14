package data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	private LinkedList linkedList;

	@Before
	public void setUp() throws Exception {
		linkedList = new LinkedList();
	}

	@Test
	public void anEmptyListIsEmpty() {
		assertNull(linkedList.get(0));
		assertEquals(0, linkedList.getSize());
	}

	@Test
	public void insertingWorks() {
		Node n = new Node();
		Node n2 = new Node();
		linkedList.insert(n);
		linkedList.insert(n2);
		assertEquals(n, linkedList.get(0));
		assertEquals(n2, linkedList.get(1));
		assertEquals(2, linkedList.getSize());
	}

	@Test
	public void gettingIllegalNodesIsNotPossible() {
		linkedList.insert(new Node());
		linkedList.insert(new Node());
		linkedList.insert(new Node());
		assertNull(linkedList.get(-1));
		assertNull(linkedList.get(3));
	}
}
