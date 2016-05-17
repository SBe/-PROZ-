package Tile;

import End.Screen;
import End.Sprite;

public class VoidTile extends Tile{

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
	public boolean solid(){
		return true;
	}
}
