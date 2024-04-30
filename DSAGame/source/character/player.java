	package character;
	import java.awt.AlphaComposite;
	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics2D;
	import java.awt.Rectangle;
	import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

import org.w3c.dom.Text;

import main.gamepanel;
	import main.keyhandler;
	import main.sounds;
	import main.state;
import objectfolder.key;
import objectfolder.potion;
	
	public class player extends entityImage {
	
		keyhandler keyhandler; 
		public final int screenX;
		public final int screenY;
		public ArrayList<entityImage> inventory = new ArrayList<>();
		public final int maxinventorysize = 10;
		int keyno = 0;
		
		public player(gamepanel gamepanel, keyhandler keyhandler) {
			
			super(gamepanel);
			
			this.keyhandler = keyhandler; 
			
			
			screenX = gamepanel.screenWidth/2 - (gamepanel.tileSize/2);
			screenY = gamepanel.screenHeight/2 - (gamepanel.tileSize/2);
			
			solidAreaRectangle = new Rectangle();
			solidAreaRectangle.x = 8;
			solidAreaRectangle.y = 16;
			solidareadefaultx = solidAreaRectangle.x;
			solidareadefauly = solidAreaRectangle.y;
			solidAreaRectangle.width = 32;
			solidAreaRectangle.height = 32;
			
			//PLAYER ATTACK
			attackRangeRectangle.width = 48;
			attackRangeRectangle.height = 48;	
			
			defaultvalue();
			getPlayerImage();
			getPlayerAttackImage();
			setItem();
		}
		
		public void defaultvalue() {
			
			Worldx = gamepanel.tileSize * 25;
			Worldy = gamepanel.tileSize * 15; 
			speed = 2; 
			directionString = "STATIC"; 
			invincible = false;
			gamepanel.currentMap = 0; 
	
			
			//player life 
			maxLife = 6;
			life = maxLife;
		
		}


		
		public void getPlayerImage() {
			
			UP1 = setup("/player/MainChar_UP1", gamepanel.tileSize, gamepanel.tileSize);
			UP2 = setup("/player/MainChar_UP2", gamepanel.tileSize, gamepanel.tileSize);
			DOWN1 = setup("/player/MainChar_DOWN1", gamepanel.tileSize, gamepanel.tileSize);
			DOWN2 = setup("/player/MainChar_DOWN2", gamepanel.tileSize, gamepanel.tileSize);
			RIGHT1= setup("/player/MainChar_RIGHT1", gamepanel.tileSize, gamepanel.tileSize);
			RIGHT2 = setup("/player/MainChar_RIGHT2", gamepanel.tileSize, gamepanel.tileSize);
			LEFT1 = setup("/player/MainChar_LEFT1", gamepanel.tileSize, gamepanel.tileSize);
			LEFT2 = setup("/player/MainChar_LEFT2", gamepanel.tileSize, gamepanel.tileSize);
			STATIC = setup("/player/MainChar_Static", gamepanel.tileSize, gamepanel.tileSize);
		}
		
		public void getPlayerAttackImage() {
			attackup1 = setup("/player/player_attackup1", gamepanel.tileSize, gamepanel.tileSize*2);
			attackup2 = setup("/player/player_attackup2", gamepanel.tileSize, gamepanel.tileSize*2);
			attackdown1 = setup("/player/player_attackdown1", gamepanel.tileSize, gamepanel.tileSize*2);
			attackdown2 = setup("/player/player_attackdown2", gamepanel.tileSize, gamepanel.tileSize*2);
			attackleft1 = setup("/player/player_attackleft1", gamepanel.tileSize*2, gamepanel.tileSize);
			attackleft2 = setup("/player/player_attackleft2", gamepanel.tileSize*2, gamepanel.tileSize);
			attackright1 = setup("/player/player_attackright1", gamepanel.tileSize*2, gamepanel.tileSize);
			attackright2 = setup("/player/player_attackright2", gamepanel.tileSize*2, gamepanel.tileSize);
		}
	
		public void update() {
			
			if (attacking == true) {
				attackingMeth();
				CollisionISOn = false;
		        gamepanel.collisionCheck.collisiontile(this);
		    } else {
		        CollisionISOn = false;
		        gamepanel.collisionCheck.collisiontile(this);
		    }
			
		
		
			if (keyhandler.up == true || keyhandler.down == true || keyhandler.left == true ||
					keyhandler.right == true || keyhandler.enter == true)  {
				
				if (keyhandler.up == true) {
					directionString = "UP";
				} else if (keyhandler.down == true) {
					directionString = "DOWN";
				} else if (keyhandler.left == true) {
					directionString = "LEFT";
				} else if (keyhandler.right == true) {
					directionString = "RIGHT";
	
				} 
				CollisionISOn = false;
				gamepanel.collisionCheck.collisiontile(this);
				
				//Object collision
				int objectIndex = gamepanel.collisionCheck.checkObject(this, true);
				objectInteraction(objectIndex); 
				
				//collision for NPC
				int npcIndex = gamepanel.collisionCheck.entityCollision(this, gamepanel.NPC);
				npcInteraction(npcIndex);
				
				//Monster Collision
				int monsterIndex = gamepanel.collisionCheck.entityCollision(this, gamepanel.monster);
				monsterInteraction(monsterIndex);
				
				regentimer++;
			    if (regentimer > 100 && life < maxLife) { 
			        life++;
			        if (life > maxLife) {
			            life = maxLife;
			        }
			        regentimer = 0;
			    }
				
				
				
				
				
				gamepanel.eventRect.checkevent();
				
				
				//Player movement collision
				if (CollisionISOn == false && keyhandler.enter == false) {
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
				gamepanel.keyhandler.enter = false; 
				
				spriteCounter++;
				if (spriteCounter > 12) {
					if (spriteNum == 1) {
						spriteNum = 2;
					} else if (spriteNum == 2) {
						spriteNum = 1; 
					}
					spriteCounter = 0;
				} 
				
			} 
			
			if (invincible == true) {
				invincibleCounter++;
				if (invincibleCounter > 30) {
					invincible = false;
					invincibleCounter = 0;
				}
			}
			
				if (life > maxLife) {
					life = maxLife;
				}
				
				if (life <= 0) {
					gamepanel.gamestate = gamepanel.gameover;
					gamepanel.playSoundEffect(1);
			     
				}
			}
		
		public void setItem() {
			
		}
			
		public void attackingMeth() {
			
			spriteCounter++;
			if (spriteCounter <= 5) {
				 spriteNum = 1;
			}
			if (spriteCounter > 5 && spriteCounter <= 25) {
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
				
				int monsterIndex = gamepanel.collisionCheck.entityCollision(this, gamepanel.monster);
				monsterDamage(monsterIndex);
				
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
			
		//NPC Interaction
		public void npcInteraction(int i) {
			
			if (gamepanel.keyhandler.enter == true) {
				if (i != 999) {
					 gamepanel.gamestate = gamepanel.dialogue;
					 gamepanel.NPC[gamepanel.currentMap][i].speak();
					 gamepanel.state.image = gamepanel.NPC[gamepanel.currentMap][i].STATIC;	
					}
					 else {
							gamepanel.playSoundEffect(2);
							attacking = true;
						}
						
					 }
				}	
		 
		
		//MONSTER INTERACTION WITH PLAYER
		public void monsterInteraction(int i) {
			if(i != 999) {
				if (invincible == false) {
					life -= 1;
					invincible = true;
					gamepanel.playSoundEffect(3);
				}
			} 
			
			
		}
		
		//Monster damage to player
		public void monsterDamage(int i) {
			if (i != 999) {
				if (gamepanel.monster[gamepanel.currentMap][i].invincible == false) {
					gamepanel.monster[gamepanel.currentMap][i].life -=1;
					gamepanel.monster[gamepanel.currentMap][i].invincible = true;
					gamepanel.monster[gamepanel.currentMap][i].damageReact();
					
					if (gamepanel.monster[gamepanel.currentMap][i].life <= 0) {
						gamepanel.monster[gamepanel.currentMap][i].dying = true;
						
					}
					gamepanel.playSoundEffect(4);
				} 
			}
		}
	
		//Interaction object
		public void objectInteraction(int i) {
			
			if (i != 999) {
			
				if (gamepanel.object[gamepanel.currentMap][i].type == type_pickup) {
					gamepanel.object[gamepanel.currentMap][i].use(this);
					gamepanel.object[gamepanel.currentMap][i] = null;
					
				} else if (gamepanel.object[gamepanel.currentMap][i].type == type_door) {
					if (gamepanel.keyhandler.enter == true) {
						gamepanel.object[gamepanel.currentMap][i].interact();
						
					}
					
					
				} else {
					
					if (inventory.size() != maxinventorysize) {
						inventory.add(gamepanel.object[gamepanel.currentMap][i]);
						
					}
					gamepanel.object[gamepanel.currentMap][i] = null;
				} 
				
			}
		}
		
		public void selectItem() {
			int itemIndex = gamepanel.state.getItemIndexSlot();
			if (itemIndex < inventory.size()) {
				entityImage selectedItem = inventory.get(itemIndex);
				
				if (selectedItem.type == type_consumable) {
					if (selectedItem.use(this) == true) {
						inventory.remove(itemIndex);	
					}
					
				} 
			} 
		}
		
		
		public void draw(Graphics2D graphics2d) {
		    BufferedImage image = null;
		    int temporaryx = screenX;
		    int temporaryy = screenY;
	
		    	switch (directionString) {
				case "UP": 
					   if (attacking == false) {
			            	if (spriteNum == 1) {
			            		
			            		image = UP1;
				                
				            } else if (spriteNum == 2) {
				            	image = UP2;
				                
				            }
			            }
					   if (attacking == true) {
						   temporaryy = screenY - gamepanel.tileSize;
						   if (spriteNum == 1) {
				                image = attackup1;
				            } else if (spriteNum == 2) {
				                image = attackup2;
				            }
			            
					   }  
				break; 
				
				case "DOWN": 
					   if (attacking == false) {
			            	if (spriteNum == 1) {
				                image = DOWN1;
				            } else if (spriteNum == 2) {
				                image = DOWN2;
				            }
			            }
					   if (attacking == true) {
							if (spriteNum == 1) {
				                image = attackdown1;
				            } else if (spriteNum == 2) {
				                image = attackdown2;
				            }
			            
					   }  
				break; 
				case "LEFT":
					  if (attacking == false) {
			            	if (spriteNum == 1) {
				                image = LEFT1;
				            } else if (spriteNum == 2) {
				                image = LEFT2;
				            }
						}
			            
			            if (attacking == true) {
			            	temporaryx = screenX - gamepanel.tileSize;
			            	if (spriteNum == 1) {
				                image = attackleft1;
				            } else if (spriteNum == 2) {
				                image = attackleft2;
				            }
						}
			   break;
			   case "RIGHT":
				      if (attacking == false) {
			        	   if (spriteNum == 1) {
				                image = RIGHT1;
				            } else if (spriteNum == 2) {
				                image = RIGHT2;
				            }
				            else {
								image = STATIC;
								
							}
			           	}
			           if (attacking == true) {
			        	   if (spriteNum == 1) {
				                image = attackright1;
				            } else if (spriteNum == 2) {
				                image = attackright2;
				            } else {
								image = STATIC;
								
							}
			           	} 
			    break;
			    case "STATIC":
			    	image = STATIC; 
			    }
				
		    	if (invincible == true) {
		        graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		    }
		    graphics2d.drawImage(image, temporaryx, temporaryy, null);
		    graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}	
	}
		
	
