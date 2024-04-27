package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyhandler implements KeyListener{
	
	gamepanel gamepanel;
	
	public boolean up, down, left, right;

	public keyhandler(gamepanel gamepanel) {
		this.gamepanel = gamepanel;

	}
	
		
	@Override
	public void keyTyped(KeyEvent e) {
	}


	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//Main menu
		if(gamepanel.gamestate == gamepanel.titlescreen){
			if(code == KeyEvent.VK_W) {
			gamepanel.state.commandNO--;
			if(gamepanel.state.commandNO < 0) {
				gamepanel.state.commandNO = 1;
				}
			}
			if(code == KeyEvent.VK_S) {
			gamepanel.state.commandNO++;
			if(gamepanel.state.commandNO > 1) {
				gamepanel.state.commandNO = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if (gamepanel.state.commandNO == 0) {
					gamepanel.gamestate = gamepanel.startstate;
				}  
				if (gamepanel.state.commandNO == 1) {
					System.exit(0)	;
				} 
			} 
		}	
		
		//Playing
		
		if(gamepanel.gamestate == gamepanel.startstate){
		if(code == KeyEvent.VK_W) {
			up = true;
		}

		if(code == KeyEvent.VK_S) {
			down = true;
		}

		if(code == KeyEvent.VK_A) {
			left = true;
		}

		if(code == KeyEvent.VK_D) {
			right = true;
			}
		}
		
		//Pause 
		if(gamepanel.gamestate == gamepanel.pausescreen) {
			
		}
		
		//Dialogue
		if (gamepanel.gamestate == gamepanel.dialogue) {
			if (code == KeyEvent.VK_ENTER) {
				gamepanel.gamestate = gamepanel.startstate;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			up = false;
		}

		if(code == KeyEvent.VK_S) {
			down = false;
		}

		if(code == KeyEvent.VK_A) {
			left = false;
		}

		if(code == KeyEvent.VK_D) {
			right = false;
		}
		
	}
	
}
