package objectfolder;

import character.entityImage;
import main.gamepanel;

public class Door2 extends entityImage {
	gamepanel gamepanel;
	
	public Door2 (gamepanel gamepanel) {
		super(gamepanel);
		this.gamepanel = gamepanel; 
		
		type = type_door;
		
		name = "Door";
		STATIC = setup("/objects/Gate_FRONT", gamepanel.tileSize*2, gamepanel.tileSize);
		collision = true; 
			
		solidAreaRectangle.x = 0;
		solidAreaRectangle.y = 16;
		solidAreaRectangle.width = 32;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;
	
	}
	
	
	public void interact(int state) {
		gamepanel.gamestate = gamepanel.dialogue; 
		gamepanel.state.currentDialogueString = "YOU NEED KEY!";
		
		
	}
}
