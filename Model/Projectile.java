package projectile;

import java.util.Random;

import End.Sprite;
import Entity.Entity;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double x, y;
	protected double distance;
	protected double speed;
	protected double range;
	protected double damage;
	protected final Random random = new Random();
	
	public Projectile(int x, int y, double dir){
		this.xOrigin = x;
		this.yOrigin = y;
		this.angle = dir;
		this.x = x;
		this.y = y;
	}
	protected void move(){
	
	}


}
