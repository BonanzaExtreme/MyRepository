package tiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;



import javax.imageio.ImageIO;

import main.gamepanel;

public class tilemanager {
	
		gamepanel gamepanel; 
		public tile[] tile;
		public int maptileno[][];
		
		
		public tilemanager(gamepanel gamepanel) {
			this.gamepanel = gamepanel; 
			tile = new tile[10];
		    maptileno = new int[gamepanel.maxWorldColumns][gamepanel.maxWorldRow];
			
			gettileImage();
			loadmap("/maps/map01.txt"); 
		}
		
		public void gettileImage() {
			try {
				
				tile[0] = new tile();
				tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Black.png"));
			
				tile[1] = new tile();
				tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile_bottomleftmost.png"));
				
				
				tile[2] = new tile();
				tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_left.png"));
				tile[2].collision = true;
				
				tile[3] = new tile();
				tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walltop1.png"));
				tile[3].collision = true;
				
				tile[4] = new tile();
				tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walltop2.png"));
				tile[4].collision = true;
				
				tile[5] = new tile();
				tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_right.png"));
				tile[5].collision = true;
				
				tile[6] = new tile();
				tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wallbottom.png"));
				
				tile[7] = new tile();
				tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walledge.png"));
				
				tile[8] = new tile();
				tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walledgeright.png"));
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void loadmap(String mapfilePath) {
			try {
				
				InputStream inputStream = getClass().getResourceAsStream(mapfilePath);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				
				int column = 0;
				int row = 0; 
				
				while(column < gamepanel.maxWorldColumns && row < gamepanel.maxWorldRow) {
					
					String line = bufferedReader.readLine();
					
					while (column < gamepanel.maxWorldColumns ) {
						String numbers[] = line.split(" ");
						
						int number = Integer.parseInt(numbers[column]);
						
						maptileno[column][row] = number;
						column++;
					} 
					if (column == gamepanel.maxWorldColumns) {
						column = 0;
						row++;
					}
				} bufferedReader.close(); 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void draw(Graphics2D graphics2d) {
			int worldColumn = 0;
			int worldRow = 0;
			
			while(worldColumn < gamepanel.maxWorldColumns && worldRow < gamepanel.maxWorldRow) {
				
				int tilenumber = maptileno[worldColumn][worldRow];
				
				int worldX =  worldColumn * gamepanel.tileSize; 
				int worldY =  worldRow * gamepanel.tileSize; 
				int screenX = worldX - gamepanel.player.Worldx + gamepanel.player.screenX;
				int screenY = worldY - gamepanel.player.Worldy + gamepanel.player.screenY;
				
				
				graphics2d.drawImage(tile[tilenumber].image, screenX, screenY, gamepanel.tileSize, gamepanel.tileSize, null);
				worldColumn++;

				
				if(worldColumn == gamepanel.maxWorldColumns) {
					worldColumn = 0; 
					worldRow++;
					
				}
			}
		}
}
