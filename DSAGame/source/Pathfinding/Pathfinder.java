package Pathfinding;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.plaf.basic.BasicPanelUI;

import character.entityImage;
import main.gamepanel;
import monster.monster1;
import tiles.tilemanager;

public class Pathfinder {
	gamepanel gamepanel;
	Node [][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node>pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;
	boolean goalreached = false;
	int step = 0;
	
	public Pathfinder(gamepanel gamepanel) {
		this.gamepanel = gamepanel;
		InstantiateNode();
		
	}
	
	public void InstantiateNode() {
		node = new Node[gamepanel.maxWorldColumns][gamepanel.maxWorldRow];
		
		int col = 0;
		int row = 0;
		
		while (col < gamepanel.maxWorldColumns && row < gamepanel.maxWorldRow) {
			node[col][row] = new Node(col, row);
			
			col++;
			
			if (col == gamepanel.maxWorldColumns) {
				col = 0;
				row++;
			}
			
		}
		
	}
	
	public void resetNode() {
		int col = 0;
		int row = 0;
		
		while (col < gamepanel.maxWorldColumns && row < gamepanel.maxWorldRow) {
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			
			col++;
			if (col == gamepanel.maxWorldColumns) {
				col = 0;
				row++;
			}
			
		}
		
		openList.clear();
		pathList.clear();
		goalreached = false;
		step = 0;
		
	}
	
	public void setNode(int startcol, int startrow, int goalcol, int goalrow, entityImage entity) {
		resetNode();
		
		startNode = node[startcol][startrow];
		currentNode = startNode;
		goalNode = node[goalcol][goalrow];
		openList.add(currentNode);
		
		int col = 0;
		int row = 0;
		
		while (col < gamepanel.maxWorldColumns && row < gamepanel.maxWorldRow) {
			
			//tiles check
			int tilenum = gamepanel.tilemanager.maptileno[gamepanel.currentMap][col][row];
			if (gamepanel.tilemanager.tile[tilenum].collision == true) {
				node[col][row].solid = true;
			}
			getCost(node[col][row]);
			
			col++;
			if (col == gamepanel.maxWorldColumns) {
				col = 0;
				row ++;
			}	
	
		}
	}
	
	public void getCost(Node node) {
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		
		 xDistance = Math.abs(node.col - goalNode.col);
		 yDistance = Math.abs(node.row - goalNode.row);
		 node.hcost = xDistance + yDistance;
		 
		 node.fcost = node.gCost + node.hcost;
	}
	
	public boolean search() {
		while (goalreached == false && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			
			currentNode.checked = true;
			openList.remove(currentNode);
			
			if (row - 1 >= 0) {
				openNode(node[col][row-1]);
			}
			if (row - 1 >= 0) {
				openNode(node[col-1][row]);
			}
			if (row + 1	 < gamepanel.maxWorldRow) {
				openNode(node[col][row+1]);
			}
			if (col + 1	 < gamepanel.maxWorldColumns) {
				openNode(node[col+1][row]);
			}
			
			int bestNodeIndex = 0;
			int bestNodefcost = 999;
			
			for (int i = 0; i < openList.size(); i++) {
				if (openList.get(i).fcost < bestNodefcost) {
					bestNodeIndex = i;
					bestNodefcost = openList.get(i).fcost;
				}
				else if (openList.get(i).fcost == bestNodefcost) {
					if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			if (openList.size() == 0) {
				break;
			}
			
			currentNode = openList.get(bestNodeIndex);
			if (currentNode == goalNode) {
				goalreached = true;
				trackPath();
			}
			
			step++;
			
		}
		return goalreached;
	}
	
	public void openNode(Node node) {
		if (node.open == false && node.checked == false && node.solid == false) {
			node.open = true;
			node.parentNode = currentNode;
			openList.add(node);
		}
	}
	
	public void trackPath() {
		Node currentNode = goalNode;
		
		while (currentNode != startNode) {
			
			pathList.add(0, currentNode);
			currentNode = currentNode.parentNode;
		}
	}	
}
