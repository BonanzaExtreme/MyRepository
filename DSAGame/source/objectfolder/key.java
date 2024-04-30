package objectfolder;

import javax.imageio.ImageIO;

import character.entityImage;
import main.gamepanel;

public class key extends entityImage {

	public key (gamepanel gamepanel) {
		super(gamepanel);
		type = type_pickup;
		name = "key";
		STATIC = setup("/objects/key", gamepanel.tileSize, gamepanel.tileSize);
		
	}
}

