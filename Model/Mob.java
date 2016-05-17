package Entity;

import java.util.ArrayList;
import java.util.List;

import End.Sprite;
import projectile.Projectile;
import projectile.myProjectile;

public abstract class Mob extends Entity{
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	public synchronized void move(int xa, int ya){
			if(xa != 0 && ya != 0){
				move(xa,0);
				move(0, ya);
				return;
			}
			if( xa > 0) dir = 1;
			if( xa < 0) dir = 3;
			if( ya > 0) dir = 2;
			if( ya < 0) dir = 0;
			if(!collision(xa, ya, 16)){
				x += xa;
				y += ya;
			} 
	}
	
	public void update(){
		
	}
	
	public void render(){
		
	}
	
	private boolean collision(int xa, int ya, int size){
		boolean solid = false;
		for( int c = 0; c < 4; c++){
			int xt = ((x + xa) + c % 2 * 13 + 10 )/ size;
			int yt = ((y + ya) + c / 2 * 10 + 21)/ size;
			if(level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	protected void shoot(int x, int y, double d){
		Projectile p = new myProjectile( x , y,  d);
		level.add(p);
	}
}
