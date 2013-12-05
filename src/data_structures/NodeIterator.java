package data_structures;

import java.util.Iterator;

public class NodeIterator implements Iterator<Node> {
	
	private LinkedList<Node> list;
	private int current;
	
	public NodeIterator(LinkedList<Node> list) {
		this.list = list;
		this.current = -1;
	}

	@Override
	public boolean hasNext() {
		return list.getSize()>0;
	}

	@Override
	public Node next() {
		current++;
		return list.get(current);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("This operation is not supported.");
	}

}
