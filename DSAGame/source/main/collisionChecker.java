package main;

import javax.swing.text.html.parser.Entity;

import character.entityImage;

public class collisionChecker {

	gamepanel gamepanel; 
	
	public collisionChecker(gamepanel gamepanel) {
	 		this.gamepanel = gamepanel;
	 }
	
	public void collisiontile(entityImage entity) {
		int leftworldx = entity.Worldx + entity.solidAreaRectangle.x;
		int rightworldx = entity.Worldx + entity.solidAreaRectangle.x + entity.solidAreaRectangle.width;
		int topworldy = entity.Worldy + entity.solidAreaRectangle.y;
		int bottomworldy = entity.Worldy + entity.solidAreaRectangle.y + entity.solidAreaRectangle.height;
		
		int entityleftcolumn = leftworldx/gamepanel.tileSize; 
		int entityrightcolumn = rightworldx/gamepanel.tileSize;
		int entitytoprow = topworldy/gamepanel.tileSize;
		int entitybottowrow = bottomworldy/gamepanel.tileSize;
	
		int tileNumber1, tileNumber2; 
		
		if(entity.directionString == "UP") {
			entitytoprow = (topworldy - entity.speed)/gamepanel.tileSize;
			tileNumber1 = gamepanel.tilemanager.maptileno[gamepanel.currentMap][entityleftcolumn][entitytoprow];
			tileNumber2 = gamepanel.tilemanager.maptileno[gamepanel.currentMap][entityrightcolumn][entitytoprow];
			if(gamepanel.tilemanager.tile[tileNumber1].collision == true || gamepanel.tilemanager.tile[tileNumber2].collision == true) {
				entity.CollisionISOn = true;
			}
		} else if (entity.directionString == "DOWN") {
			entitybottowrow = (bottomworldy + entity.speed)/gamepanel.tileSize;
			tileNumber1 = gamepanel.tilemanager.maptileno[gamepanel.currentMap][entityleftcolumn][entitybottowrow];
			tileNumber2 = gamepanel.tilemanager.maptileno[gamepanel.currentMap][entityrightcolumn][entitybottowrow];
			if(gamepanel.tilemanager.tile[tileNumber1].collision == true || gamepanel.tilemanager.tile[tileNumber2].collision == true) {
				entity.CollisionISOn = true;
			}
		} else if (entity.directionString == "LEFT") {
			entityleftcolumn = (leftworldx - entity.speed)/gamepanel.tileSize;
			tileNumber1 = gamepanel.tilemanager.maptileno[gamepanel.currentMap][entityleftcolumn][entitytoprow];
			tileNumber2 = gamepanel.tilemanager.maptileno[gamepanel.currentMap][entityleftcolumn][entitybottowrow];
			if(gamepanel.tilemanager.tile[tileNumber1].collision == true || gamepanel.tilemanager.tile[tileNumber2].collision == true) {
				entity.CollisionISOn = true;
			}
		} else if (entity.directionString == "RIGHT") {
			entityrightcolumn = (rightworldx + entity.speed)/gamepanel.tileSize;
			tileNumber1 = gamepanel.tilemanager.maptileno[gamepanel.currentMap][entityrightcolumn][entitytoprow];
			tileNumber2 = gamepanel.tilemanager.maptileno[gamepanel.currentMap][entityrightcolumn][entitybottowrow];
			if(gamepanel.tilemanager.tile[tileNumber1].collision == true || gamepanel.tilemanager.tile[tileNumber2].collision == true) {
				entity.CollisionISOn = true;
			}
		}
	
	}
	
