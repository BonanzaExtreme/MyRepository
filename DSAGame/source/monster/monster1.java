package monster;



import java.util.Random;

import character.entityImage;
import main.gamepanel;

public class monster1 extends entityImage {
	gamepanel gamepanel;
	
	public monster1(gamepanel gamepanel) {
		super(gamepanel);
		this.gamepanel = gamepanel;
		
		type = type_monster;
		name = "Zombie";
		speed = 1; 	
		maxLife = 3; 
		life = maxLife;
		
		solidAreaRectangle.x = 3;
		solidAreaRectangle.y = 18;
		solidAreaRectangle.width = 42;
		solidAreaRectangle.height = 34;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;
		
		getmonster1image();
	}

	public void getmonster1image() {
		
			UP1 = setup("/enemies/monster1_walk1", gamepanel.tileSize, gamepanel.tileSize);
			UP2 = setup("/enemies/monster1_walk2", gamepanel.tileSize, gamepanel.tileSize);
			DOWN1 = setup("/enemies/monster1_walk1", gamepanel.tileSize, gamepanel.tileSize);
			DOWN2 = setup("/enemies/monster1_walk2", gamepanel.tileSize, gamepanel.tileSize);
			RIGHT1 = setup("/enemies/monster1_walk1", gamepanel.tileSize, gamepanel.tileSize);
			RIGHT2= setup("/enemies/monster1_walk2", gamepanel.tileSize, gamepanel.tileSize);
			LEFT1 = setup("/enemies/monster1_walk1", gamepanel.tileSize, gamepanel.tileSize);
			LEFT2 = setup("/enemies/monster1_walk2", gamepanel.tileSize, gamepanel.tileSize);
			STATIC = setup("/enemies/monster1_static", gamepanel.tileSize, gamepanel.tileSize);
	}

	public void update() {
		super.update();
		
		int xDistance = Math.abs(Worldx - gamepanel.player.Worldx);
		int yDistance = Math.abs(Worldy - gamepanel.player.Worldy);
		int tiledistance = (xDistance + yDistance)/gamepanel.tileSize;
		
		if(onPath == false && tiledistance < 4) {
			int i = new Random().nextInt(100)+1;
			if (i > 50) {
				onPath = true;
			}
		}
		if (onPath == true && tiledistance > 4) {
			onPath = false;
		}
		
	}
	
	//MONSTER ACTION
	public void setAction() {
		
		if (onPath == true) {
			 int goalCol = (gamepanel.player.Worldx + gamepanel.player.solidAreaRectangle.x)/gamepanel.tileSize;
			 int goalRow = (gamepanel.player.Worldy + gamepanel.player.solidAreaRectangle.y)/gamepanel.tileSize;
			 
			 searchPath(goalCol, goalRow);
			 
		} else {

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
				else {
					directionString = "STATIC";
				}
				
				actionCounter = 0; 	
			}	
		}	
	}
	public void damageReact() {
		actionCounter = 0;
		onPath = true;
	}
	
}	
