package data_structures;

public class LinkedList {
	private int size;
	private LinkedListNode head, tail;

	public LinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	public void insert(Node insertee) {
		LinkedListNode inserteeListNode = new LinkedListNode(insertee);
		if (head == null) {
			head = inserteeListNode;
		} else {
			tail.setNext(inserteeListNode);
		}
		size++;
		tail = inserteeListNode;
	}

	/**
	 * Returns the node at the specified index
	 * @return Node, if it exits, null otherwise
	 */
	public Node get(int index) {
		if (head == null || index < 0) {
			return null;
		}
		LinkedListNode fastForwardNode = head;
		int i = 0;
		while (i < index) {
			fastForwardNode = fastForwardNode.getNext();
			if (fastForwardNode == null) {
				return null;
			}
			i++;
		}
		return fastForwardNode.getNode();
	}

	public int getSize() {
		return this.size;
	}
}
