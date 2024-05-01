package objectfolder;

import character.entityImage;
import main.gamepanel;

public class doorside extends entityImage {

	public doorside (gamepanel gamepanel) {
		super(gamepanel);
		
		name = "DoorSide";
		UP1 = setup("/objects/doorsideway", gamepanel.tileSize, gamepanel.tileSize);
		collision = true; 
		
		solidAreaRectangle.x = 0;
		solidAreaRectangle.y = 16;
		solidAreaRectangle.width = 32;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;
	
	}
}
