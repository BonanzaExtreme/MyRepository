package main;

import javax.swing.JFrame;

public class Main {
	
		public static void main(String[] args) {
			
			
			JFrame windowFrame = new JFrame();
			windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			windowFrame.setResizable(false);
			windowFrame.setTitle("Adventurer's Maze");
			
			gamepanel gamepanel = new gamepanel();
			windowFrame.add(gamepanel);
			
			windowFrame.pack();
			
			windowFrame.setLocationRelativeTo(null);
			windowFrame.setVisible(true);
			
			gamepanel.setUp();
			gamepanel.gameThread();
			
		}
}
