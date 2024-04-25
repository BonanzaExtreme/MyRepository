package character;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class entityImage {
	
	public int Worldx, Worldy;
	public int speed;
	
	public BufferedImage STATIC, BACKIDLE, UP1, UP2, DOWN1, DOWN2, RIGHT1, RIGHT2, LEFT1, LEFT2; 
	public String directionString;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidAreaRectangle;
	public boolean CollisionISOn = false; 

}
