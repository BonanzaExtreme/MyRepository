package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

import character.entityImage;
import character.npc_1;
import character.player;
import objectfolder.parentObject;
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
	public final int maxMap = 10; 
	public final int currentMap = 0; 
	int fps = 60; 
	
	//World
	public int maxWorldColumns;
	public int maxWorldRow;
	public final int worldWidth = tileSize * maxWorldColumns;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	
	//initialize
	tilemanager tilemanager = new tilemanager(this); 
	keyhandler keyhandler = new keyhandler(this);
	Thread gameThread;
	public state state = new state(this); 
	public Assetstarter objectsetter = new Assetstarter(this);
	public collisionChecker collisionCheck = new collisionChecker(this);
	public cutsceneManage sceneManager  = new cutsceneManage(this);
	
	//Sounds
	sounds sounds = new sounds();
	
	//Entity
	public player player = new player(this,keyhandler);

	
	//Object
	public parentObject object[] = new parentObject[10];
	
	//NPC ARRAY
	public entityImage NPC[] = new entityImage[5];	
	
	//Monster Array
	public entityImage monster[] = new entityImage[10];
	

	//Game State
	public int gamestate;
	public final int titlescreen = 0;
	public final int pausescreen = 1;
	public final int startstate = 2;
	public final int dialogue = 3; 

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
		objectsetter.setMonster();
		objectsetter.setNPC();
		gamestate = titlescreen; 
		playKanta(0);
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
		for(int i = 0; i < NPC.length; i++) {
			if (NPC[i] != null) {
				NPC[i].update();
			}
		} 
		//Monster
		for (int i = 0; i < monster.length; i++) {
			if (monster[i] != null) {
				monster[i].update();
				} 
			}
		}	
		if (gamestate == pausescreen) {
			//wala pa
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
			kantastop();
			//Object
			for(int i = 0; i < object.length; i++) {
				if(object[i] != null) {
					object[i].draw(graphics2d, this);
				}
			}
			
			//NPC DRAW
			for(int i = 0; i < NPC.length; i++) {
				if (NPC[i] != null) {
					NPC[i].draw(graphics2d);
				}
			}
			
			//Monster Draw
			for(int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					monster[i].draw(graphics2d);
				}
			}
			
			//player
			player.paintComponent(graphics2d);
			state.paintComponent(graphics2d);
			
		}
		
		graphics2d.dispose();
	} 
		public void playKanta(int i) {
			sounds.setFile(i);
			sounds.play();
			sounds.loop();
	
	}	public void kantastop() {
		   sounds.stop();
		}
	}
