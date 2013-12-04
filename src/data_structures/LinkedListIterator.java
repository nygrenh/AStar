package data_structures;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

	private LinkedListNode<T> current;

	public LinkedListIterator(LinkedListNode<T> n) {
		this.current = n;
	}

	@Override
	public boolean hasNext() {
		return current.getNext() != null;
	}

	@Override
	public T next() {
		if(current == null){
			return null;
		}
		T help = current.getNode();
		current = current.getNext();
		return help;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