	public int checkObject(entityImage entity, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gamepanel.object[1].length; i++) {
				if (gamepanel.object[gamepanel.currentMap][i] != null) {
					
					entity.solidAreaRectangle.x = entity.Worldx + entity.solidAreaRectangle.x; 
					entity.solidAreaRectangle.y = entity.Worldy + entity.solidAreaRectangle.y;
					
					gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle.x = gamepanel.object[gamepanel.currentMap][i].Worldx + gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle.x;
					gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle.y = gamepanel.object[gamepanel.currentMap][i].Worldy + gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle.y;
				
					switch(entity.directionString) {
					case "UP":
						entity.solidAreaRectangle.y -= entity.speed;
						if (entity.solidAreaRectangle.intersects(gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle)) {
							if (gamepanel.object[gamepanel.currentMap][i].collision == true) {
								entity.CollisionISOn = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
						
					case "DOWN":
						entity.solidAreaRectangle.y += entity.speed;
						if (entity.solidAreaRectangle.intersects(gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle)) {
							if (gamepanel.object[gamepanel.currentMap][i].collision == true) {
								entity.CollisionISOn = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
					case "LEFT":
						entity.solidAreaRectangle.x -= entity.speed;
						if (entity.solidAreaRectangle.intersects(gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle)) {
							if (gamepanel.object[gamepanel.currentMap][i].collision == true) {
								entity.CollisionISOn = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
					case "RIGHT":
						entity.solidAreaRectangle.x += entity.speed;
						if (entity.solidAreaRectangle.intersects(gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle)) {
							if (gamepanel.object[gamepanel.currentMap][i].collision == true) {
								entity.CollisionISOn = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
					}
				if(entity.solidAreaRectangle.intersects(gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle)) {
					if (gamepanel.object[gamepanel.currentMap][i].collision == true) {
						entity.CollisionISOn = true; 
						}
					}
					entity.solidAreaRectangle.x = entity.solidareadefaultx;
					entity.solidAreaRectangle.y = entity.solidareadefauly;
					gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle.x = gamepanel.object[gamepanel.currentMap][i].solidareadefaultx;
					gamepanel.object[gamepanel.currentMap][i].solidAreaRectangle.y = gamepanel.object[gamepanel.currentMap][i].solidareadefauly;
			}			
		}
		
		return index; 
	}
	public int entityCollision (entityImage entity, entityImage[][] target) {
	int index = 999;
		
		for(int i = 0; i < target[1].length; i++) {
				if (target[gamepanel.currentMap][i] != null) {
					
					entity.solidAreaRectangle.x = entity.Worldx + entity.solidAreaRectangle.x; 
					entity.solidAreaRectangle.y = entity.Worldy + entity.solidAreaRectangle.y;
					
					target[gamepanel.currentMap][i].solidAreaRectangle.x = target[gamepanel.currentMap][i].Worldx + target[gamepanel.currentMap][i].solidAreaRectangle.x;
					target[gamepanel.currentMap][i].solidAreaRectangle.y = target[gamepanel.currentMap][i].Worldy + target[gamepanel.currentMap][i].solidAreaRectangle.y;
				
					switch(entity.directionString) {
					case "UP":
						entity.solidAreaRectangle.y -= entity.speed;
						break;
					case "DOWN":
						entity.solidAreaRectangle.y += entity.speed;
						break;
					case "LEFT":
						entity.solidAreaRectangle.x -= entity.speed;
						break;
					case "RIGHT":
						entity.solidAreaRectangle.x += entity.speed;
						break;	
					}
				
				if(entity.solidAreaRectangle.intersects(target[gamepanel.currentMap][i].solidAreaRectangle)) {
					if (target[gamepanel.currentMap][i] != entity) {
					entity.CollisionISOn = true;
					index = i;
					}
				}
				entity.solidAreaRectangle.x = entity.solidareadefaultx;
				entity.solidAreaRectangle.y = entity.solidareadefauly;
				target[gamepanel.currentMap][i].solidAreaRectangle.x = target[gamepanel.currentMap][i].solidareadefaultx;
				target[gamepanel.currentMap][i].solidAreaRectangle.y = target[gamepanel.currentMap][i].solidareadefauly;
			}
			
		}
		return index; 
	}
		public boolean checkMainplayer(entityImage entity) {
			
			boolean contactWithPlayer = false; 
			
			entity.solidAreaRectangle.x = entity.Worldx + entity.solidAreaRectangle.x; 
			entity.solidAreaRectangle.y = entity.Worldy + entity.solidAreaRectangle.y;
			
			gamepanel.player.solidAreaRectangle.x = gamepanel.player.Worldx + gamepanel.player.solidAreaRectangle.x;
			gamepanel.player.solidAreaRectangle.y = gamepanel.player.Worldy + gamepanel.player.solidAreaRectangle.y;
		
			switch(entity.directionString) {
			case "UP":
				entity.solidAreaRectangle.y -= entity.speed;
				if(entity.solidAreaRectangle.intersects(gamepanel.player.solidAreaRectangle)) {
						entity.CollisionISOn = true; 
						contactWithPlayer = true;
					}
				break;
			case "DOWN":
				entity.solidAreaRectangle.y += entity.speed;
				if(entity.solidAreaRectangle.intersects(gamepanel.player.solidAreaRectangle)) {
						entity.CollisionISOn = true; 
						contactWithPlayer = true;
				}
				break;
			case "LEFT":
				entity.solidAreaRectangle.x -= entity.speed;
				if(entity.solidAreaRectangle.intersects(gamepanel.player.solidAreaRectangle)) {
						entity.CollisionISOn = true; 
						contactWithPlayer = true;

				}
				break;
			case "RIGHT":
				entity.solidAreaRectangle.x += entity.speed;
				if(entity.solidAreaRectangle.intersects(gamepanel.player.solidAreaRectangle)) {
						entity.CollisionISOn = true; 
						contactWithPlayer = true;
				}
				break;
			}
			entity.solidAreaRectangle.x = entity.solidareadefaultx;
			entity.solidAreaRectangle.y = entity.solidareadefauly;
			gamepanel.player.solidAreaRectangle.x =gamepanel.player.solidareadefaultx;
			gamepanel.player.solidAreaRectangle.y = gamepanel.player.solidareadefauly;
			
			return contactWithPlayer;
		}
}
