package main;

import java.awt.Graphics2D;

public class cutsceneManage {

	gamepanel gamepanel;
	Graphics2D graphics2d; 
	public int scenenumber;
	public int scenePhase;
	
	// SCENE
	public final int NA = 0;
	public final int startingScene = 1;
	
	public cutsceneManage(gamepanel gamepanel) {
		this.gamepanel = gamepanel; 
	}
	
	public void draw(Graphics2D graphics2d) {
		this.graphics2d = graphics2d; 
	}
}
