package character;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.time.Year;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.text.html.parser.Entity;

import org.w3c.dom.UserDataHandler;

import main.TOOL;
import main.gamepanel;


public class entityImage  {
	
	public gamepanel gamepanel;

	public int Worldx, Worldy;
	public int speed;
	


	public int getTileDistance(entityImage targetEntity) {
		int tiledistance = (getXdistance(targetEntity)) + getYdistance(targetEntity)/gamepanel.tileSize;
		return tiledistance;
	}
	public int getGoalcol(entityImage targetEntity) {
		 int goalCol = (targetEntity.Worldx + targetEntity.solidAreaRectangle.x)/gamepanel.tileSize;
		 return goalCol;  
	}
	public int getGoalrow(entityImage targetEntity) {
		 int goalRow = (targetEntity.Worldy + targetEntity.solidAreaRectangle.y)/gamepanel.tileSize;
		 return goalRow;  
	}
	
	//object detection
	public int getleftx() {
		return Worldx+ solidAreaRectangle.x;
	}
	public int getrightx() {
		return Worldx + solidAreaRectangle.x + solidAreaRectangle.width;
	}
	public int getTopY() {
		return Worldy + solidAreaRectangle.y;
	}
	public int getBottomY() {
		return Worldy + solidAreaRectangle.y + solidAreaRectangle.height;
	}
	public int getCol() {
		return (Worldx + solidAreaRectangle.x)/gamepanel.tileSize;
	}
	public int getRow() {
		return (Worldy + solidAreaRectangle.y)/gamepanel.tileSize;
	}
	
	public int getcenterX() {
		int centerx = Worldx + UP1.getWidth()/2;
		return centerx;
	}
	
	public int getcenterY() {
		int centery = Worldy + UP1.getHeight();
		return centery;
	}
	
	//MONSTER DIRECTION
	public int getXdistance(entityImage targetEntity) {
		int xDistance = Math.abs(getcenterX() - targetEntity.getcenterX());
		return xDistance;
	}
	public int getYdistance(entityImage targetEntity) {
		int yDistance = Math.abs(getcenterY() - targetEntity.getcenterY());
		return yDistance;
	}
	
	//MOVEMENT
	public BufferedImage STATIC, UP1, UP2, DOWN1, DOWN2, RIGHT1, RIGHT2, LEFT1, LEFT2, attackdown1, 
	attackdown2, attackleft1, attackleft2, attackright1, attackright2, attackup1, attackup2 ; 
	public String directionString = "STATIC";
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	

	public int solidareadefaultx, solidareadefauly; 
	public Rectangle solidAreaRectangle = new Rectangle(0, 0, 48,48);
	public Rectangle attackRangeRectangle = new Rectangle(0, 0, 0, 0);
	public boolean CollisionISOn = false; 
	public int actionCounter = 0;
	
	
	//Pathfinding
	public boolean onPath = false;
	
	
	//DEATH ANIMATION 
	public boolean alive = true;
	public boolean dying = false;
	int dyingcounter = 0;
	
	//If object, monster or NPC
	public int type; 
	public final int type_player = 0;
	public final int type_monster = 3;
	public final int type_door = 1;
	public final int type_chest = 2;
	public final int type_pickup = 4;
	public final int type_design = 5;
	public final int type_consumable = 6;
	
	//Health Mechanics GAME
	public boolean invincible = false;
	public int invincibleCounter = 0;
	public boolean hpON = false;
	int hpcounter = 0; 
	int regentimer = 0;
	
	//Dialogue 
	public String dialogues[] = new String[50];
	int dialogueindex = 0;
	
	//Character life
	public int maxLife;
	public int life;

	
	//ITEM Description
	public String descriptionString = "";
	
	
	//ATTACKING ANIMATION
	public boolean attacking = false; 
	
	public entityImage (gamepanel gamepanel) {
		
		this.gamepanel = gamepanel;
	
	}
	
	
	public void setAction() {}
	public void damageReact() {}
	public void speak() {}
	public void interact() {}
	
	public void checkCollision() {
		CollisionISOn = false; 
		gamepanel.collisionCheck.collisiontile(this);
		gamepanel.collisionCheck.checkObject(this,true);
		gamepanel.collisionCheck.entityCollision(this, gamepanel.NPC);
		gamepanel.collisionCheck.entityCollision(this, gamepanel.monster);
		
		//MONSTER CONTACT WITH PLAYER
		boolean contactWithplayer = gamepanel.collisionCheck.checkMainplayer(this);
		if (this.type == type_monster  && contactWithplayer == true) {
			if (gamepanel.player.invincible == false) {
				gamepanel.player.life -= 1;
				gamepanel.player.invincible = true; 
			}
		}
	}
	
