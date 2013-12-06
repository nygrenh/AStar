package algorithms;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import data_structures.Coordinates;
import data_structures.Node;
import data_structures.NodeType;

public class AStarTest {
private AStar aStar;

	@Before
	public void setUp() throws Exception {
		aStar = new AStar();
	}
	
	private Node[][] nodeMap(int width, int height) {
		Node map[][] = new Node[height][width];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = new Node(new Coordinates(i, j));
			}
		}
		return map;
	}
	
	@Test
	public void alogrithmDoesntFindImpossibleRoutes(){
		Node[][] map = nodeMap(5, 2);
		map[0][3].setType(NodeType.wall);
		map[1][3].setType(NodeType.wall);
		assertNull(aStar.findPath(map[0][0], map[1][4], map));
	}

	@Test
	public void alogrithmAvoidsCostlyNodes(){
		Node[][] map = nodeMap(5, 2);
		map[0][2].setType(NodeType.glue);
		List<Node> path = aStar.findPath(map[0][0], map[0][4], map);
		for(Node n : path){
			int x = n.getCoordinates().x;
			int y = n.getCoordinates().y;
			if(x == 0 && y == 2){
				fail("The algorithm chose a suboptimal path");
			}
		}
	}
	
	@Test
	public void alogrithmDoesntFindAPathIfStartingPointIsInsideWall(){
		Node[][] map = nodeMap(5, 2);
		map[0][0].setType(NodeType.wall);
		assertNull(aStar.findPath(map[0][0], map[1][4], map));
	}
	
	@Test
	public void ifStartAndEndNodesAreTheSamePathContainsOneNode(){
		Node[][] map = nodeMap(5, 2);
		List<Node> path = aStar.findPath(map[0][0], map[0][0], map);
		assertEquals(1, path.size());
	}
	
	@Test
	public void getHelpNodeReturnsNullIfHelpMapHasNotBeenInitialized(){
		assertNull(aStar.getHelpNode(new Node(new Coordinates(0, 0))));
	}
	
	@Test
	public void pathForNodesNextToEachOtherIsCorrect(){
		Node[][] map = nodeMap(1, 2);
		List<Node> path = aStar.findPath(map[1][0], map[0][0], map);
		assertSame(map[1][0], path.get(0));
		assertSame(map[0][0], path.get(1));
		assertEquals(2, path.size());
		path = aStar.findPath(map[0][0], map[1][0], map);
		assertEquals(2, path.size());
	}

}
