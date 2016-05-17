package Tile;

import End.Screen;
import End.Sprite;

public class WoodenFloorTile extends Tile {

	public WoodenFloorTile(Sprite sprite) {
		super(sprite);
	}
	public boolean solid(){
		return false;
	}
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4 , y << 4, this);
	}
}
