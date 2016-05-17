package Tile;

import End.Screen;
import End.Sprite;

public class WallTile extends Tile {

	public WallTile(Sprite sprite) {
		super(sprite);
	}
	public boolean solid(){
		return true;
	}
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4 , y << 4, this);
	}

}
