package data_structures;

public class LinkedListNode<T> {
	private T node;
	private LinkedListNode<T> next;
	
	public LinkedListNode(T node) {
		this.node = node;
		this.next = null;
	}
	
	public LinkedListNode<T> getNext() {
		return next;
	}
	
	public T getNode() {
		return node;
	}
	
	public void setNext(LinkedListNode<T> next) {
		this.next = next;
	}
}
