package data_structures;

public interface List {
	public void insert(Node n);
	public Node get(int index);
	public int getSize();
	public AStarNode delete();
}
