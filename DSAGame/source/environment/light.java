package environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.gamepanel;

public class light {
	gamepanel gamepanel;
	BufferedImage darknessBufferedImage;
	
	public light(gamepanel gamepanel, int spotlight) {
		darknessBufferedImage = new BufferedImage(gamepanel.screenWidth,gamepanel.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2d = (Graphics2D)darknessBufferedImage.getGraphics();
		
		//SCREEN SIZED RECTANGLE FOR THE DARKNESS FILTER
		Area screenSpotlightArea = new Area(new Rectangle2D.Double(0,0,gamepanel.screenWidth,gamepanel.screenHeight));
		
		//CENTER OF THE SPOTLIGHT
		int centerx = gamepanel.player.screenX + (gamepanel.tileSize)/2;
		int centery = gamepanel.player.screenY + (gamepanel.tileSize)/2;
		//top left x/y of the spotlight
		double x = centerx - (spotlight/2);
		double y = centery - (spotlight/2);
		
		//CIRCLE NG ILAW
		Shape circleShape = new Ellipse2D.Double(x,y, spotlight, spotlight);
		
		//AREA NG CIRCLE NG ILAW
		Area circleLightArea = new Area(circleShape);
		
		screenSpotlightArea.subtract(circleLightArea);
		
		Color color[] = new Color[10];
		float fraction[] = new float [10];
		
		color[0] = new Color(0,0,0,0.0f);
		color[1] = new Color(0,0,0,0.2f);
		color[2] = new Color(0,0,0,0.3f);
		color[3] = new Color(0,0,0,0.4f);
		color[4] = new Color(0,0,0,0.5f);
		color[5] = new Color(0,0,0,0.6f);
		color[6] = new Color(0,0,0,0.7f);
		color[7] = new Color(0,0,0,0.8f);
		color[8] = new Color(0,0,0,0.9f);
		color[9] = new Color(0,0,0,1f);
		
		fraction[0] = 0.0f;
		fraction[1] = 0.2f;
		fraction[2] = 0.3f;
		fraction[3] = 0.4f;
		fraction[4] = 0.5f;
		fraction[5] = 0.6f;
		fraction[6] = 0.7f;
		fraction[7] = 0.8f;
		fraction[8] = 0.9f;
		fraction[9] = 1f;
		
		RadialGradientPaint gradient = new RadialGradientPaint(centerx, centery, (spotlight/2), fraction, color);
		
		graphics2d.setPaint(gradient);
		graphics2d.fill(circleLightArea);
		
		
	
		graphics2d.fill(screenSpotlightArea);
		graphics2d.dispose();
	}
	
	public void draw(Graphics2D graphics2d) {
		graphics2d.drawImage(darknessBufferedImage, 0, 0, null);
	}
	
	
	
}
