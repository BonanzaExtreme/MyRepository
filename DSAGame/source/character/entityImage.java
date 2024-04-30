package character;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.text.html.parser.Entity;

import main.TOOL;
import main.gamepanel;


public class entityImage  {
	
	gamepanel gamepanel;

	public int Worldx, Worldy;
	public int speed;
	
	
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
	
	//Health Mechanics GAME
	public boolean invincible = false;
	public int invincibleCounter = 0;
	public boolean hpON = false;
	int hpcounter = 0; 
	int regentimer = 0;
	
	//Dialogue 
	String dialogues[] = new String[50];
	int dialogueindex = 0;
	
	//Character life
	public int maxLife;
	public int life;
	
	
	//ATTACKING ANIMATION
	boolean attacking = false; 
	
	public entityImage (gamepanel gamepanel) {
		
		this.gamepanel = gamepanel;
	
	}
	
	
	public void setAction() {}
	public void damageReact() {}
	public void speak() {}
	public void interact() {
		
	}
	
	public void checkCollision() {
		CollisionISOn = false; 
		gamepanel.collisionCheck.collisiontile(this);
		gamepanel.collisionCheck.checkObject(this, false);
		gamepanel.collisionCheck.entityCollision(this, gamepanel.NPC);
		gamepanel.collisionCheck.entityCollision(this, gamepanel.monster);
		
		//MONSTER CONTACT WITH PLAYER
		boolean contactWithplayer = gamepanel.collisionCheck.checkMainplayer(this);
		if (this.type == 2  && contactWithplayer == true) {
			if (gamepanel.player.invincible == false) {
				gamepanel.player.life -= 1;
				gamepanel.player.invincible = true; 
			}
		}
	}
	
	
	public void update() {
			
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


	//DRAW ENTITY
	public void draw(Graphics2D graphics2d) {
		int screenX = Worldx - gamepanel.player.Worldx + gamepanel.player.screenX;
		int screenY = Worldy - gamepanel.player.Worldy + gamepanel.player.screenY;
		
		if(Worldx + gamepanel.tileSize > gamepanel.player.Worldx - gamepanel.player.screenX && 
		   Worldx - gamepanel.tileSize < gamepanel.player.Worldx + gamepanel.player.screenX &&
		   Worldy + gamepanel.tileSize > gamepanel.player.Worldy - gamepanel.player.screenY &&
		   Worldy - gamepanel.tileSize < gamepanel.player.Worldy + gamepanel.player.screenY) {
			 
			BufferedImage image = null;
			if (directionString == "UP") {
				
				if (spriteNum == 1) {
					image = UP1; 				
				} else if (spriteNum == 2) {
					image = UP2;
				}

			} else if (directionString == "DOWN") {
				if (spriteNum == 1) {
					image = DOWN1; 				
				} else if (spriteNum == 2) {
					image = DOWN2;
				}
			 } 
			  else if (directionString == "LEFT") {
				if (spriteNum == 1) {
					image = LEFT1; 				
				} else if (spriteNum == 2) {
					image = LEFT2;
				}
			 } 
			  else if (directionString == "RIGHT") {
					if (spriteNum == 1) {
						image = RIGHT1; 				
					} else if (spriteNum == 2) {
						image = RIGHT2;
					}
				 } 
			 else {
				image = STATIC;
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
			
			graphics2d.drawImage(image, screenX, screenY, gamepanel.tileSize, gamepanel.tileSize, null);
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
			int entityTOPY = Worldy+ solidAreaRectangle.x + solidAreaRectangle.width;
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
	
}
