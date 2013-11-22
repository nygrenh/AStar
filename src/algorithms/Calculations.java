package algorithms;

import data_structures.Coordinates;
import data_structures.Node;

public class Calculations {

	public static int absoluteValue(int i) {
		if (i >= 0)
			return i;
		else
			return -i;
	}

	public static int distanceBetween(Coordinates c1, Coordinates c2) {
		return absoluteValue(c1.x - c2.x) + absoluteValue(c1.y - c2.y);
	}

	public static int distanceBetween(Node n1, Node n2) {
		return distanceBetween(n1.getCoordinates(), n2.getCoordinates());
	}

}
