package Level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Monster;
import Entity.Turrent;

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
	//	add(new Monster( 22,  55));
		add(new Turrent(11, 62));
		add(new Turrent(42, 21));
		add(new Turrent(47, 12));
		add(new Turrent(68, 8));
		add(new Turrent(63, 17));
		add(new Turrent(72, 24));
		add(new Turrent(70, 26));
		add(new Turrent(94, 14));
		add(new Turrent(94, 21));
		add(new Turrent(109, 17));
		add(new Turrent(97, 29));
		add(new Turrent(106, 24));
		add(new Turrent(102, 29));
		add(new Turrent(29, 61));
		add(new Turrent(26, 52));
		add(new Turrent(8, 48));
		add(new Turrent(23, 41));
		add(new Turrent(10, 14));
		add(new Turrent(28, 14));
		add(new Monster(16, 51));
		add(new Monster(21, 51));
		add(new Monster( 16,  55));
		add(new Monster( 18,  57));
	
	}
}
