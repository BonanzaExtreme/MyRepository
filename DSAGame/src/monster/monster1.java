package monster;



import java.util.Random;

import character.entityImage;
import main.gamepanel;

public class monster1 extends entityImage {

	public monster1(gamepanel gamepanel) {
		super(gamepanel);
		
		name = "Zombie";
		directionString = "DOWN";
		speed = 1; 
		maxLife = 2; 
		life = maxLife;
		
		solidAreaRectangle.x = 3;
		solidAreaRectangle.y = 18;
		solidAreaRectangle.width = 42;
		solidAreaRectangle.height = 30;
		solidareadefaultx = solidAreaRectangle.x;
		solidareadefauly = solidAreaRectangle.y;
		
		getmonster1image();
	}

	public void getmonster1image() {
		
		UP1 = setup("/enemies/monster1_walk1");
		UP2 = setup("/enemies/monster1_walk2");
		DOWN1 = setup("/enemies/monster1_walk1");
		DOWN1 = setup("/enemies/monster1_walk2");
		RIGHT1 = setup("/enemies/monster1_walk1");
		RIGHT2= setup("/enemies/monster1_walk2");
		LEFT1 = setup("/enemies/monster1_walk1");
		LEFT2 = setup("/enemies/monster1_walk2");
		STATIC = setup("/enemies/monster1_static");
		
	}
	public void setMonster1Action() {
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
