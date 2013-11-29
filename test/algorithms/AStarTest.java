package algorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data_structures.AStarNode;
import data_structures.Coordinates;
import data_structures.List;

public class AStarTest {
private AStar aStar;

	@Before
	public void setUp() throws Exception {
		aStar = new AStar();
	}

	@Test
	public void reconstructPathTest() {
		AStarNode node = new AStarNode(new Coordinates(0, 0));
		AStarNode node2 = new AStarNode(new Coordinates(0, 0));
		AStarNode node3 = new AStarNode(new Coordinates(0, 0));
		node3.setCameFrom(node2);
		node2.setCameFrom(node);
		List path = aStar.reconstructPath(node3);
		assertEquals(node, path.get(0));
		assertEquals(node2, path.get(1));
		assertEquals(node3, path.get(2));
		assertNull(path.get(3));
	}

}
