package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



import character.entityImage;
import objectfolder.Heart;




public class state {
	gamepanel gamepanel;
	Graphics2D graphics2d; 
	BufferedImage heart_full, heart_empty, heart_half;
	public BufferedImage image;
	Font minecraft; 
	public int commandNO = 0;
	public String currentDialogueString = " ";
	
	
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
		
		 entityImage heart = new Heart(gamepanel);
		 heart_full = heart.image;
		 heart_half = heart.image2;
		 heart_empty = heart.image3;
		
	}
	
	
	public void paintComponent(Graphics2D graphics2d) {
		this.graphics2d = graphics2d; 
		
		graphics2d.setFont(minecraft);
		graphics2d.setColor(Color.white);
		
		//Title
		if(gamepanel.gamestate == gamepanel.titlescreen) {
			drawTitle();
		}
		//Play
		if(gamepanel.gamestate == gamepanel.startstate) {
			drawPlayerHeart();
		}
			//Pause
		if (gamepanel.gamestate == gamepanel.pausescreen) {
			drawPause();
		}
		// Dialogue state 
		if (gamepanel.gamestate == gamepanel.dialogue) {
			drawDialogueBOX();
			drawPlayerHeart();
		}
		
		//Game over state
		if (gamepanel.gamestate == gamepanel.gameover) {
			drawGameoverScreen();
		}
	}
	
	public void drawPause() {
		
	}
	
	public void drawPlayerHeart() {
		int x = gamepanel.tileSize/2;
		int y = gamepanel.tileSize/2;
		int i = 0;
		
		while(i < gamepanel.player.life/2) {
			graphics2d.drawImage(heart_empty, x, y, null);
			i++;
			x += gamepanel.tileSize;
		}
		
			x = gamepanel.tileSize/2;
			y = gamepanel.tileSize/2;
			i = 0;
			
			while(i < gamepanel.player.life) {
				graphics2d.drawImage(heart_half, x, y, null);
				i++;
				if (i < gamepanel.player.life) {
					graphics2d.drawImage(heart_full, x,y, null);
				}
				i++;
				x +=gamepanel.tileSize;
			}
	}
	public void drawDialogueBOX() {
		//WINDOW
		int x = gamepanel.tileSize*2;
		int y = gamepanel.tileSize*10;
		int width = gamepanel.screenWidth - (gamepanel.tileSize*4); 
		int height = gamepanel.screenHeight/4;

		drawsubWindow(x, y, width, height);
		
		graphics2d.setFont(graphics2d.getFont().deriveFont(Font.BOLD,20f));
		x += gamepanel.tileSize;
		y += gamepanel.tileSize;

		
		
		if (image != null) {
			  int imageX = x - (gamepanel.tileSize/4);
			  int imageY = y - (gamepanel.tileSize/2);
			  int imageWidth = gamepanel.tileSize * 2; 
			  int imageHeight = gamepanel.tileSize * 2; 
			  graphics2d.drawImage(image, imageX, imageY, imageWidth, imageHeight, null);
			  x += imageWidth + (gamepanel.tileSize/2);
	    }
		
		for(String lineString : currentDialogueString.split("\n")) {
		graphics2d.drawString(lineString, x, y);
			y += 40; }
	

	}
	
	public void drawsubWindow(int x, int y, int width, int height) {
		
		Color color = new Color(0, 0, 0, 200);
		graphics2d.setColor(color);
		graphics2d.fillRoundRect(x, y, width, height, 25, 25);
		
		color = new Color(255, 255, 255);
		graphics2d.setColor(color);
		graphics2d.setStroke(new BasicStroke(3));
		graphics2d.drawRoundRect(x+3, y+3, width-10, height-3, 10, 10);
	}
	
	public void drawGameoverScreen() {
		graphics2d.setColor(new Color(0,0,0,150));
		graphics2d.fillRect(0, 0, gamepanel.screenWidth, gamepanel.screenHeight);
		
		int x;
		int y;
		String text;
		graphics2d.setFont(graphics2d.getFont().deriveFont(Font.BOLD, 110f));
		
		text = "GAME OVER!";
		
		//shadow
		graphics2d.setColor(Color.black);
		x = XaxisCenter(text);
		y = gamepanel.tileSize*4;
		
		//MAIN TEXT
		graphics2d.drawString(text, x, y);
		graphics2d.setColor(Color.white);
		graphics2d.drawString(text, x-5, y-5);
		
		//RETRY
		graphics2d.setFont(graphics2d.getFont().deriveFont(60f));
		text = "RETRY";
		x = XaxisCenter(text);
		y += gamepanel.tileSize*5;
		graphics2d.drawString(text, x, y);
		if (commandNO == 0) {
			graphics2d.drawString(">", x-40, y);
		}
		
		text = "QUIT";
		x = XaxisCenter(text);
		y += 60;
		graphics2d.drawString(text, x, y);
		if (commandNO == 1) {
			graphics2d.drawString(">", x-40, y);
		}
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
