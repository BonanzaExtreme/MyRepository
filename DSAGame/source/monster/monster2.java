package monster;



import java.util.Random;

import character.entityImage;
import main.gamepanel;
import objectfolder.key;
import objectfolder.potion;

public class monster2 extends entityImage {
	gamepanel gamepanel;
	
	public monster2(gamepanel gamepanel) {
		super(gamepanel);
		this.gamepanel = gamepanel;
		
		type = type_monster;
		name = "BigMouth";
		speed = 2; 	
		maxLife = 6; 
		life = maxLife;
		
		solidAreaRectangle.x = 16;
		solidAreaRectangle.y = 32;
		solidAreaRectangle.width = 64;
		solidAreaRectangle.height = 54;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;
		attackRangeRectangle.width = 16;
		attackRangeRectangle.height = 16;
		
		getmonster2image();
		getAttackImage();
	}

	public void getmonster2image() {
		
			int i = 2; 
			
			UP1 = setup("/enemies/monster2_walk1", gamepanel.tileSize*i, gamepanel.tileSize*i);
			UP2 = setup("/enemies/monster2_walk2", gamepanel.tileSize*i, gamepanel.tileSize*i);
			DOWN1 = setup("/enemies/monster2_walk1", gamepanel.tileSize*i, gamepanel.tileSize*i);
			DOWN2 = setup("/enemies/monster2_walk2", gamepanel.tileSize*i, gamepanel.tileSize*i);
			RIGHT1 = setup("/enemies/monster2_walk1", gamepanel.tileSize*i, gamepanel.tileSize*i);
			RIGHT2= setup("/enemies/monster2_walk2", gamepanel.tileSize*i, gamepanel.tileSize*i);
			LEFT1 = setup("/enemies/monster2_walk1", gamepanel.tileSize*i, gamepanel.tileSize*i);
			LEFT2 = setup("/enemies/monster2_walk2", gamepanel.tileSize*i, gamepanel.tileSize*i);

	}
	public void getAttackImage() {
		int i = 2; 
		attackup1 = setup("/enemies/monster2_attack1left", gamepanel.tileSize*i, gamepanel.tileSize*i);
		attackup2 = setup("/enemies/monster2_attack2left", gamepanel.tileSize*i, gamepanel.tileSize*i);
		attackdown1 = setup("/enemies/monster2_attack1left", gamepanel.tileSize*i, gamepanel.tileSize*i);
		attackdown2 = setup("/enemies/monster2_attack2left", gamepanel.tileSize*i, gamepanel.tileSize*i);
		attackleft1 = setup("/enemies/monster2_attack1left", gamepanel.tileSize*i, gamepanel.tileSize*i);
		attackleft2 = setup("/enemies/monster2_attack2left", gamepanel.tileSize*i, gamepanel.tileSize*i);
		attackright1 = setup("/enemies/monster2_attack1right", gamepanel.tileSize*i, gamepanel.tileSize*i);
		attackright2 = setup("/enemies/monster2_attack2right", gamepanel.tileSize*i, gamepanel.tileSize*i);
	}


	
	//MONSTER ACTION
	public void setAction() {
		int xDistance = Math.abs(Worldx - gamepanel.player.Worldx);
		int yDistance = Math.abs(Worldy - gamepanel.player.Worldy);
		int tiledistance = (xDistance + yDistance)/gamepanel.tileSize;
		
		if (tiledistance > 4) {
			onPath = false;
		}
		
		if (onPath == true) {
	
			 searchPath(getGoalcol(gamepanel.player), getGoalrow(gamepanel.player));
			 
		}
		
		else {
			if(onPath == false && tiledistance < 4) {
				
				int i = new Random().nextInt(100)+1;
				if (i > 50) {
					onPath = true;
				}
			}

			actionCounter ++;
			if (actionCounter == 120) {
				
				Random rando = new Random();
				int i = rando.nextInt(100) + 1; 
				
				if (i <= 25) {
					directionString = "UP";
				}
				if (i > 25 && i <= 50) {
					directionString = "DOWN";
				}
				if (i > 50 && i <= 75) {
					directionString = "LEFT";
				}
				if (i > 75 && i <= 100) {
					directionString = "RIGHT";
				}
				
				actionCounter = 0; 	
			}	
			
		}
	
	}
	public void damageReact() {
		actionCounter = 0;
		onPath = true;
	}
		public void checkItemDrop() {
		
		int i = 100;
		if (i == 100) {
			droppedItem(new potion(gamepanel));		
		}
		
	}
	
}	
