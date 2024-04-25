package character;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamepanel;
import main.keyhandler;

public class player extends entityImage {

	gamepanel gamepanel;
	keyhandler keyhandler; 
	
	public final int screenX;
	public final int screenY;
	
	public player(gamepanel gamepanel, keyhandler keyhandler) {
		
		this.gamepanel = gamepanel;
		this.keyhandler = keyhandler; 
		
		screenX = gamepanel.screenWidth/2 - (gamepanel.tileSize/2);
		screenY = gamepanel.screenHeight/2 - (gamepanel.tileSize/2);
		
		solidAreaRectangle = new Rectangle();
		solidAreaRectangle.x = 8;
		solidAreaRectangle.y = 16;
		solidAreaRectangle.width = 32;
		solidAreaRectangle.height = 32;
		defaultvalue();
		getPlayerImage();
	}
	
	public void defaultvalue() {
		
		Worldx = gamepanel.tileSize * 24;
		Worldy = gamepanel.tileSize * 14; 
		speed = 3; 
		directionString = "UP"; 
	
	}
	
	public void getPlayerImage() {
		try {
			UP1 = ImageIO.read(getClass().getResourceAsStream("/player/Character_UP1.png"));
			UP2 = ImageIO.read(getClass().getResourceAsStream("/player/Character_UP2.png"));
			DOWN1 = ImageIO.read(getClass().getResourceAsStream("/player/Character_down1.png"));
			DOWN2 = ImageIO.read(getClass().getResourceAsStream("/player/Character_down2.png"));
			RIGHT1 = ImageIO.read(getClass().getResourceAsStream("/player/Character_RIGHT1.png"));
			RIGHT2 = ImageIO.read(getClass().getResourceAsStream("/player/Character_RIGHT2.png"));
			LEFT1 = ImageIO.read(getClass().getResourceAsStream("/player/Character_LEFT1.png"));
			LEFT2 = ImageIO.read(getClass().getResourceAsStream("/player/Character_LEFT2.png"));
 			STATIC = ImageIO.read(getClass().getResourceAsStream("/player/Character.png"));
					
					
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	  } graphics2d.drawImage(image, screenX, screenY, gamepanel.tileSize, gamepanel.tileSize, null);
	}
	
}
	

