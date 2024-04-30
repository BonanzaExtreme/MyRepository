package objectfolder;
import character.entityImage;
import main.gamepanel;

public class chest extends entityImage {
	
	gamepanel gamepanel;
	entityImage object;
	boolean open = false;
	
	public chest (gamepanel gamepanel) {
		super(gamepanel);
		
		type = type_door;
		name = "chest";
		image = setup("/objects/chest", gamepanel.tileSize, gamepanel.tileSize);
		image2 = setup("/objects/chestopen", gamepanel.tileSize, gamepanel.tileSize);
		STATIC = image;
		
		collision = true; 
	
	}
}