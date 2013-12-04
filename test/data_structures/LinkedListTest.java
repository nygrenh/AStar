package data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	private LinkedList<Node> linkedList;

	@Before
	public void setUp() throws Exception {
		linkedList = new LinkedList<Node>();
	}

	@Test
	public void anEmptyListIsEmpty() {
		assertNull(linkedList.get(0));
		assertEquals(0, linkedList.getSize());
	}

	@Test
	public void addingWorks() {
		Node n = new Node(new Coordinates(0, 0));
		Node n2 = new Node(new Coordinates(0, 0));
		linkedList.add(n);
		linkedList.add(n2);
		assertEquals(n, linkedList.get(0));
		assertEquals(n2, linkedList.get(1));
		assertEquals(2, linkedList.getSize());
	}

	@Test
	public void gettingIllegalNodesIsNotPossible() {
		linkedList.add(new Node(new Coordinates(0, 0)));
		linkedList.add(new Node(new Coordinates(0, 0)));
		linkedList.add(new Node(new Coordinates(0, 0)));
		assertNull(linkedList.get(-1));
		assertNull(linkedList.get(3));
	}
	
	@Test
	public void addingAtTheBeginningWorks() {
		Node n = new Node(new Coordinates(0, 0));
		Node n2 = new Node(new Coordinates(0, 0));
		linkedList.addAtTheBeginning(n2);
		linkedList.addAtTheBeginning(n);
		assertEquals(n, linkedList.get(0));
		assertEquals(n2, linkedList.get(1));
		assertEquals(2, linkedList.getSize());
	}
	
	@Test
	public void mixedInsertWorks() {
		Node n = new Node(new Coordinates(0, 0));
		Node n2 = new Node(new Coordinates(0, 0));
		linkedList.addAtTheBeginning(n2);
		linkedList.add(n);
		assertEquals(n2, linkedList.get(0));
		assertEquals(n, linkedList.get(1));
		assertEquals(2, linkedList.getSize());
	}
}
