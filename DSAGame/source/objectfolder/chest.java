package objectfolder;
import character.entityImage;
import main.gamepanel;

public class chest extends entityImage {
	
	public chest (gamepanel gamepanel) {
		super(gamepanel);
		
		name = "chest";
		STATIC = setup("/objects/chest", gamepanel.tileSize, gamepanel.tileSize);
		collision = true; 
	
	}
}