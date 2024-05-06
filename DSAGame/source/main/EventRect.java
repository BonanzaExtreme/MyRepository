package main;

import java.awt.Rectangle;

import javax.swing.text.GapContent;

import character.player;
import tiles.tilemanager;

public class EventRect {
	gamepanel gamepanel;
	eventRectangle RectangleArray[][][];
	
	int previousEventX, previousEventY;
	boolean canInteract = true;
	int tempmap, tempcol, temprow;

	
	public EventRect(gamepanel gamepanel) {
		this.gamepanel = gamepanel;
		
		RectangleArray = new eventRectangle[gamepanel.maxMap][gamepanel.maxWorldColumns][gamepanel.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		
		while (map < gamepanel.maxMap && col < gamepanel.maxWorldColumns && row < gamepanel.maxWorldRow) {
			RectangleArray[map][col][row] = new eventRectangle();
			RectangleArray[map][col][row].x = 23;
			RectangleArray[map][col][row].y = 23;
			RectangleArray[map][col][row].width = 2;
			RectangleArray[map][col][row].height = 2;
			RectangleArray[map][col][row].eventRectdefaultX = RectangleArray[map][col][row].x;
			RectangleArray[map][col][row].eventRectdefaultY = RectangleArray[map][col][row].y;
			
			col++;
			if (col == gamepanel.maxWorldColumns) {
				col = 0;
				row++;
				
				if (row == gamepanel.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		
		
	}
	
	public void checkevent() {
		int xDistance = Math.abs(gamepanel.player.Worldx - previousEventX);
		int yDistance = Math.abs(gamepanel.player.Worldy - previousEventY);
		int distance = Math.max(xDistance, yDistance);	

		
		if (distance > gamepanel.tileSize) {
			canInteract = true;
		}
		if (canInteract == true) {
			if(hit(0,7,49,"DOWN") == true) {
				teleport(1,14,12);
			} 
			if(hit(1,30,36,"DOWN") == true) {
				teleport(2,3,47);
			} 
			if(hit(2,16,1,"DOWN") == true) {
				teleport(3,25,15);
			} 
			
			
		}
		
	}
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false; 
		
		if (map == gamepanel.currentMap) {
			gamepanel.player.solidAreaRectangle.x = gamepanel.player.Worldx + gamepanel.player.solidAreaRectangle.x;
			gamepanel.player.solidAreaRectangle.y = gamepanel.player.Worldy + gamepanel.player.solidAreaRectangle.y;
			RectangleArray[map][col][row].x = col*gamepanel.tileSize + RectangleArray[map][col][row].x;
			RectangleArray[map][col][row].y = row*gamepanel.tileSize + RectangleArray[map][col][row].y;
			
			if(gamepanel.player.solidAreaRectangle.intersects(RectangleArray[map][col][row])) {
				if(gamepanel.player.directionString.contentEquals(reqDirection) || 
					reqDirection.contentEquals("DOWN")) {
					hit = true;	
					previousEventX = gamepanel.player.Worldx;
					previousEventY = gamepanel.player.Worldy;
					
				}
			}
			
			gamepanel.player.solidAreaRectangle.x = gamepanel.player.solidareadefaultx;
			gamepanel.player.solidAreaRectangle.y = gamepanel.player.solidareadefauly;
			RectangleArray[map][col][row].x = RectangleArray[map][col][row].eventRectdefaultX;
			RectangleArray[map][col][row].y = RectangleArray[map][col][row].eventRectdefaultY;	
		}
	
		return hit;
	}
	
	public void teleport(int map, int col, int row) {
		gamepanel.gamestate = gamepanel.transition;
		tempmap = map;
		tempcol = col;
		temprow = row;
		canInteract = false;
	}
	
}
	

