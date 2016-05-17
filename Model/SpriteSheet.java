package End;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet spawnLevelSheet = new SpriteSheet("/Spwan_level.png", 48);
	public static SpriteSheet playerSheet = new SpriteSheet("/pl.png", 32 * 4);
	public static SpriteSheet monsterSheet = new SpriteSheet("/monster.png", 352);
	public SpriteSheet(String path, int size){
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	public void load(){
		try{
			BufferedImage img = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = img.getWidth();
			int h = img.getHeight();
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch(IOException e){
			e.printStackTrace();
		}
		
		}
}