	public boolean use(entityImage entity) {return false;}
	

	
	
	public void update() {
			
		if (attacking == true) {
			attackingMeth();
		}
		
			checkCollision();
			setAction();
			
			
			
			
		if (CollisionISOn == false) {
				switch (directionString) {
				case "UP":
					Worldy -= speed;
					break;
				case "DOWN":
					Worldy += speed;
					break;	
				case "LEFT":
					Worldx -= speed;
					break;	
				case "RIGHT":
					Worldx += speed;
					break;
			}
		}
			
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1; 
				}
				spriteCounter = 0;
			} 
			if (invincible == true) {
				invincibleCounter++;
				if (invincibleCounter > 30) {
					invincible = false;
					invincibleCounter = 0;
				}
			}
		}	

	public void checkItemDrop() {}
	public void droppedItem(entityImage droppedItem) {
		for(int i = 0; i < gamepanel.object.length; i++) {
			if (gamepanel.object[gamepanel.currentMap][i] == null) {
				gamepanel.object[gamepanel.currentMap][i] = droppedItem;
				gamepanel.object[gamepanel.currentMap][i].Worldx = Worldx;
				gamepanel.object[gamepanel.currentMap][i].Worldy = Worldy;
				break;
			}
				
		}
	}


	
	
	public void checkstartchasing(entityImage target, int distance, int rate) {
		if (getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = true;
			}
		}
	}
	
	
	
	public void checkStopchasing(entityImage target, int distance, int rate) {
		if (getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = false;
			}
		}
	}
	
	public void getRandomMovement(int interval) {
		actionCounter ++;
		if (actionCounter == interval) {
			
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
	
	public void moveTowardPlayer(int interval) {
		actionCounter ++;
		if (actionCounter > interval) {
			if (getXdistance(gamepanel.player)> getYdistance(gamepanel.player)){
				if (gamepanel.player.getcenterX() < getcenterX()) {
					directionString = "LEFT";
				}
				else {
					directionString = "RIGHT";
				}
			} else if (getXdistance(gamepanel.player) < getYdistance(gamepanel.player)) {
				if (gamepanel.player.getcenterY() < getcenterY()) {
					directionString = "UP";
				}
				else {
					directionString = "DOWN";
				}
			}
			actionCounter = 0;
		}
	}

	public void attackingMeth() {
		
		spriteCounter++;
		if (spriteCounter <= 12) {
			 spriteNum = 1;
		}
		if (spriteCounter > 12 && spriteCounter <= 25) {
			spriteNum = 2;
			
			int currentworldX = Worldx;
			int currentworldY = Worldy;
			int solidAreaWidth = solidAreaRectangle.width;
			int solidAreaHeight = solidAreaRectangle.height;
			
			//Player Attack area adjustment
			switch(directionString) {
			case "UP": Worldy -= attackRangeRectangle.height; break;
			case "DOWN": Worldy += attackRangeRectangle.height; break;
			case "LEFT": Worldx -= attackRangeRectangle.width; break;
			case "RIGHT": Worldx += attackRangeRectangle.width; break;
			}
			solidAreaRectangle.width = attackRangeRectangle.width;
			solidAreaRectangle.height = attackRangeRectangle.height;
			
			if (type == type_monster ) {
				if (gamepanel.collisionCheck.checkMainplayer(this) == true) {
					damageReact(); 
				}
			} else {
				
				int monsterIndex = gamepanel.collisionCheck.entityCollision(this, gamepanel.monster);
				gamepanel.player.monsterDamage(monsterIndex, this);
				
			}
		
			Worldx = currentworldX;
			Worldy = currentworldY;
			solidAreaRectangle.width = solidAreaWidth;
			solidAreaRectangle.height = solidAreaHeight;
		}
		if (spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
		
	}
	
	
	
	public void checkAttackOrNot(int rate, int straight, int horizontal) {
		boolean targetInRange = false;
		int xDis = getXdistance(gamepanel.player);
		int yDis = getYdistance(gamepanel.player);
		
		switch (directionString) {
		case "UP":
			if (gamepanel.player.getcenterY() < getcenterY() && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "DOWN":
			if (gamepanel.player.getcenterY() > getcenterY() && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "LEFT":
			if (gamepanel.player.getcenterX() < getcenterX() && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "RIGHT":
			if (gamepanel.player.getcenterX() > getcenterX() && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		}
		if (targetInRange == true) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				attacking = true;
				spriteNum = 1;
				spriteCounter = 0;
		
			}
		}
		
		
	}
	

	//DRAW ENTITY
	public void draw(Graphics2D graphics2d) {
		int screenX = Worldx - gamepanel.player.Worldx + gamepanel.player.screenX;
		int screenY = Worldy - gamepanel.player.Worldy + gamepanel.player.screenY;
		
		if(Worldx + gamepanel.tileSize*4 > gamepanel.player.Worldx - gamepanel.player.screenX && 
		   Worldx - gamepanel.tileSize < gamepanel.player.Worldx + gamepanel.player.screenX &&
		   Worldy + gamepanel.tileSize*4 > gamepanel.player.Worldy - gamepanel.player.screenY &&
		   Worldy - gamepanel.tileSize < gamepanel.player.Worldy + gamepanel.player.screenY) {
	
			int tempx = screenX;
			int tempy = screenY;
			
			switch (directionString) {
			case "UP":
				if (attacking == false) {
					if (spriteNum == 1 ) {image = UP1;}
					if (spriteNum == 2 ) {image = UP2;}
					}
				if (attacking == true) {
					tempy= screenY - UP1.getHeight();
					if (spriteNum == 1 ) {image = attackup1;}
					if (spriteNum == 2 ) {image = attackup2;}
					}
			break;
			case "DOWN":
				if (attacking == false) {
					if (spriteNum == 1 ) {image = DOWN1;}
					if (spriteNum == 2 ) {image = DOWN2;}
					}
				if (attacking == true) {
					if (spriteNum == 1 ) {image = attackdown1;}
					if (spriteNum == 2 ) {image = attackdown2;}
					}
			break;
			case "LEFT":
				if (attacking == false) {
					if (spriteNum == 1 ) {image = LEFT1;}
					if (spriteNum == 2 ) {image = LEFT2;}
					}
				if (attacking == true) {
					tempx = screenX - LEFT1.getWidth();
					if (spriteNum == 1 ) {image = attackleft1;}
					if (spriteNum == 2 ) {image = attackleft2;}
					}
			break;
			case "RIGHT":
				if (attacking == false) {
					if (spriteNum == 1 ) {image = RIGHT1;}
					if (spriteNum == 2 ) {image = RIGHT2;}
					}
				if (attacking == true) {
					if (spriteNum == 1 ) {image = attackright1;}
					if (spriteNum == 2 ) {image = attackright2;}
					}
			break;
			case "STATIC": 
				image = STATIC;
			break;
			}
			
			//MONSTER HEALTH BAR
			if (type == type_monster && hpON == true) {
				
				double scale = (double) gamepanel.tileSize/maxLife;
				double value = scale*life;
				
				graphics2d.setColor(new Color(35, 35, 35));
				graphics2d.fillRect(screenX-1, screenY-16, gamepanel.tileSize + 2, 12);
				
				
				graphics2d.setColor(new Color(255,0,30));
				graphics2d.fillRect(screenX, screenY - 15, (int)value, 10);
				
				hpcounter++;
				
				if (hpcounter > 720) {
					hpcounter = 0;
					hpON = false;
				}
			}
	
			
			
			if (invincible == true) {
				hpON = true;
				hpcounter = 0;
		        graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		    }
			
			if (dying == true) {
				dyingAnim(graphics2d);
			}
			
			graphics2d.drawImage(image, tempx, tempy, null);
			graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
		}
	
	public void dyingAnim(Graphics2D graphics2d) {
		dyingcounter ++;
		
		int i = 5;
		
		if (dyingcounter <= i) {
			Alpha(graphics2d, 0f);
		}
		if (dyingcounter > i && dyingcounter <= i*2) {
			Alpha(graphics2d, 1f);
		}
		if (dyingcounter > i*2 && dyingcounter <= i*3) {
			Alpha(graphics2d, 0f);
		}
		if (dyingcounter > i*3 && dyingcounter <= i*4) {
			Alpha(graphics2d, 1f);
		}
		if (dyingcounter > i*4 && dyingcounter <= i*5) {
			Alpha(graphics2d, 0f);
		}if (dyingcounter > i*5 && dyingcounter <= i*6) {
			Alpha(graphics2d, 1f);
		}
		if (dyingcounter > i*6 && dyingcounter <= i*7) {
			Alpha(graphics2d, 0f);
		}
		if (dyingcounter > i*7 && dyingcounter <= i*8) {
			Alpha(graphics2d, 1f);
		}
		if (dyingcounter > i*8) {
			dying = false; 
			alive = false;
		}
	}
	public void Alpha(Graphics2D graphics2d, float alpha) {
		graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}
	
	
	public BufferedImage setup(String imagename, int width, int height) {
		TOOL utilTool = new TOOL();
		BufferedImage scaledImage = null;
		
		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream(imagename + ".png"));
			scaledImage = utilTool.scaleImage(scaledImage, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scaledImage;
	}
	public void searchPath(int goalcol, int goalrow) {
		int startcol = (Worldx + solidAreaRectangle.x)/gamepanel.tileSize;
		int startrow = (Worldy + solidAreaRectangle.y)/gamepanel.tileSize;
		
		gamepanel.pathfinder.setNode(startcol, startrow, goalcol, goalrow, this);
		
		if (gamepanel.pathfinder.search() == true) {
			int nextx= gamepanel.pathfinder.pathList.get(0).col * gamepanel.tileSize;
			int nexty= gamepanel.pathfinder.pathList.get(0).row * gamepanel.tileSize;
			
			int entityLeftX = Worldx + solidAreaRectangle.x;
			int entityRightX = Worldx + solidAreaRectangle.x + solidAreaRectangle.width;
			int entityTOPY = Worldy + solidAreaRectangle.y;
			int entityBOTTOMY = Worldy + solidAreaRectangle.y + solidAreaRectangle.height;
			
			if (entityTOPY > nexty && entityLeftX >= nextx && entityRightX < nextx + gamepanel.tileSize) {
				directionString = "UP";
			} 
			else if (entityTOPY < nexty && entityLeftX >= nextx && entityRightX < nextx + gamepanel.tileSize) {
				directionString = "DOWN";
			} 
			else if (entityTOPY >= nexty && entityBOTTOMY < nexty + gamepanel.tileSize) {
				if (entityLeftX > nextx) {
					directionString = "LEFT";
				}
				if (entityLeftX < nextx) {
					directionString = "RIGHT";
				}
			}
			else if (entityTOPY > nexty && entityLeftX > nextx) {
				directionString = "UP";
				checkCollision();
				if (CollisionISOn == true) {
					directionString = "LEFT";
				}
			}
			else if (entityTOPY > nexty && entityLeftX < nextx) {
				directionString = "UP";
				checkCollision();
				if (CollisionISOn == true) {
					directionString = "RIGHT";
				}
				
			}
			else if (entityTOPY < nexty && entityLeftX > nextx) {
				directionString = "DOWN";
				checkCollision();
				if (CollisionISOn == true) {
					directionString = "LEFT";
				}
				
			}
			else if (entityTOPY < nexty && entityLeftX < nextx) {
				directionString = "DOWN";
				checkCollision();
				if (CollisionISOn == true) {
					directionString = "RIGHT";
				}
				
			}
			
			int nextcol = gamepanel.pathfinder.pathList.get(0).col;
			int nextrow = gamepanel.pathfinder.pathList.get(0).row;
			if (nextcol == goalcol && nextrow == goalrow) {
				onPath = false;
			}
			
		}
		
	}
	public int getDetected(entityImage user, entityImage target[][], String targetname) {
	
			int index = 999;
			int nextWorldX = user.getleftx();
			int nextWorldY = user.getrightx();
			switch (user.directionString) {
			case "UP":
				nextWorldY = user.getTopY() - user.speed;
				break;

			case "DOWN":
				nextWorldY = user.getBottomY() + user.speed;
				break;

			case "LEFT":
				nextWorldX = user.getleftx() - user.speed;
				break;

			case "RIGHT":
				nextWorldX = user.getrightx() + user.speed;
				break;
			}
			int col = nextWorldX/gamepanel.tileSize;
			int row = nextWorldY/gamepanel.tileSize;
			
			for (int i = 0; i < target[1].length; i++) {
				if (target[gamepanel.currentMap][i] != null) {
					if (target[gamepanel.currentMap][i].getCol() == col && target[gamepanel.currentMap]
							[i].getRow() == row && target[gamepanel.currentMap][i].name.equals(targetname)) {
						
						index = i;
						break;
					}
				}
			}
			return index;
	} 
}
