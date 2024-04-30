package character;

import java.awt.image.BufferedImage;
import java.security.PublicKey;
import java.util.Random;

import main.gamepanel;
import main.keyhandler;

public class npc_1 extends entityImage {
	keyhandler keyhandler; 
	
	public npc_1(gamepanel gamepanel) {
		
		super(gamepanel);
	
		
		directionString = "STATIC";
		speed = 1; 
		
		getNPC1Image();
		dialogue();
	}
	public void getNPC1Image() {
			
			UP1 = setup("/NPCpicture/Character_UP1", gamepanel.tileSize, gamepanel.tileSize);
			UP2 = setup("/NPCpicture/Character_UP2", gamepanel.tileSize, gamepanel.tileSize);
			DOWN1 = setup("/NPCpicture/Character_down1", gamepanel.tileSize, gamepanel.tileSize);
			DOWN2 = setup("/NPCpicture/Character_down2", gamepanel.tileSize, gamepanel.tileSize);
			RIGHT1= setup("/NPCpicture/Character_RIGHT1", gamepanel.tileSize, gamepanel.tileSize);
			RIGHT2 = setup("/NPCpicture/Character_RIGHT2", gamepanel.tileSize, gamepanel.tileSize);
			LEFT1 = setup("/NPCpicture/Character_LEFT1", gamepanel.tileSize, gamepanel.tileSize);
			LEFT2 = setup("/NPCpicture/Character_LEFT2", gamepanel.tileSize, gamepanel.tileSize);
			STATIC = setup("/NPCpicture/Character", gamepanel.tileSize, gamepanel.tileSize);
		}
		
		public void dialogue() {
			dialogues[0] = "Greetings, adventurer, and welcome to the endless \nlabyrinth that has become my home. ";
			dialogues[1] = "I have dwelled within these halls for what feels like an \neternity,watching the passage fade into the shadows. \nYet, your arrival kindles a glimmer of hope that perhaps, \nthis solitary existence may soon come to an end."; 
			dialogues[2] = "Tread carefully, for the trials that lie ahead are many, \nbut know that you are not alone in this journey.";
		}
	
		public void setAction() {
				actionCounter ++;
				if (actionCounter == 120) {
					
					Random rando = new Random();
					int i = rando.nextInt(100)+1; 
					
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

		public void speak() {
			if (dialogues[dialogueindex] == null) {
				dialogueindex = 0;
			}
			gamepanel.state.currentDialogueString = dialogues[dialogueindex];
			dialogueindex++;
			
			if (gamepanel.player.directionString == "UP") {
				directionString = "DOWN"; 
			} else if (gamepanel.player.directionString == "DOWN") {
				directionString = "UP";
			} else if (gamepanel.player.directionString == "LEFT") {
				directionString = "RIGHT";
			} else if (gamepanel.player.directionString == "RIGHT") {
				directionString = "LEFT";
			} 
			
		}
	}


