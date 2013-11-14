package data_structures;

public class LinkedListNode {
	private Node node;
	private LinkedListNode next;
	
	public LinkedListNode(Node node) {
		this.node = node;
		this.next = null;
	}
	
	public LinkedListNode getNext() {
		return next;
	}
	
	public Node getNode() {
		return node;
	}
	
	public void setNext(LinkedListNode next) {
		this.next = next;
	}
}
