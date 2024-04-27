package character;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.TOOL;
import main.gamepanel;
import main.keyhandler;

public class player extends entityImage {

	keyhandler keyhandler; 
	
	public final int screenX;
	public final int screenY;
	int keyno = 0;
	
	public player(gamepanel gamepanel, keyhandler keyhandler) {
		
		super(gamepanel);
		
		this.keyhandler = keyhandler; 
		
		
		screenX = gamepanel.screenWidth/2 - (gamepanel.tileSize/2);
		screenY = gamepanel.screenHeight/2 - (gamepanel.tileSize/2);
		
		solidAreaRectangle = new Rectangle();
		solidAreaRectangle.x = 8;
		solidAreaRectangle.y = 16;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;
		solidAreaRectangle.width = 32;
		solidAreaRectangle.height = 32;
		defaultvalue();
		getPlayerImage();
	}
	
	public void defaultvalue() {
		
		Worldx = gamepanel.tileSize * 25;
		Worldy = gamepanel.tileSize * 15; 
		speed = 2; 
		directionString = "UP"; 
		
		//player life 
		maxLife = 6;
		life = maxLife;
	
	}
	
	public void getPlayerImage() {
		
		UP1 = setup("/player/MainChar_UP1");
		UP2 = setup("/player/MainChar_UP2");
		DOWN1 = setup("/player/MainChar_DOWN1");
		DOWN2 = setup("/player/MainChar_DOWN2");
		RIGHT1= setup("/player/MainChar_RIGHT1");
		RIGHT2 = setup("/player/MainChar_RIGHT2");
		LEFT1 = setup("/player/MainChar_LEFT1");
		LEFT2 = setup("/player/MainChar_LEFT2");
		STATIC = setup("/player/MainChar_Static");
	}
	

	public void update() {
		
		if (keyhandler.up == true || keyhandler.down == true || keyhandler.left == true || keyhandler.right == true)  {
			
			if (keyhandler.up == true) {
				directionString = "UP";
			} else if (keyhandler.down == true) {
				directionString = "DOWN";
			} else if (keyhandler.left == true) {
				directionString = "LEFT";
			} else if (keyhandler.right == true) {
				directionString = "RIGHT";

			}
			
			CollisionISOn = false;
			gamepanel.collisionCheck.collisiontile(this);
			
			int objectIndex = gamepanel.collisionCheck.checkObject(this, true);
			objectInteraction(objectIndex); 
			
			//collision for NPC
			int npcIndex = gamepanel.collisionCheck.entityCollision(this, gamepanel.NPC);
			npcInteraction(npcIndex);
			
			
			//Player movement collision
			if (CollisionISOn == false) {
				switch (directionString) {
				case "UP":
					Worldy -= speed;
					break;
				case "DOWN":
					Worldy += speed;
					break;	
				case "LEFT":
					Worldx -= speed;
					break;	
				case "RIGHT":
					Worldx += speed;
					break;
			}
		}
			
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1; 
				}
				spriteCounter = 0;
			} 
		}	
	}
		
	//NPC Interaction
	public void npcInteraction(int i) {
		if (i != 999) {
			 gamepanel.gamestate = gamepanel.dialogue;
			 gamepanel.NPC[i].speak();
			 gamepanel.state.image = gamepanel.NPC[i].STATIC;
		}
	}
	
	
	//Interaction object
	public void objectInteraction(int i) {
		
		if (i != 999) {
			String objectString = gamepanel.object[i].name; 
				
			if (objectString == "key") {
					keyno++;
					gamepanel.object[i] = null;
				}
			if (objectString == "Door") {
				if (keyno > 0) {
					gamepanel.object[i] = null;
				}
			 	}
			}
			
		}
	
	
	public void paintComponent(Graphics2D graphics2d) {
		
		BufferedImage image = null;
		if (keyhandler.up || keyhandler.down || keyhandler.left || keyhandler.right) {
			
			
		if (directionString == "UP") {
		
			if (spriteNum == 1) {
				image = UP1; 				
			} else if (spriteNum == 2) {
				image = UP2;
			}

		} else if (directionString == "DOWN") {
			if (spriteNum == 1) {
				image = DOWN1; 				
			} else if (spriteNum == 2) {
				image = DOWN2;
			}
		 } 
		  else if (directionString == "LEFT") {
			if (spriteNum == 1) {
				image = LEFT1; 				
			} else if (spriteNum == 2) {
				image = LEFT2;
			}
		 } 
		  else if (directionString == "RIGHT") {
				if (spriteNum == 1) {
					image = RIGHT1; 				
				} else if (spriteNum == 2) {
					image = RIGHT2;
				}
			 } 
		} else {
			image = STATIC;
	
	  } graphics2d.drawImage(image, screenX, screenY,  null);
	}
	
}
	

