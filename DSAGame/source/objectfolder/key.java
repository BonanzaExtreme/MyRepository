package objectfolder;

import javax.imageio.ImageIO;

import character.entityImage;
import main.gamepanel;

public class key extends entityImage {
	gamepanel gamepanel;

	public key (gamepanel gamepanel) {
		super(gamepanel);
		this.gamepanel = gamepanel;
		
		type = type_consumable;
		name = "key";
		STATIC = setup("/objects/key", gamepanel.tileSize, gamepanel.tileSize);
		descriptionString = "[" + name + "]\nOpens A door";
		
	}
	public boolean use(entityImage entity) {
		gamepanel.gamestate = gamepanel.dialogue;
		
		int objectDetected = getDetected(entity, gamepanel.object, "Door");
		if (objectDetected != 999) {
			gamepanel.state.currentDialogueString = "You used the " + name + " and opened the door";
			gamepanel.object[gamepanel.currentMap][objectDetected] = null;
			return true;
		}
		else {
			gamepanel.state.currentDialogueString = "YOU CAN'T USE THAT HEREEEEEEEE!!!! >:( ";
			return false;
			
		}
		
	}
}


