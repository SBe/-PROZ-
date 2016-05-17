package Tile;

import End.Screen;
import End.Sprite;

public class Tile {
	private int x, y;
	public Sprite sprite;
	
	public static Tile grassTile = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidsSprite);
	public static Tile woodenfloorTile = new WoodenFloorTile(Sprite.woodenfloor);
	public static Tile wallTile = new WallTile(Sprite.wall);
	public static Tile rockfloorTile = new RockFloorTile(Sprite.rockfloor);
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	public void render(int x, int y, Screen screen){
		
	}
	public boolean solid(){
		return false;
	}
}
