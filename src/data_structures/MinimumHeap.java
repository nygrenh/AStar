package data_structures;

/**
 * Binary minimum heap.
 */
public class MinimumHeap {
	private Node[] array;
	private Scorer scorer;
	private int heapSize;

	public MinimumHeap(int size, Scorer scorer) {
		this.scorer = scorer;
		array = new Node[size + 1];
		heapSize = 0;
	}

	private int parent(int i) {
		return i / 2;
	}

	private int left(int i) {
		return 2 * i;
	}

	private int right(int i) {
		return 2 * i + 1;
	}

	/**
	 * Moves node at index i downwards as long as the heap property is violated
	 */
	private void heapify(int i) {
		int left = left(i);
		int right = right(i);
		double leftScore = getScore(left);
		double rightScore = getScore(right);

		if (right <= heapSize && rightScore <= leftScore && rightScore < getScore(i)) {
			swap(right, i);
			heapify(right);
			return;
		}

		if (left <= heapSize && leftScore < rightScore && leftScore < getScore(i)) {
			swap(left, i);
			heapify(left);
		}
	}

	private double getScore(int left) {
		try {
			return scorer.getScore(array[left]);
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * Deletes the root
	 * 
	 * @return the deleted node
	 */
	public Node delete() {
		if (heapSize == 0) {
			return null;
		}
		Node min = array[1];
		array[1] = array[heapSize];
		heapSize--;
		heapify(1);
		updateHeapIndex(-1, min);
		return min;
	}

	public void insert(Node insertee) {
		if (heapSize == array.length - 1) {
			System.err.println("Warning: Heap dropped a node on insert. Consider increasing heap size.");
			return;
		}
		heapSize++;
		array[heapSize] = insertee;
		updateHeapIndex(heapSize, insertee);
		travelUpwards(heapSize);
	}

	/**
	 * Moves node at index i upwards as long as the heap property is violated
	 */
	public void travelUpwards(int i) {
		if (i == 1) {
			return;
		}
		int parent = parent(i);
		if (getScore(parent) > getScore(i)) {
			swap(parent, i);
			travelUpwards(parent);
		}
	}

	private void swap(int i, int j) {
		if(i<1 || j < 1){
			return;
		}
		Node help = array[i];
		array[i] = array[j];
		updateHeapIndex(i, array[i]);
		array[j] = help;
		updateHeapIndex(j, array[j]);
	}

	private void updateHeapIndex(int i, Node n) {
		//array[i].heapindex = i;
	}

	public int getSize() {
		return heapSize;
	}

}
