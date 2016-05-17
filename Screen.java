package End;

import java.util.Random;

import javax.swing.Timer;

import Entity.Player;
import Tile.Tile;

public class Screen {
	public int width;
	public int height;
	public final int MAP_SIZE = 64;
	public final int MAP_MASK = MAP_SIZE - 1;
	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	public int xOffset, yOffset;
	private Timer time = new Timer(5, null);
	
	private Random random = new Random();
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		for(int i =0; i < tiles.length; i++){
			tiles[i] = random.nextInt(0xFFFFFF);
		}
	}
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
	      xp -= xOffset;
	      yp -= yOffset;
	      for (int y = 0; y < tile.sprite.SIZE; y++) {
	         int ya = y + yp;
	         for (int x = 0; x < tile.sprite.SIZE; x++) {
	            int xa = x + xp;
	            if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
	            if( xa < 0) xa = 0;
	            pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
	         }
	      }
	   }
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed){
		if(fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		for(int y = 0; y < sprite.getHeight(); y++){
			int ya = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++){
				int xa = x + xp;
				if( xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
	public void renderSprite(int xp, int yp, Sprite sprite) {
	      xp -= xOffset;
	      yp -= yOffset;
	      for (int y = 0; y < sprite.SIZE; y++) {
	         int ya = y + yp;
	         for (int x = 0; x < sprite.SIZE; x++) {
	            int xa = x + xp;
	            if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
	            if( xa < 0) xa = 0;
	            if(sprite.pixels[x + y * sprite.SIZE] != 0xFFFF00FF)
	            pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
	         }
	      }
	   }
	public void renderMob(int xp, int yp, Sprite sprite){
	xp -= xOffset;
    yp -= yOffset;
    for (int y = 0; y < sprite.SIZE; y++) {
       int ya = y + yp;
       for (int x = 0; x < sprite.SIZE; x++) {
          int xa = x + xp;
          if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
          if( xa < 0) xa = 0;
          int col = sprite.pixels[x + y * sprite.SIZE];
          if(col != 0xFFFF00FF)
        	  pixels[xa + ya * width] = col;
       }
    }
 }
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
