package Entity;

import End.Sprite;
import projectile.MonsterProjectile;
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
			
			if(this instanceof Player){
				for(int y = 0; y < Math.abs(ya); y++){
					if(!collision(xa, absolute(ya), 16))
						this.y += absolute(ya);
				}
				for(int x = 0; x < Math.abs(xa); x++){
					if(!collision(absolute(xa), ya, 16))
						this.x += absolute(xa);
				}
			} 
			if(!collision(x + xa, y + ya) && this instanceof Monster){
				for(int y = 0; y < Math.abs(ya); y++){
					if(!collision(xa, absolute(ya)))
						this.y += absolute(ya);
				}
				for(int x = 0; x < Math.abs(xa); x++){
					if(!collision(absolute(xa), ya))
						this.x += absolute(xa);
				}
			} 
	}
	
	private int absolute(double y) {
		if(y < 0) return -1;
		return 1;
	}

	public void update(){
		
	}
	
	public void render(){
		
	}
	
	private boolean collision(double xa, double ya, int size){
		boolean solid = false;
		for( int c = 0; c < 4; c++){
			double xt = ((x + xa) + c % 2 * size + 7)/16;
			double yt = ((y + ya) + c / 2 * size)/16;
			int ix = (int) Math.floor(xt);
			int iy= (int) Math.ceil(yt);
			//if( c / 2 == 0) iy = (int) Math.floor(yt);
			if(level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
	public boolean collision(double xa, double ya){
		boolean solid = false;
		for( int c = 0; c < 4; c++){
			double xt = (xa + c % 2 * 32 )/16;
			double yt = (ya + c / 2 * 32)/16;
			if(level.getTile((int)xt, (int)yt).solid()) solid = true;
		}
		return solid;
	}
	protected void shoot(double x, double y, double d){
		Projectile p = new myProjectile( (int)(x + 9) , (int)y,  d);
		level.add(p);
	}
	protected void shootmonster(double x, double y, double d){
		Projectile p = new MonsterProjectile( (int)(x + 9) , (int)y,  d);
		level.add_monster_projectiles(p);;
	}
}
