package objectfolder;

import javax.imageio.ImageIO;

import main.gamepanel;

public class chest extends parentObject {
	gamepanel gamepanel;
	public chest (gamepanel gamepanel) {
		this.gamepanel = gamepanel;
		
		name = "chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			image = utilTool.scaleImage(image, gamepanel.tileSize, gamepanel.tileSize);

		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}