package objectfolder;

import character.entityImage;
import main.gamepanel;

public class chest_design extends entityImage{
	public chest_design (gamepanel gamepanel) {
		super(gamepanel);
		
		
		type = type_design;
		name = "chest_design";
		STATIC = setup("/objects/chest_design", gamepanel.tileSize, gamepanel.tileSize);
		collision = true; 
	
	}
}
