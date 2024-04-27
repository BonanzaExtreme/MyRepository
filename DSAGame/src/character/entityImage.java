package character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.text.html.parser.Entity;

import main.TOOL;
import main.gamepanel;
import main.keyhandler;


public class entityImage  {
	
	gamepanel gamepanel;
	keyhandler keyhandler; 
	
	public int Worldx, Worldy;
	public int speed;
	
	
	
	public BufferedImage STATIC, BACKIDLE, UP1, UP2, DOWN1, DOWN2, RIGHT1, RIGHT2, LEFT1, LEFT2; 
	public String directionString;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public String name;
	public int solidareadefaultx, solidareadefauly; 
	public Rectangle solidAreaRectangle = new Rectangle(0, 0, 48,48);;
	public boolean CollisionISOn = false; 
	public int actionCounter = 0;
	
	//Dialogue 
	String dialogues[] = new String[50];
	int dialogueindex = 0;
	
	//Character life
	
	public int maxLife;
	public int life;
	
	public entityImage (gamepanel gamepanel) {
		
		this.gamepanel = gamepanel;
	
	}
	
	
	public void setAction() {}
	public void speak() {}
	public void update() {
		
			setAction();
			CollisionISOn = false; 
			gamepanel.collisionCheck.collisiontile(this);
			gamepanel.collisionCheck.checkObject(this, false);
			gamepanel.collisionCheck.checkMainplayer(this);
			
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


	//DRAW ENTITY
	public void draw(Graphics2D graphics2d) {
		int screenX = Worldx - gamepanel.player.Worldx + gamepanel.player.screenX;
		int screenY = Worldy - gamepanel.player.Worldy + gamepanel.player.screenY;
		
		if(Worldx + gamepanel.tileSize > gamepanel.player.Worldx - gamepanel.player.screenX && 
		   Worldx - gamepanel.tileSize < gamepanel.player.Worldx + gamepanel.player.screenX &&
		   Worldy + gamepanel.tileSize > gamepanel.player.Worldy - gamepanel.player.screenY &&
		   Worldy - gamepanel.tileSize < gamepanel.player.Worldy + gamepanel.player.screenY) {
			 
			BufferedImage image = null;
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
			 else {
				image = STATIC;
			 } graphics2d.drawImage(image, screenX, screenY, gamepanel.tileSize, gamepanel.tileSize, null);
			}
		}
	
	
	
	public BufferedImage setup(String imagename) {
		TOOL utilTool = new TOOL();
		BufferedImage scaledImage = null;
		
		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream(imagename + ".png"));
			scaledImage = utilTool.scaleImage(scaledImage, gamepanel.tileSize, gamepanel.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scaledImage;
	}
}
