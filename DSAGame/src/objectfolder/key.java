package objectfolder;

import javax.imageio.ImageIO;

import main.gamepanel;

public class key extends parentObject {
	gamepanel gamepanel;
	public key (gamepanel gamepanel) {
		this.gamepanel = gamepanel;
		
		name = "key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			image = utilTool.scaleImage(image, gamepanel.tileSize, gamepanel.tileSize);

		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}

