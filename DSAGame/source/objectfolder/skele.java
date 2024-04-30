package objectfolder;

import character.entityImage;
import main.gamepanel;

public class skele extends entityImage {

	public skele (gamepanel gamepanel) {
		super(gamepanel);
		
		type = type_design;
		name = "Skeleton";
		STATIC = setup("/objects/designskele", gamepanel.tileSize, gamepanel.tileSize);
		collision = true; 
		
		solidAreaRectangle.x = 0;
		solidAreaRectangle.y = 16;
		solidAreaRectangle.width = 32;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;
	
	}
}
