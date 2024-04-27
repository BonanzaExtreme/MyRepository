package main;

import character.npc_1;
import monster.monster1;
import objectfolder.Heart;

public class Assetstarter {
	gamepanel gamepanel;
	
	
	public Assetstarter (gamepanel gamepanel) {
		this.gamepanel = gamepanel;

	}
	
	public void setObject() {
		
	}
	
	public void setNPC() {
		gamepanel.NPC[0] = new npc_1(gamepanel);
		gamepanel.NPC[0].Worldx = gamepanel.tileSize*23;
		gamepanel.NPC[0].Worldy = gamepanel.tileSize*17;
		
		
	} public void setMonster() {
		gamepanel.monster[0] = new monster1(gamepanel);
		gamepanel.monster[0].Worldx = gamepanel.tileSize*23;
		gamepanel.monster[0].Worldy = gamepanel.tileSize*30;
		
		gamepanel.monster[0] = new monster1(gamepanel);
		gamepanel.monster[0].Worldx = gamepanel.tileSize*27;
		gamepanel.monster[0].Worldy = gamepanel.tileSize*30;
	}
}
