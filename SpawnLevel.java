package Level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Monster;
import Tile.Tile;

public class SpawnLevel extends Level{
	
	public SpawnLevel(String path) {
		super(path);
	}
	protected void generateLevel(){
		
	}
	//7F3300 - floor
	//9F9F9F - wall
	//00FF00 - grass
	//606060 - rockfloor
	protected void loadLevel(String path){
		try{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch(IOException e){
			e.printStackTrace();
		}
	//	add(new Monster( 19,  55));
	}
}
