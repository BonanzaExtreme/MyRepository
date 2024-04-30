package Pathfinding;

public class Node {
	Node parentNode;
	public int col;
	public int row;
	int gCost;
	int hcost;
	int fcost;
	boolean solid;
	boolean open;
	boolean checked;
	
	public Node(int col, int row) {
		this.col = col;
		this.row = row;
		
	}
}
