package tiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;

import main.gamepanel;

public class tilemanager {
	
		gamepanel gamepanel; 
		tile[] tile;
		int maptileno[][];
		
		
		public tilemanager(gamepanel gamepanel) {
			this.gamepanel = gamepanel; 
			tile = new tile[10];
			maptileno = new int[gamepanel.maxScreenCol][gamepanel.maxScreenRow];
			
			gettileImage();
			loadmap(); 
		}
		
		public void gettileImage() {
			try {
				
				tile[0] = new tile();
				tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile_bottomleftmost.png"));
				tile[1] = new tile();
				tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_left.png"));
				tile[2] = new tile();
				tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walltop1.png"));
				tile[3] = new tile();
				tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walltop2.png"));
				tile[4] = new tile();
				tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_right.png"));
				tile[5] = new tile();
				tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wallbottom.png"));
				tile[6] = new tile();
				tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/walledge.png"));
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void loadmap() {
			try {
				
				InputStream inputStream = getClass().getResourceAsStream("/maps/map01.txt");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				
				int column = 0;
				int row = 0; 
				
				while(column < gamepanel.maxScreenCol && row < gamepanel.maxScreenRow) {
					
					String line = bufferedReader.readLine();
					
					while (column < gamepanel.maxScreenCol ) {
						String numbers[] = line.split(" ");
						
						int number = Integer.parseInt(numbers[column]);
						
						maptileno[column][row] = number;
						column++;
					} 
					if (column == gamepanel.maxScreenCol) {
						column = 0;
						row++;
					}
				} bufferedReader.close(); 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void draw(Graphics2D graphics2d) {
			int column = 0;
			int row = 0; 
			int x = 0;
			int y = 0; 
			
			while(column < gamepanel.maxScreenCol && row < gamepanel.maxScreenRow) {
				
				int tilenumber = maptileno[column][row];
				
				graphics2d.drawImage(tile[tilenumber].image, x, y, gamepanel.tileSize, gamepanel.tileSize, null);
				column++;
				x += gamepanel.tileSize;
				
				if(column == gamepanel.maxScreenCol) {
					column = 0;
					x = 0;
					row++;
					y += gamepanel.tileSize;
				}
			}
		}
}
