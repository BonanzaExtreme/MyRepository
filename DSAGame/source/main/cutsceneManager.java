package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import character.entityImage;
import main.gamepanel;

public class cutsceneManager {
	gamepanel gamepanel;
	Graphics2D graphics2d;
	public int sceneNo; 
	public int scenePhase;
	float alpha = 0f;
	int y;
	
	//Scene number
	public final int NA = 0;
	public final int ending = 1;
	
	public cutsceneManager(gamepanel gamepanel) {
		this.gamepanel = gamepanel;
	}
	public void draw(Graphics2D graphics2d) {
		this.graphics2d = graphics2d;
		if (sceneNo == ending) {
			alpha += 0.005f;
		    if (alpha > 1f) {
		        alpha = 1f;
		    }
		    graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		    graphics2d.setColor(Color.black);
		    graphics2d.fillRect(0, 0, gamepanel.screenWidth, gamepanel.screenHeight);
		    graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		   if (alpha == 1f) {
			   gamepanel.gamestate = gamepanel.titlescreen;
			   gamepanel.playKanta(0);
		   }
		}
	}
	public void displayDialogue() {
	  
	    graphics2d.setColor(Color.white);
	    graphics2d.fillRect(50, 50, gamepanel.screenWidth - 100, gamepanel.screenHeight - 100);
	    graphics2d.setColor(Color.black);
	    graphics2d.drawString("Thank you for playing!.", 100, 100); 
	
	}
		
		
}
