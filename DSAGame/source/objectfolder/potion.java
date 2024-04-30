package objectfolder;

import character.entityImage;
import main.gamepanel;

public class potion extends entityImage{
	gamepanel gamepanel;
	int value = 2;

	public potion (gamepanel gamepanel) {
		super(gamepanel);
		this.gamepanel = gamepanel;
		
		type = type_consumable;
		name = "potion";
		STATIC = setup("/objects/potion", gamepanel.tileSize, gamepanel.tileSize);
		descriptionString = "[" + name + "]\nHeals";
		
	}	
	public boolean use(entityImage entity) {
	
		gamepanel.gamestate = gamepanel.dialogue;
		gamepanel.state.currentDialogueString = "YOUR LIFE HAS BEEN RECOVERED! ";
		
		
		entity.life += value;
		if (gamepanel.player.life > gamepanel.player.maxLife) {
				gamepanel.player.life = gamepanel.player.maxLife;
		}
		return true;  
		}
	}

