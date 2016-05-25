package projectile;

import java.util.ArrayList;

import End.Screen;
import End.Sprite;
import Entity.Entity;
import Entity.Monster;
import Entity.ParticleSpawner;
import Entity.Turrent;

public class myProjectile extends Projectile{
	public static final int  FIRE_RATE = 10;
	public myProjectile(double x, double y, double dir){
		super(x, y, dir);
		range = 200 + random.nextInt(70);
		damage = 10;
		sprite = Sprite.projectile;
		speed = 5;
		nx = speed * Math.cos(dir) +  random.nextDouble() - 0.5;
		ny = speed * Math.sin(dir) +  random.nextDouble() - 0.5;
	}
	public void update(){
		//List<Entity> monster_list = level.getEntities();
	//	if()
		ArrayList<Entity> asd = (ArrayList<Entity>) level.getEntities();
		for(int i = 0 ; i < asd.size(); i++	)
			if((asd.get(i) instanceof Turrent || asd.get(i) instanceof Monster)&& ((int)(x + nx) >= asd.get(i).getX())  && ((int)(x + nx) <= asd.get(i).getX() + 16) 
					&& (int)(y + ny) >= asd.get(i).getY() - 8 && (int)(y + ny) <= asd.get(i).getY() + 8){
				remove();
				asd.get(i).setHP(asd.get(i).getHP() - damage);
				if(asd.get(i).getHP() == 0){
					asd.get(i).remove();
				}
				level.add(new ParticleSpawner((int)(x - 8), (int)(y - 8), 30, 1, level));
			}
		if(level.tileCollision((int)(x + nx), (int)(y + ny),  5,  5, 6)){
				remove();
				level.add(new ParticleSpawner((int)(x - 8), (int)(y - 8), 30, 5, level));
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
