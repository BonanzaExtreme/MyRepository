package objectfolder;

import character.entityImage;
import main.gamepanel;

public class treasure extends entityImage {
	gamepanel gamepanel;
	public static final String OBJECTNAME_STRING = "Treasure";
	
	public treasure(gamepanel gamepanel) {
		super(gamepanel);
		this.gamepanel = gamepanel;
		
		type = type_pickup; 
		name = OBJECTNAME_STRING;
		STATIC = setup("/objects/treasure", gamepanel.tileSize, gamepanel.tileSize);
		
	}
	
	public boolean use(entityImage entityImage) {
		gamepanel.gamestate = gamepanel.dialogue; 
		gamepanel.gamestate = gamepanel.cutsceneSTATE;
		gamepanel.csManager.sceneNo = gamepanel.csManager.ending;  
		return true; 
	}
}
