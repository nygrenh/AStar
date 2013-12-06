package data_structures;

import static org.junit.Assert.*;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class LinkedListIteratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void iteratorWorksCorrectly() {
		LinkedList<Integer> list = new LinkedList<>();
		int x = 5, y = 35, z = 1;
		list.add(x);
		list.add(y);
		list.add(z);
		Iterator<Integer> iterator = list.iterator();
		assertTrue(iterator.hasNext());
		assertSame(iterator.next(), x);
		assertSame(iterator.next(), y);
		assertSame(iterator.next(), z);
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void ifListIsEmptyNextReturnsNull() {
		LinkedList<Integer> list = new LinkedList<>();
		Iterator<Integer> iterator = list.iterator();
		assertNull(iterator.next());

	}

}
