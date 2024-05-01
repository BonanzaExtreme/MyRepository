package objectfolder;
import character.entityImage;
import main.gamepanel;

public class chest extends entityImage {
	
	gamepanel gamepanel;
	entityImage loot;
	boolean open = false;
	
	public chest (gamepanel gamepanel, entityImage loot) {
		super(gamepanel);
		this.gamepanel = gamepanel;
		this.loot = loot;
		
		type = type_door;
		name = "chest";
		image = setup("/objects/chest", gamepanel.tileSize, gamepanel.tileSize);
		image2 = setup("/objects/chestopen", gamepanel.tileSize, gamepanel.tileSize);
		
		STATIC = image;
		
		collision = true; 
		
		solidAreaRectangle.x = 4;
		solidAreaRectangle.y = 16;
		solidAreaRectangle.width = 40;
		solidAreaRectangle.height = 32;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;
	
	}
	public void interact() {
		gamepanel.gamestate = gamepanel.dialogue;
		
		if (open == false) {
			StringBuilder aBuilder = new StringBuilder();
			aBuilder.append("You found a " + loot.name + "!");
			
			if (gamepanel.player.inventory.size() == gamepanel.player.maxinventorysize) {
				aBuilder.append("STORAGE FULL!");
			} else {
				aBuilder.append("\n YOou obtain " + loot.name);
				gamepanel.player.inventory.add(loot);
				STATIC = image2; 
				open = true;
			}
			gamepanel.state.currentDialogueString = aBuilder.toString();
		}
	
	}
}