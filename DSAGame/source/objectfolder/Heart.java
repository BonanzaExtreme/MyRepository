package objectfolder;
import character.entityImage;
import main.gamepanel;

public class Heart extends entityImage  {
	
	public Heart(gamepanel gamepanel) {
		super(gamepanel);
		
		name = "Heart";
		image = setup("/objects/heart_full", gamepanel.tileSize, gamepanel.tileSize);
		image2 = setup("/objects/heart_half", gamepanel.tileSize, gamepanel.tileSize);
		image3 = setup("/objects/heart_empty", gamepanel.tileSize, gamepanel.tileSize);
	
	}
}
