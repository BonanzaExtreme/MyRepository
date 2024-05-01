package tiles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.Graphics2D;



import javax.imageio.ImageIO;

import main.TOOL;
import main.gamepanel;

public class tilemanager {
	
		gamepanel gamepanel; 
		public tile[] tile;
		public int maptileno[][][];
		boolean drawPath = false;
		ArrayList<String> fileNameArrayList = new ArrayList<String>();
		ArrayList<String> collisionStatuStrings = new ArrayList<String>();
		
		
		public tilemanager(gamepanel gamepanel) {
			this.gamepanel = gamepanel; 
			
			InputStream is = getClass().getResourceAsStream("/maps/tiledata.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String line; 
			
			try {
				while ((line = br.readLine()) != null) {
					fileNameArrayList.add(line);
					collisionStatuStrings.add(br.readLine());
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			tile = new tile[fileNameArrayList.size()];
		    gettileImage();
		    
		    is = getClass().getResourceAsStream("/maps/MAP1.txt");
		    br = new BufferedReader(new InputStreamReader(is));
		    
		    try {
				String lineString = br.readLine();
				String maxTile[] = lineString.split(" ");
				
				gamepanel.maxWorldColumns = maxTile.length;
				gamepanel.maxWorldRow = maxTile.length;
				maptileno = new int[gamepanel.maxMap][gamepanel.maxWorldRow][gamepanel.maxWorldRow];
				
				br.close();
				
			} catch (Exception e) {
				System.out.println("di nagana");
			}
		
		    loadmap("/maps/MAP1.txt", 0);
		    loadmap("/maps/map2.txt", 1);
		    loadmap("/maps/map4.txt", 2);
			
		}
		
		public void gettileImage() {
			for(int i = 0; i < fileNameArrayList.size(); i++) {
				String filename;
				boolean collision; 
				
				filename = fileNameArrayList.get(i);
				
				if (collisionStatuStrings.get(i).equals("true")) {
					collision = true;
				} else {
					collision = false;
					}
				setup (i, filename, collision);
				}
			}
		
		public void setup(int index, String imagename, boolean collision) {
			TOOL utiltool = new TOOL();
			
			try {
				tile[index] = new tile();
				tile[index].image = ImageIO.read(getClass().getResource("/tiles/" + imagename));
				tile[index].image = utiltool.scaleImage(tile[index].image, gamepanel.tileSize, gamepanel.tileSize);
				tile[index].collision = collision; 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void loadmap(String mapfilePath, int map) {
			try {
				
				InputStream inputStream = getClass().getResourceAsStream(mapfilePath);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				
				int col = 0;
				int row = 0; 
				
				while(col < gamepanel.maxWorldColumns && row < gamepanel.maxWorldRow) {
					
					String line = bufferedReader.readLine();
					
					while (col < gamepanel.maxWorldColumns ) {
						String numbers[] = line.split(" ");
						
						int number = Integer.parseInt(numbers[col]);
						
						maptileno[map][col][row] = number;
						col++;
					} 
					if (col == gamepanel.maxWorldColumns) {
						col = 0;
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
				
				int tilenumber = maptileno[gamepanel.currentMap][worldColumn][worldRow];
				
				int worldX =  worldColumn * gamepanel.tileSize; 
				int worldY =  worldRow * gamepanel.tileSize; 
				int screenX = worldX - gamepanel.player.Worldx + gamepanel.player.screenX;
				int screenY = worldY - gamepanel.player.Worldy + gamepanel.player.screenY;
				
				
				graphics2d.drawImage(tile[tilenumber].image, screenX, screenY, null);
				worldColumn++;

				
				if(worldColumn == gamepanel.maxWorldColumns) {
					worldColumn = 0; 
					worldRow++;
					
				}
			}
		}
}
