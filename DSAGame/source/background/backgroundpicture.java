package background;

import character.entityImage;
import main.gamepanel;


public class backgroundpicture extends entityImage{
	gamepanel gamepanel; 
	
	public backgroundpicture(gamepanel gamepanel) {
		super(gamepanel);
		
		image =  setup("/objects/key", gamepanel.tileSize, gamepanel.tileSize);
	}
}
