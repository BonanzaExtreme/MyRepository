package monster;

import java.util.Random;

import character.entityImage;
import main.gamepanel;
import objectfolder.key;

public class boss1 extends entityImage {
gamepanel gamepanel;
	
	public boss1(gamepanel gamepanel) {
		super(gamepanel);
		this.gamepanel = gamepanel;
		
		type = type_monster;
		name = "Knight";
		speed = 13; 	
		maxLife = 24; 
		life = maxLife;
		

		solidAreaRectangle.x = 4;
		solidAreaRectangle.y = 4;
		solidAreaRectangle.width = 40; 
		solidAreaRectangle.height = 44;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;

		
		getboss1image();
	
	}

	public void getboss1image() {
		
			 
			
			UP1 = setup("/enemies/catrunup1", gamepanel.tileSize, gamepanel.tileSize*2);
			UP2 = setup("/enemies/catrunup2", gamepanel.tileSize, gamepanel.tileSize*2);
			DOWN1 = setup("/enemies/catrundown1", gamepanel.tileSize, gamepanel.tileSize*2);
			DOWN2 = setup("/enemies/catrundown2", gamepanel.tileSize, gamepanel.tileSize*2);
			RIGHT1 = setup("/enemies/catrunright1", gamepanel.tileSize*2, gamepanel.tileSize);
			RIGHT2= setup("/enemies/catrunright2", gamepanel.tileSize*2, gamepanel.tileSize);
			LEFT1 = setup("/enemies/catrunleft1", gamepanel.tileSize*2, gamepanel.tileSize);
			LEFT2 = setup("/enemies/catrunleft2", gamepanel.tileSize*2, gamepanel.tileSize);
	}

	
	public void setAction() {

		actionCounter ++;
		if (actionCounter == 40) {
			
			Random rando = new Random();
			int i = rando.nextInt(100)+1; 
			
			if (i <= 10) {
				directionString = "LEFT";
			}
			if (i > 10 && i <= 20) {
				directionString = "DOWN";
			}
			if (i > 20 && i <= 30) {
				directionString = "UP";
			}
			if (i > 30 && i <= 40) {
				directionString = "RIGHT";
			}
	
			
			actionCounter = 0; 	
		}
		
		}
		
	public void damageReact() {
		actionCounter = 0;
	
	}
	public void checkItemDrop() {
		
		int i = 100;
		if (i == 100) {
			droppedItem(new key(gamepanel));		
		}
		
	}
}
