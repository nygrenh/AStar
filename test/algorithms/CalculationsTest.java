package algorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data_structures.Coordinates;

public class CalculationsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void distanceBetweenTest() {
		Coordinates c1 = new  Coordinates(3, 5);
		Coordinates c2 = new Coordinates(7, 1);
		assertEquals(8, Calculations.distanceBetween(c1, c2), 0.0001);
	}

}
