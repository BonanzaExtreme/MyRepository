package environment;

import java.awt.Graphics2D;

import main.gamepanel;

public class environmentManage {
	gamepanel gamepanel;
	light light;
	
	public environmentManage(gamepanel gamepanel) {
		this.gamepanel = gamepanel;
	}
	
	public void setup() {
		light = new light(gamepanel, 360);
	}
	
	public void draw(Graphics2D graphics2d) {
		light.draw(graphics2d);
	}
}
