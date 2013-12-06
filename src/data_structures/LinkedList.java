package data_structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<T> implements List<T> {
	private int size;
	private LinkedListNode<T> head, tail;

	public LinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	public void addAtTheBeginning(T insertee) {
		LinkedListNode<T> inserteeListT = new LinkedListNode<T>(insertee);
		inserteeListT.setNext(head);
		head = inserteeListT;
		if (tail == null) {
			tail = head;
		}
		size++;
	}

	/**
	 * Returns the node at the specified index
	 * 
	 * @return T, if it exits, null otherwise
	 */
	@Override
	public T get(int index) {
		if (head == null || index < 0) {
			return null;
		}
		LinkedListNode<T> fastForwardT = head;
		int i = 0;
		while (i < index) {
			fastForwardT = fastForwardT.getNext();
			if (fastForwardT == null) {
				return null;
			}
			i++;
		}
		return fastForwardT.getNode();
	}

	public int getSize() {
		return this.size;
	}

	public T delete() {
		T returnee = get(0);
		if (head != null) {
			head = head.getNext();
		}
		if (size != 0)
			size--;
		return returnee;
	}

	@Override
	public boolean add(T insertee) {
		LinkedListNode<T> inserteeListT = new LinkedListNode<T>(insertee);
		if (head == null) {
			head = inserteeListT;
		} else {
			tail.setNext(inserteeListT);
		}
		size++;
		tail = inserteeListT;
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator<T>(head);
	}

	@Override
	public void add(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
}
