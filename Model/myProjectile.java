package projectile;

import End.Screen;
import End.Sprite;
import Entity.ParticleSpawner;
import Entity.Spawner;

public class myProjectile extends Projectile{
	public static final int  FIRE_RATE = 15;
	public myProjectile(int x, int y, double dir){
		super(x, y, dir);
		range = 100 + random.nextInt(70);
		damage = 20;
		sprite = Sprite.projectile;
		speed = 3;
		nx = speed * Math.cos(dir) +  random.nextDouble() - 0.5;
		ny = speed * Math.sin(dir) +  random.nextDouble() - 0.5;
	}
	public void update(){
		if(level.tileCollision((int)(x + nx), (int)(y + ny),  5,  5, 6)){
				remove();
				level.add(new ParticleSpawner((int)(x - 8), (int)(y - 8), 30, 50, level));
		}
			move();
	}
	public void render(Screen screen){
		screen.renderSprite((int)(x ) , (int)(y )  + 8, sprite);
	}
	protected void move(){
		if(!level.tileCollision((int)(x ), (int)(y ),  5,  5, 6)){
			this.x += nx;
			this.y += ny;
		}
		if(calculate() > range){
			remove();
		}
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	public int getSpriteSize(){
		return sprite.SIZE;
	}
	protected double calculate(){
		distance = Math.sqrt((xOrigin - x ) * (xOrigin - x  ) + (yOrigin - y ) * (yOrigin - y ) );
		return distance;
	}
}
