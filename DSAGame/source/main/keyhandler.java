package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.PublicKey;

public class keyhandler implements KeyListener{
	
	gamepanel gamepanel;
	
	  private static final int DELAY_MS = 120; // Delay in milliseconds
	    private long lastKeyPressTime = 0;
	public boolean up, down, left, right, enter, f;



	public keyhandler(gamepanel gamepanel) {
		this.gamepanel = gamepanel;

	}
	
		
	@Override
	public void keyTyped(KeyEvent e) {
	}


	
	
	@Override
	public void keyPressed(KeyEvent e) {
		 	long currentTime = System.currentTimeMillis();
	        if (currentTime - lastKeyPressTime < DELAY_MS) {
	            return;
	        }
	        lastKeyPressTime = currentTime;
		
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
					gamepanel.kantastop(0);
					
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
		
		if(code == KeyEvent.VK_F) {
			f = true;
			gamepanel.gamestate = gamepanel.characterstate;
			
			}
		if(code == KeyEvent.VK_ENTER) {
			enter = true;
		
		}

		}
		
		if (gamepanel.gamestate == gamepanel.characterstate) {
			if (code == KeyEvent.VK_G) {
				gamepanel.gamestate = gamepanel.startstate;
			}
			if (code == KeyEvent.VK_W) {
				if (gamepanel.state.slotrow != 0) {
					gamepanel.state.slotrow --;
				}
			
			}
			if (code == KeyEvent.VK_A) {
				if (gamepanel.state.slotcol != 0) {
					gamepanel.state.slotcol --;
			}
			}
			if (code == KeyEvent.VK_S) {
				if (gamepanel.state.slotrow != 4) {
					gamepanel.state.slotrow ++;
			}
			}
			if (code == KeyEvent.VK_D) {
				if (gamepanel.state.slotcol != 2) {
					gamepanel.state.slotcol ++;
			}		
			
			
			}
			if (code == KeyEvent.VK_1) {
				gamepanel.player.selectItem();
			}
		}	
		
		
		//Pause 
		if(gamepanel.gamestate == gamepanel.pausescreen) {
			
		}
		
		//Dialogue
		if (gamepanel.gamestate == gamepanel.dialogue) {
			if (code == KeyEvent.VK_ENTER) {
				enter = true;
				gamepanel.gamestate = gamepanel.startstate;
			} enter = false;
		} 
		
		//GAME OVER
		if (gamepanel.gamestate == gamepanel.gameover) {
			
			if (code == KeyEvent.VK_W) {
				gamepanel.state.commandNO --;
				if (gamepanel.state.commandNO < 0) {
					gamepanel.state.commandNO = 1;
				}
				
			}
			if (code == KeyEvent.VK_S) {
				gamepanel.state.commandNO ++;
				if (gamepanel.state.commandNO > 1) {
					gamepanel.state.commandNO = 0;
				}
			
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gamepanel.state.commandNO == 0) {
					gamepanel.gamestate = gamepanel.startstate;
					gamepanel.retry();
					gamepanel.kantastop(1);
					
					
				} else if (gamepanel.state.commandNO == 1) {
					gamepanel.gamestate = gamepanel.titlescreen;
					gamepanel.playKanta(0);
					gamepanel.retry();
					
				}
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
