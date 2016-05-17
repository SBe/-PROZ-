package End;

import java.util.Random;

public class Sprite {
	public final int SIZE;
	private int x, y;
	private int width, height;
	private SpriteSheet sheet;
	public int[] pixels;
	private static Random random = new Random();
	private static int r = random.nextInt(16777215);
	public static Sprite grass = new Sprite( 16, 0, 0, SpriteSheet.spawnLevelSheet );
	public static Sprite voidsSprite = new Sprite(16, 0x332211);
	public static Sprite grass1 = new Sprite(16, 0, 1, SpriteSheet.spawnLevelSheet);
	public static Sprite grass2 = new Sprite(16, 0 , 2, SpriteSheet.spawnLevelSheet);
	public static Sprite rockfloor = new Sprite(16, 1, 0, SpriteSheet.spawnLevelSheet);
	public static Sprite woodenfloor = new Sprite(16, 1, 1, SpriteSheet.spawnLevelSheet);
	public static Sprite wall = new Sprite (16, 1, 2, SpriteSheet.spawnLevelSheet);
	
	public static Sprite projectile = new Sprite (16, 2, 0, SpriteSheet.spawnLevelSheet);
	
	public static Sprite particle1 = new Sprite (3, r); 
	
	public static Sprite playerFrontSprite = new Sprite(32, 0 , 0, SpriteSheet.playerSheet);
	public static Sprite playerFront1Sprite = new Sprite(32, 0 , 1, SpriteSheet.playerSheet);
	public static Sprite playerFront2Sprite = new Sprite(32, 0 , 2, SpriteSheet.playerSheet);
	public static Sprite playerRightSprite = new Sprite(32, 1 , 0, SpriteSheet.playerSheet);
	public static Sprite playerRight1Sprite = new Sprite(32, 1 , 1, SpriteSheet.playerSheet);
	public static Sprite playerRight2Sprite = new Sprite(32, 1 , 2, SpriteSheet.playerSheet);
	public static Sprite playerLeftSprite = new Sprite(32, 2 , 0, SpriteSheet.playerSheet);
	public static Sprite playerLeft1Sprite = new Sprite(32, 2 , 1, SpriteSheet.playerSheet);
	public static Sprite playerLeft2Sprite = new Sprite(32, 2 , 2, SpriteSheet.playerSheet);
	public static Sprite playerBack2Sprite = new Sprite(32, 3 , 0, SpriteSheet.playerSheet);
	public static Sprite playerBack1Sprite = new Sprite(32, 3 , 1, SpriteSheet.playerSheet);
	public static Sprite playerBackSprite = new Sprite(32, 3 , 2, SpriteSheet.playerSheet);
	
	public static Sprite monsterBackSprite = new Sprite(32, 8, 5, SpriteSheet.monsterSheet);
	public static Sprite monsterBack1Sprite = new Sprite(32, 9, 5, SpriteSheet.monsterSheet);
	public static Sprite monsterRightSprite = new Sprite(32, 8, 7, SpriteSheet.monsterSheet);
	public static Sprite monsterRight1Sprite = new Sprite(32, 9, 7, SpriteSheet.monsterSheet);
	public static Sprite monsterLeftSprite = new Sprite(32, 6, 7, SpriteSheet.monsterSheet);
	public static Sprite monsterLeft1Sprite = new Sprite(32, 7, 7, SpriteSheet.monsterSheet);
	public static Sprite monsterFrontSprite = new Sprite(32, 8, 6, SpriteSheet.monsterSheet);
	public static Sprite monsterFront1Sprite = new Sprite(32, 9, 6, SpriteSheet.monsterSheet);

	public Sprite( int size, int x, int y, SpriteSheet sheet){
		this.SIZE = size;
		this.sheet = sheet;
		this.x = size * x;
		this.y = size * y;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	public Sprite(int size, int color){
		this.SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	public Sprite(int width, int height, int color){
		this.SIZE = -1;
		this.width = width;
		this.height =height;
		pixels = new int[width * height];
		setColor(color);
	}
	private void setColor(int color){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = color;
		}
	}
	public void load(){
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) +(y + this.y) * sheet.SIZE];
			}
		}
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
