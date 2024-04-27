package objectfolder;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.TOOL;
import main.gamepanel;

public class parentObject {
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int Worldx, Worldy;
	public Rectangle solidAreaRectangle = new Rectangle(0,0,48,48);
	public int solidAreaRectangleDefaultX = 0;
	public int solidAreaRectangleDefaultY = 0; 
	TOOL utilTool = new TOOL();
	
	public void draw(Graphics2D graphics2d, gamepanel gamepanel) {
		int screenX = Worldx - gamepanel.player.Worldx + gamepanel.player.screenX;
		int screenY = Worldy - gamepanel.player.Worldy + gamepanel.player.screenY;
		
		if(Worldx + gamepanel.tileSize > gamepanel.player.Worldx - gamepanel.player.screenX && 
		   Worldx - gamepanel.tileSize < gamepanel.player.Worldx + gamepanel.player.screenX &&
		   Worldy + gamepanel.tileSize > gamepanel.player.Worldy - gamepanel.player.screenY &&
		   Worldy - gamepanel.tileSize < gamepanel.player.Worldy + gamepanel.player.screenY) {
			 graphics2d.drawImage(image, screenX, screenY, gamepanel.tileSize, gamepanel.tileSize, null);
		}
	}
}
