package main;

import character.entityImage;
import character.npc_1;
import character.player;
import monster.boss1;
import monster.boss2;
import monster.monster1;
import monster.monster2;
import objectfolder.Door;
import objectfolder.Door2;
import objectfolder.Heart;
import objectfolder.chest;
import objectfolder.chest_design;
import objectfolder.doorside;
import objectfolder.key;
import objectfolder.potion;
import objectfolder.skele;
import objectfolder.treasure;

public class ResourceStarter {
	gamepanel gamepanel;
	
	
	public ResourceStarter (gamepanel gamepanel) {
		this.gamepanel = gamepanel;
		
		

	}
	
	public void setObject() {
		
		int mapNumber = 0;
		
		gamepanel.object[mapNumber][0] = new Door(gamepanel);
		gamepanel.object[mapNumber][0].Worldx = gamepanel.tileSize*7;
		gamepanel.object[mapNumber][0].Worldy = gamepanel.tileSize*33;
		
		
		gamepanel.object[mapNumber][1] = new chest(gamepanel, new potion(gamepanel));
		gamepanel.object[mapNumber][1].Worldx = gamepanel.tileSize*24;
		gamepanel.object[mapNumber][1].Worldy = gamepanel.tileSize*20;
		
		gamepanel.object[mapNumber][2] = new chest(gamepanel, new key(gamepanel));
		gamepanel.object[mapNumber][2].Worldx = gamepanel.tileSize*35;
		gamepanel.object[mapNumber][2].Worldy = gamepanel.tileSize*27;
		

	
		
		
		//CHEST DESIGN NOT INTERACTIVE
		gamepanel.object[mapNumber][3] = new chest_design(gamepanel);
		gamepanel.object[mapNumber][3].Worldx = gamepanel.tileSize*23;
		gamepanel.object[mapNumber][3].Worldy = gamepanel.tileSize*20;
		
		gamepanel.object[mapNumber][4] = new chest_design(gamepanel);
		gamepanel.object[mapNumber][4].Worldx = gamepanel.tileSize*23;
		gamepanel.object[mapNumber][4].Worldy = gamepanel.tileSize*27;
		
		gamepanel.object[mapNumber][5] = new chest_design(gamepanel);
		gamepanel.object[mapNumber][5].Worldx = gamepanel.tileSize*21;
		gamepanel.object[mapNumber][5].Worldy = gamepanel.tileSize*39;
		
		gamepanel.object[mapNumber][6] = new chest_design(gamepanel);
		gamepanel.object[mapNumber][6].Worldx = gamepanel.tileSize*8;
		gamepanel.object[mapNumber][6].Worldy = gamepanel.tileSize*36;
		
		gamepanel.object[mapNumber][7] = new skele(gamepanel);
		gamepanel.object[mapNumber][7].Worldx = gamepanel.tileSize*27;
		gamepanel.object[mapNumber][7].Worldy = gamepanel.tileSize*20;
		
		
		gamepanel.object[mapNumber][8] = new skele(gamepanel);
		gamepanel.object[mapNumber][8].Worldx = gamepanel.tileSize*2;
		gamepanel.object[mapNumber][8].Worldy = gamepanel.tileSize*36;
		
		gamepanel.object[mapNumber][9] = new skele(gamepanel);
		gamepanel.object[mapNumber][9].Worldx = gamepanel.tileSize*4;
		gamepanel.object[mapNumber][9].Worldy = gamepanel.tileSize*42;
		
		gamepanel.object[mapNumber][10] = new skele(gamepanel);
		gamepanel.object[mapNumber][10].Worldx = gamepanel.tileSize*33;
		gamepanel.object[mapNumber][10].Worldy = gamepanel.tileSize*37;
		
		gamepanel.object[mapNumber][16] = new Door(gamepanel);
		gamepanel.object[mapNumber][16].Worldx = gamepanel.tileSize*7;
		gamepanel.object[mapNumber][16].Worldy = gamepanel.tileSize*48;

		
		
		
		mapNumber = 1;
		gamepanel.object[mapNumber][11] = new Door2(gamepanel);
		gamepanel.object[mapNumber][11].Worldx = gamepanel.tileSize*22;
		gamepanel.object[mapNumber][11].Worldy = gamepanel.tileSize*14;
		
		gamepanel.object[mapNumber][12] = new chest_design(gamepanel);
		gamepanel.object[mapNumber][12].Worldx = gamepanel.tileSize*24;
		gamepanel.object[mapNumber][12].Worldy = gamepanel.tileSize*13;
		
		gamepanel.object[mapNumber][13] = new skele(gamepanel);
		gamepanel.object[mapNumber][13].Worldx = gamepanel.tileSize*11;
		gamepanel.object[mapNumber][13].Worldy = gamepanel.tileSize*16;
		
		gamepanel.object[mapNumber][14] = new skele(gamepanel);
		gamepanel.object[mapNumber][14].Worldx = gamepanel.tileSize*15;
		gamepanel.object[mapNumber][14].Worldy = gamepanel.tileSize*21;
		
		gamepanel.object[mapNumber][15] = new skele(gamepanel);
		gamepanel.object[mapNumber][15].Worldx = gamepanel.tileSize*9;
		gamepanel.object[mapNumber][15].Worldy = gamepanel.tileSize*26;
		
		gamepanel.object[mapNumber][17] = new chest_design(gamepanel);
		gamepanel.object[mapNumber][17].Worldx = gamepanel.tileSize*26;
		gamepanel.object[mapNumber][17].Worldy = gamepanel.tileSize*24;
		
		gamepanel.object[mapNumber][18] = new skele(gamepanel);
		gamepanel.object[mapNumber][18].Worldx = gamepanel.tileSize*34;
		gamepanel.object[mapNumber][18].Worldy = gamepanel.tileSize*26;
		
		gamepanel.object[mapNumber][19] = new skele(gamepanel);
		gamepanel.object[mapNumber][19].Worldx = gamepanel.tileSize*22;
		gamepanel.object[mapNumber][19].Worldy = gamepanel.tileSize*30;
		
		gamepanel.object[mapNumber][20] = new skele(gamepanel);
		gamepanel.object[mapNumber][20].Worldx = gamepanel.tileSize*19;
		gamepanel.object[mapNumber][20].Worldy = gamepanel.tileSize*34;
		

		gamepanel.object[mapNumber][21] = new Door(gamepanel);
		gamepanel.object[mapNumber][21].Worldx = gamepanel.tileSize*30;
		gamepanel.object[mapNumber][21].Worldy = gamepanel.tileSize*33;

		
		gamepanel.object[mapNumber][22] = new chest(gamepanel, new key(gamepanel));
		gamepanel.object[mapNumber][22].Worldx = gamepanel.tileSize*10;
		gamepanel.object[mapNumber][22].Worldy = gamepanel.tileSize*26;
		
		
		mapNumber = 3;
		
		gamepanel.object[mapNumber][23] = new treasure(gamepanel);
		gamepanel.object[mapNumber][23].Worldx = gamepanel.tileSize*25;
		gamepanel.object[mapNumber][23].Worldy = gamepanel.tileSize*11;
		
		mapNumber = 2;
		
		gamepanel.object[mapNumber][24] = new Door(gamepanel);
		gamepanel.object[mapNumber][24].Worldx = gamepanel.tileSize*16;
		gamepanel.object[mapNumber][24].Worldy = gamepanel.tileSize*2;
		
		gamepanel.object[mapNumber][25] = new chest(gamepanel, new potion(gamepanel));
		gamepanel.object[mapNumber][25].Worldx = gamepanel.tileSize*2;
		gamepanel.object[mapNumber][25].Worldy = gamepanel.tileSize*34;
		
		gamepanel.object[mapNumber][26] = new chest(gamepanel, new potion(gamepanel));
		gamepanel.object[mapNumber][26].Worldx = gamepanel.tileSize*8;
		gamepanel.object[mapNumber][26].Worldy = gamepanel.tileSize*38;
		
		gamepanel.object[mapNumber][27] = new chest(gamepanel, new potion(gamepanel));
		gamepanel.object[mapNumber][27].Worldx = gamepanel.tileSize*41;
		gamepanel.object[mapNumber][27].Worldy = gamepanel.tileSize*43;
		
		gamepanel.object[mapNumber][28] = new chest_design(gamepanel);
		gamepanel.object[mapNumber][28].Worldx = gamepanel.tileSize*32;
		gamepanel.object[mapNumber][28].Worldy = gamepanel.tileSize*17;

		gamepanel.object[mapNumber][29] = new skele(gamepanel);
		gamepanel.object[mapNumber][29].Worldx = gamepanel.tileSize*3;
		gamepanel.object[mapNumber][29].Worldy = gamepanel.tileSize*14;
		
		gamepanel.object[mapNumber][30] = new skele(gamepanel);
		gamepanel.object[mapNumber][30].Worldx = gamepanel.tileSize*10;
		gamepanel.object[mapNumber][30].Worldy = gamepanel.tileSize*41;

	
	}
	
