package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.text.html.parser.Entity;

import Pathfinding.Pathfinder;
import character.entityImage;
import character.npc_1;
import character.player;
import environment.environmentManage;
import tiles.tilemanager;

public class gamepanel extends JPanel implements Runnable{
	
	//Panel Settings
	final int origTileSize = 16; //16 pixels size ng characters/tiles
	final int scale = 3;
	public final int tileSize = origTileSize * scale; //48x48 tile
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 15;
	public final int screenWidth = tileSize * maxScreenCol; //960 by 720
	public final int screenHeight = tileSize * maxScreenRow; 
 
	int fps = 60; 
	
	//MAPS
	public final int maxMap = 10; 
	public int currentMap = 0;	
	
	//World
	public int maxWorldColumns = 999;
	public int maxWorldRow = 999;
	public final int worldWidth = tileSize * maxWorldColumns;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//Environment
	environmentManage eManage = new environmentManage(this);
	
	//Event
	public EventRect eventRect = new EventRect(this);
	
	//initialize
	public tilemanager tilemanager = new tilemanager(this); 
	public keyhandler keyhandler = new keyhandler(this);
	Thread gameThread;
	public state state = new state(this); 
	public ResourceStarter objectsetter = new ResourceStarter(this);
	public collisionChecker collisionCheck = new collisionChecker(this);
	public cutsceneManage sceneManager  = new cutsceneManage(this);
	
	//Pathfinding
	public Pathfinder pathfinder = new Pathfinder(this);
	
	//Sounds
	sounds sounds = new sounds();
	//Entity
	public player player = new player(this,keyhandler);
	
	
	//ENTITY ARRAY
	ArrayList<entityImage> entitylistArrayList = new ArrayList<entityImage>();
	//Object
	public entityImage object[][] = new entityImage[maxMap][100];
	//NPC ARRAY
	public entityImage NPC[][] = new entityImage[maxMap][5];	
	//Monster Array
	public entityImage monster[][] = new entityImage[maxMap][20];
	

	//Game State
	public int gamestate;
	public final int titlescreen = 0;
	public final int pausescreen = 1;
	public final int startstate = 2;
	public final int dialogue = 3; 
	public final int gameover = 4; 

	//panel
	public gamepanel(){
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyhandler);
		this.setFocusable(true);
	}
		
	public void setUp() {
		
		objectsetter.setObject();
		objectsetter.setNPC();
		objectsetter.setMonster();
		gamestate = titlescreen; 
		eManage.setup();
		playKanta(0);
	}
	
	public void retry() {
		player.defaultvalue();
		objectsetter.setObject();
		objectsetter.setNPC();
		objectsetter.setMonster();
	}
	
	
	

	public void gameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	
	//core game loop 
	public void run() {
		
		double interval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentOras;

		while(gameThread != null) {
		
			currentOras = System.nanoTime();
			
			delta += (currentOras - lastTime)/interval;
			lastTime = currentOras; 
			
			if (delta >= 1) {
				update();
				repaint();
				delta --;
			}
		}
	}
	//
	
	
	public void update() {
		
		if(gamestate == startstate) {
			player.update();
		
		//NPC
		for(int i = 0; i < NPC[1].length; i++) {
			if (NPC[currentMap][i] != null) {
				NPC[currentMap][i].update();
			}
		} 
		//Monster
		for (int i = 0; i < monster[1].length; i++) {
			if (monster[currentMap][i] != null) {
				if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
					monster[currentMap][i].update();
				}
				if (monster[currentMap][i].alive == false) {
					monster[currentMap][i] = null;
				}
			}
		}	
		
		if (gamestate == pausescreen) {
			//wala pa
			}
		}
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D graphics2d = (Graphics2D)g;
		
		//Title Screen
		if(gamestate == titlescreen) {
			state.paintComponent(graphics2d);
		} 
		 else {
			
			tilemanager.draw(graphics2d);
		
			//ADD ENTITIES TO THE LIST
			
			//Player
			entitylistArrayList.add(player);
			//NPC
			for (int i = 0; i < NPC[1].length; i++) {
				if (NPC[currentMap][i] != null) {
					entitylistArrayList.add(NPC[currentMap][i]);
				}
			}
			
			//MONSTER
			for (int i = 0; i < monster[1].length; i++) {
				if (monster[currentMap][i] != null) {
					entitylistArrayList.add(monster[currentMap][i]);
				}
			}
			
			//Objects
			for (int i = 0; i < object[1].length; i++) {
				if (object[currentMap][i] != null) {
					entitylistArrayList.add(object[currentMap][i]);
				}
			}
			
			// SORTING
			Collections.sort(entitylistArrayList, new Comparator<entityImage>() {

				@Override
				public int compare(entityImage o1, entityImage o2) {
					int result = Integer.compare(o1.Worldy, o2.Worldy);
					return result;
				}

			});
			
			//Printing entities on the screen
			for (int i = 0; i < entitylistArrayList.size(); i++) {
				entitylistArrayList.get(i).draw(graphics2d);
			}
			
			//Emptying entityArrayLIST
			entitylistArrayList.clear();
			
			eManage.draw(graphics2d);
		}
	
		//STATE
		state.paintComponent(graphics2d);
	} 
	
	
	
	
		public void playKanta(int i) {
			sounds.setFile(i);
			sounds.play();
			sounds.loop();
	
	}	public void kantastop() {
		   sounds.stop();
		}
	
		public void playSoundEffect(int i) {
			sounds.setFile(i);
			sounds.play();
		}
	
	
	}
