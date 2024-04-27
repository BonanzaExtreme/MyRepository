package objectfolder;



import javax.imageio.ImageIO;

import main.gamepanel;

public class Heart extends parentObject  {
	gamepanel gamepanel;
	public Heart(gamepanel gamepanel) {
		this.gamepanel = gamepanel;
		
		name = "Heart";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_empty.png"));
			image = utilTool.scaleImage(image, gamepanel.tileSize, gamepanel.tileSize);
			image2 = utilTool.scaleImage(image2, gamepanel.tileSize, gamepanel.tileSize);
			image3 = utilTool.scaleImage(image3, gamepanel.tileSize, gamepanel.tileSize);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
