package monster;

import java.util.zip.Inflater;

import character.entityImage;
import main.gamepanel;
import objectfolder.key;

public class boss2 extends entityImage{
	gamepanel gamepanel;
	public static final String MONSTERNAME_STRING = "Skeleton";
		
		public boss2(gamepanel gamepanel) {
			super(gamepanel);
			this.gamepanel = gamepanel;
			
			type = type_monster;
			name = "Skeleton";
			speed = 1; 	
			maxLife = 45; 
			life = maxLife;
			
			int size = gamepanel.tileSize*4;
			solidAreaRectangle.x = 48;
			solidAreaRectangle.y = 48;
			solidAreaRectangle.width = size - 48*2;
			solidAreaRectangle.height = size - 48;
			solidareadefaultx = solidAreaRectangle.x;
			solidareadefauly = solidAreaRectangle.y;
			attackRangeRectangle.width = 100;
			attackRangeRectangle.height = 100;
			
			
			getboss2image();
			getAttackImage();
		}

		public void getboss2image() {
			
				int i = 4; 
				
				UP1 = setup("/enemies/finalbosswalkup1", gamepanel.tileSize*i, gamepanel.tileSize*i);
				UP2 = setup("/enemies/finalbosswalkup2", gamepanel.tileSize*i, gamepanel.tileSize*i);
				DOWN1 = setup("/enemies/finalbosswalkdown1", gamepanel.tileSize*i, gamepanel.tileSize*i);
				DOWN2 = setup("/enemies/finalbosswalkdown2", gamepanel.tileSize*i, gamepanel.tileSize*i);
				RIGHT1 = setup("/enemies/finalbosswalkright1", gamepanel.tileSize*i, gamepanel.tileSize*i);
				RIGHT2= setup("/enemies/finalbosswalkright2", gamepanel.tileSize*i, gamepanel.tileSize*i);
				LEFT1 = setup("/enemies/finalbosswalkleft1", gamepanel.tileSize*i, gamepanel.tileSize*i);
				LEFT2 = setup("/enemies/finalbosswalkleft2", gamepanel.tileSize*i, gamepanel.tileSize*i);
				
		}
		public void getAttackImage() {
			int i = 4; 
			attackup1 = setup("/enemies/finalbossattackup1", gamepanel.tileSize*i, gamepanel.tileSize*i);
			attackup2 = setup("/enemies/finalbossattackup2", gamepanel.tileSize*i, gamepanel.tileSize*i);
			attackdown1 = setup("/enemies/finalbossattackdown1", gamepanel.tileSize*i, gamepanel.tileSize*i);
			attackdown2 = setup("/enemies/finalbossattackdown2", gamepanel.tileSize*i, gamepanel.tileSize*i);
			attackleft1 = setup("/enemies/finalbossattackleft1", gamepanel.tileSize*i, gamepanel.tileSize*i);
			attackleft2 = setup("/enemies/finalbossattackleft2", gamepanel.tileSize*i, gamepanel.tileSize*i);
			attackright1 = setup("/enemies/finalbossattackright1", gamepanel.tileSize*i, gamepanel.tileSize*i);
			attackright2 = setup("/enemies/finalbossattackright2", gamepanel.tileSize*i, gamepanel.tileSize*i);
		}


		
		//MONSTER ACTION
		public void setAction() {
			if (getTileDistance(gamepanel.player) < 10) {
					moveTowardPlayer(60);
			} else {
				
				getRandomMovement(120);
			}
			
			if (attacking == false) {
				checkAttackOrNot(10, gamepanel.tileSize*4, gamepanel.tileSize);
			}
		
		}
		public void damageReact() {
			actionCounter = 0;
	
		
		}
		public void droppedItem() {
			int i = 100;
			if (i == 100) {
				droppedItem(new key(gamepanel));	
			}
		}
	}