	public void setNPC() {
		int mapNumber = 0;
		
		gamepanel.NPC[mapNumber][0] = new npc_1(gamepanel);
		gamepanel.NPC[mapNumber][0].Worldx = gamepanel.tileSize*26;
		gamepanel.NPC[mapNumber][0].Worldy = gamepanel.tileSize*15;
		
		
	} public void setMonster() {
		int mapNumber = 0;
		
		//LEVEL 1 monsters
		gamepanel.monster[mapNumber][0] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][0].Worldx = gamepanel.tileSize*27;
		gamepanel.monster[mapNumber][0].Worldy = gamepanel.tileSize*23;
		
		gamepanel.monster[mapNumber][1] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][1].Worldx = gamepanel.tileSize* 24;
		gamepanel.monster[mapNumber][1].Worldy = gamepanel.tileSize* 21;
		
		gamepanel.monster[mapNumber][2] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][2].Worldx = gamepanel.tileSize*23;
		gamepanel.monster[mapNumber][2].Worldy = gamepanel.tileSize*24;
		
		gamepanel.monster[mapNumber][3] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][3].Worldx = gamepanel.tileSize*32;
		gamepanel.monster[mapNumber][3].Worldy = gamepanel.tileSize*27;
		
		gamepanel.monster[mapNumber][4] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][4].Worldx = gamepanel.tileSize*34;
		gamepanel.monster[mapNumber][4].Worldy = gamepanel.tileSize*27;

		gamepanel.monster[mapNumber][5] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][5].Worldx = gamepanel.tileSize*34;
		gamepanel.monster[mapNumber][5].Worldy = gamepanel.tileSize*28;
		
		gamepanel.monster[mapNumber][6] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][6].Worldx = gamepanel.tileSize*21;
		gamepanel.monster[mapNumber][6].Worldy = gamepanel.tileSize*38;

		
		//LEVEL 2 monsters
		mapNumber = 1;
		gamepanel.monster[mapNumber][7] = new monster2(gamepanel);
		gamepanel.monster[mapNumber][7].Worldx = gamepanel.tileSize*10;
		gamepanel.monster[mapNumber][7].Worldy = gamepanel.tileSize*18;

		gamepanel.monster[mapNumber][8] = new monster2(gamepanel);
		gamepanel.monster[mapNumber][8].Worldx = gamepanel.tileSize*14;
		gamepanel.monster[mapNumber][8].Worldy = gamepanel.tileSize*15;
	
		
		gamepanel.monster[mapNumber][10] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][10].Worldx = gamepanel.tileSize*12;
		gamepanel.monster[mapNumber][10].Worldy = gamepanel.tileSize*18;
		
		gamepanel.monster[mapNumber][11] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][11].Worldx = gamepanel.tileSize*20;
		gamepanel.monster[mapNumber][11].Worldy = gamepanel.tileSize*12;
	
		
		gamepanel.monster[mapNumber][12] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][12].Worldx = gamepanel.tileSize*26;
		gamepanel.monster[mapNumber][12].Worldy = gamepanel.tileSize*16;
		
		mapNumber = 0;
		gamepanel.monster[mapNumber][13] = new boss1(gamepanel);
		gamepanel.monster[mapNumber][13].Worldx = gamepanel.tileSize*4;
		gamepanel.monster[mapNumber][13].Worldy = gamepanel.tileSize*44;
		
		mapNumber = 1;
		gamepanel.monster[mapNumber][14] = new boss2(gamepanel);
		gamepanel.monster[mapNumber][14].Worldx = gamepanel.tileSize*28;
		gamepanel.monster[mapNumber][14].Worldy = gamepanel.tileSize*29;
		
		mapNumber = 2;
		gamepanel.monster[mapNumber][15] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][15].Worldx = gamepanel.tileSize*5;
		gamepanel.monster[mapNumber][15].Worldy = gamepanel.tileSize*12;
		
		gamepanel.monster[mapNumber][16] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][16].Worldx = gamepanel.tileSize*9;
		gamepanel.monster[mapNumber][16].Worldy = gamepanel.tileSize*12;
		
		gamepanel.monster[mapNumber][17] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][17].Worldx = gamepanel.tileSize*11;
		gamepanel.monster[mapNumber][17].Worldy = gamepanel.tileSize*16;
		
		gamepanel.monster[mapNumber][18] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][18].Worldx = gamepanel.tileSize*25;
		gamepanel.monster[mapNumber][18].Worldy = gamepanel.tileSize*17;
		
		gamepanel.monster[mapNumber][19] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][19].Worldx = gamepanel.tileSize*16;
		gamepanel.monster[mapNumber][19].Worldy = gamepanel.tileSize*8;
		
		gamepanel.monster[mapNumber][20] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][20].Worldx = gamepanel.tileSize*16;
		gamepanel.monster[mapNumber][20].Worldy = gamepanel.tileSize*20;
		
		gamepanel.monster[mapNumber][21] = new monster2(gamepanel);
		gamepanel.monster[mapNumber][21].Worldx = gamepanel.tileSize*9;
		gamepanel.monster[mapNumber][21].Worldy = gamepanel.tileSize*42;
		
		gamepanel.monster[mapNumber][22] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][22].Worldx = gamepanel.tileSize*2;
		gamepanel.monster[mapNumber][22].Worldy = gamepanel.tileSize*42;
		
		gamepanel.monster[mapNumber][23] = new monster2(gamepanel);
		gamepanel.monster[mapNumber][23].Worldx = gamepanel.tileSize*29;
		gamepanel.monster[mapNumber][23].Worldy = gamepanel.tileSize*42;
		
		gamepanel.monster[mapNumber][24] = new boss2(gamepanel);
		gamepanel.monster[mapNumber][24].Worldx = gamepanel.tileSize*16;
		gamepanel.monster[mapNumber][24].Worldy = gamepanel.tileSize*13;
		
		gamepanel.monster[mapNumber][25] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][25].Worldx = gamepanel.tileSize*19;
		gamepanel.monster[mapNumber][25].Worldy = gamepanel.tileSize*44;
		
		gamepanel.monster[mapNumber][26] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][26].Worldx = gamepanel.tileSize*20;
		gamepanel.monster[mapNumber][26].Worldy = gamepanel.tileSize*44;
		
		gamepanel.monster[mapNumber][27] = new monster1(gamepanel);
		gamepanel.monster[mapNumber][27].Worldx = gamepanel.tileSize*19;
		gamepanel.monster[mapNumber][27].Worldy = gamepanel.tileSize*43;
		
	}
	
}
