package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import javax.print.DocFlavor.INPUT_STREAM;



public class state {
	gamepanel gamepanel;
	Graphics2D graphics2d; 
	Font minecraft; 
	public int commandNO = 0;
	
	
	public state(gamepanel gamepanel) {
		this.gamepanel = gamepanel;
		try {
			InputStream iStream = getClass().getResourceAsStream("/font/Minecraft.ttf");
			minecraft = Font.createFont(Font.TRUETYPE_FONT, iStream);
		} catch (FontFormatException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void paintComponent(Graphics2D graphics2d) {
		this.graphics2d = graphics2d; 
		
		graphics2d.setFont(minecraft);
		graphics2d.setColor(Color.white);
		
		//Title
		
		if(gamepanel.gamestate == gamepanel.titlescreen) {
			drawTitle();
		}
		if (gamepanel.gamestate == gamepanel.pausescreen) {
			drawPause();
		}
	}
	
	public void drawPause() {
		
	}
	

	public void drawTitle() {
		graphics2d.setFont(graphics2d.getFont().deriveFont(Font.BOLD,63F));
		String text = "Adventurer's Maze";
		int x = XaxisCenter(text);
		int y = gamepanel.tileSize*4;
		
		graphics2d.setColor(Color.white);
		graphics2d.drawString(text, x, y);
		
		
		//MENU
		graphics2d.setFont(graphics2d.getFont().deriveFont(Font.BOLD,35f));
		text = "Start Game";
		x = XaxisCenter(text);
		y += gamepanel.tileSize*4;
		graphics2d.drawString(text, x, y);
		if(commandNO == 0){
			graphics2d.drawString("→", x-gamepanel.tileSize, y);
		}
		text = "Quit Game";
		x = XaxisCenter(text);
		y += gamepanel.tileSize;
		graphics2d.drawString(text, x, y);
		if(commandNO == 1){
			graphics2d.drawString("→", x-gamepanel.tileSize, y);
		}
	}
	
	public int XaxisCenter(String text) {
		
		int length = (int)graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
		int x = gamepanel.screenWidth/2 - length/2;
		return x;
	}

}
