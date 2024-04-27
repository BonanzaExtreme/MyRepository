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
			tileNumber1 = gamepanel.tilemanager.maptileno[entityleftcolumn][entitytoprow];
			tileNumber2 = gamepanel.tilemanager.maptileno[entityrightcolumn][entitytoprow];
			if(gamepanel.tilemanager.tile[tileNumber1].collision == true || gamepanel.tilemanager.tile[tileNumber2].collision == true) {
				entity.CollisionISOn = true;
			}
		} else if (entity.directionString == "DOWN") {
			entitybottowrow = (bottomworldy - entity.speed)/gamepanel.tileSize;
			tileNumber1 = gamepanel.tilemanager.maptileno[entityleftcolumn][entitybottowrow];
			tileNumber2 = gamepanel.tilemanager.maptileno[entityrightcolumn][entitybottowrow];
			if(gamepanel.tilemanager.tile[tileNumber1].collision == true || gamepanel.tilemanager.tile[tileNumber2].collision == true) {
				entity.CollisionISOn = true;
			}
		} else if (entity.directionString == "LEFT") {
			entityleftcolumn = (leftworldx - entity.speed)/gamepanel.tileSize;
			tileNumber1 = gamepanel.tilemanager.maptileno[entityleftcolumn][entitytoprow];
			tileNumber2 = gamepanel.tilemanager.maptileno[entityleftcolumn][entitybottowrow];
			if(gamepanel.tilemanager.tile[tileNumber1].collision == true || gamepanel.tilemanager.tile[tileNumber2].collision == true) {
				entity.CollisionISOn = true;
			}
		} else if (entity.directionString == "RIGHT") {
			entityrightcolumn = (rightworldx + entity.speed)/gamepanel.tileSize;
			tileNumber1 = gamepanel.tilemanager.maptileno[entityrightcolumn][entitytoprow];
			tileNumber2 = gamepanel.tilemanager.maptileno[entityrightcolumn][entitybottowrow];
			if(gamepanel.tilemanager.tile[tileNumber1].collision == true || gamepanel.tilemanager.tile[tileNumber2].collision == true) {
				entity.CollisionISOn = true;
			}
		}
	
	}
	
	public int checkObject(entityImage entity, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gamepanel.object.length; i++) {
				if (gamepanel.object[i] != null) {
					
					entity.solidAreaRectangle.x = entity.Worldx + entity.solidAreaRectangle.x; 
					entity.solidAreaRectangle.y = entity.Worldy + entity.solidAreaRectangle.y;
					
					gamepanel.object[i].solidAreaRectangle.x = gamepanel.object[i].Worldx + gamepanel.object[i].solidAreaRectangle.x;
					gamepanel.object[i].solidAreaRectangle.y = gamepanel.object[i].Worldy + gamepanel.object[i].solidAreaRectangle.y;
				
					switch(entity.directionString) {
					case "UP":
						entity.solidAreaRectangle.y -= entity.speed;
						if(entity.solidAreaRectangle.intersects(gamepanel.object[i].solidAreaRectangle)) {
							if (gamepanel.object[i].collision == true) {
								entity.CollisionISOn = true; 
							}
							if (player == true) {
									index = i;
							}
						}
						break;
					case "DOWN":
						entity.solidAreaRectangle.y += entity.speed;
						if(entity.solidAreaRectangle.intersects(gamepanel.object[i].solidAreaRectangle)) {
							if (gamepanel.object[i].collision == true) {
								entity.CollisionISOn = true; 
							}
							if (player == true) {
									index = i;
							}
						}
						break;
					case "LEFT":
						entity.solidAreaRectangle.x -= entity.speed;
						if(entity.solidAreaRectangle.intersects(gamepanel.object[i].solidAreaRectangle)) {
							if (gamepanel.object[i].collision == true) {
								entity.CollisionISOn = true; 
							}
							if (player == true) {
									index = i;
							}
						}
						break;
					case "RIGHT":
						entity.solidAreaRectangle.x += entity.speed;
						if(entity.solidAreaRectangle.intersects(gamepanel.object[i].solidAreaRectangle)) {
							if (gamepanel.object[i].collision == true) {
								entity.CollisionISOn = true; 
							}
							if (player == true) {
									index = i;
							}
						}
						break;
					}
					entity.solidAreaRectangle.x = entity.solidareadefaultx;
					entity.solidAreaRectangle.y = entity.solidareadefauly;
					gamepanel.object[i].solidAreaRectangle.x = gamepanel.object[i].solidAreaRectangleDefaultX;
					gamepanel.object[i].solidAreaRectangle.y = gamepanel.object[i].solidAreaRectangleDefaultY;
				}
		}
		
		return index; 
	}
	public int entityCollision (entityImage entity, entityImage[] target) {
	int index = 999;
		
		for(int i = 0; i < target.length; i++) {
				if (target[i] != null) {
					
					entity.solidAreaRectangle.x = entity.Worldx + entity.solidAreaRectangle.x; 
					entity.solidAreaRectangle.y = entity.Worldy + entity.solidAreaRectangle.y;
					
					target[i].solidAreaRectangle.x = target[i].Worldx + target[i].solidAreaRectangle.x;
					target[i].solidAreaRectangle.y = target[i].Worldy + target[i].solidAreaRectangle.y;
				
					switch(entity.directionString) {
					case "UP":
						entity.solidAreaRectangle.y -= entity.speed;
						if(entity.solidAreaRectangle.intersects(target[i].solidAreaRectangle)) {
								entity.CollisionISOn = true; 
								index = i;
						}
						break;
					case "DOWN":
						entity.solidAreaRectangle.y += entity.speed;
						if(entity.solidAreaRectangle.intersects(target[i].solidAreaRectangle)) {
								entity.CollisionISOn = true; 
								index = i;
						}
						break;
					case "LEFT":
						entity.solidAreaRectangle.x -= entity.speed;
						if(entity.solidAreaRectangle.intersects(target[i].solidAreaRectangle)) {						
								entity.CollisionISOn = true; 
								index = i;
						}
						break;
					case "RIGHT":
						entity.solidAreaRectangle.x += entity.speed;
						if(entity.solidAreaRectangle.intersects(target[i].solidAreaRectangle)) {
								entity.CollisionISOn = true; 
								index = i;
						}
						break;
					}
					entity.solidAreaRectangle.x = entity.solidareadefaultx;
					entity.solidAreaRectangle.y = entity.solidareadefauly;
					target[i].solidAreaRectangle.x = target[i].solidareadefaultx;
					target[i].solidAreaRectangle.y = target[i].solidareadefauly;
				}
		}
		
		return index; 
		
	}
	
		public void checkMainplayer(entityImage entity) {
			entity.solidAreaRectangle.x = entity.Worldx + entity.solidAreaRectangle.x; 
			entity.solidAreaRectangle.y = entity.Worldy + entity.solidAreaRectangle.y;
			
			gamepanel.player.solidAreaRectangle.x = gamepanel.player.Worldx + gamepanel.player.solidAreaRectangle.x;
			gamepanel.player.solidAreaRectangle.y = gamepanel.player.Worldy + gamepanel.player.solidAreaRectangle.y;
		
			switch(entity.directionString) {
			case "UP":
				entity.solidAreaRectangle.y -= entity.speed;
				if(entity.solidAreaRectangle.intersects(gamepanel.player.solidAreaRectangle)) {
						entity.CollisionISOn = true; 
					}
				break;
			case "DOWN":
				entity.solidAreaRectangle.y += entity.speed;
				if(entity.solidAreaRectangle.intersects(gamepanel.player.solidAreaRectangle)) {
						entity.CollisionISOn = true; 
				}
				break;
			case "LEFT":
				entity.solidAreaRectangle.x -= entity.speed;
				if(entity.solidAreaRectangle.intersects(gamepanel.player.solidAreaRectangle)) {
						entity.CollisionISOn = true; 

				}
				break;
			case "RIGHT":
				entity.solidAreaRectangle.x += entity.speed;
				if(entity.solidAreaRectangle.intersects(gamepanel.player.solidAreaRectangle)) {
						entity.CollisionISOn = true; 
				}
				break;
			}
			entity.solidAreaRectangle.x = entity.solidareadefaultx;
			entity.solidAreaRectangle.y = entity.solidareadefauly;
			gamepanel.player.solidAreaRectangle.x =gamepanel.player.solidareadefaultx;
			gamepanel.player.solidAreaRectangle.y = gamepanel.player.solidareadefauly;
		}
}
