package algorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data_structures.LinkedList;
import data_structures.Node;

public class AStarTest {
private AStar aStar;

	@Before
	public void setUp() throws Exception {
		aStar = new AStar();
	}

	@Test
	public void reconstructPathTest() {
		Node node = new Node(0);
		Node node2 = new Node(0);
		Node node3 = new Node(0);
		node3.setCameFrom(node2);
		node2.setCameFrom(node);
		LinkedList path = aStar.reconstructPath(node3);
		assertEquals(node, path.get(0));
		assertEquals(node2, path.get(1));
		assertEquals(node3, path.get(2));
		assertNull(path.get(3));
	}

}
