package objectfolder;

import character.entityImage;
import main.gamepanel;
import main.state;

public class Door extends entityImage {
		gamepanel gamepanel;
		
	public Door (gamepanel gamepanel) {
		super(gamepanel);
		this.gamepanel = gamepanel; 
		
		type = type_door;
		
		name = "Door";
		STATIC = setup("/objects/Gate_FRONT", gamepanel.tileSize, gamepanel.tileSize);
		
		collision = true; 
			
		solidAreaRectangle.x = 0;
		solidAreaRectangle.y = 16;
		solidAreaRectangle.width = 32;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;
	
	}
	
	
	public void interact() {
		gamepanel.gamestate = gamepanel.dialogue; 
		gamepanel.state.currentDialogueString = "YOU NEED KEY!";
		
		
	}
}
