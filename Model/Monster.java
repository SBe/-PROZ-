package Entity;

import End.Screen;
import End.Sprite;

public class Monster extends Mob {
	
	private int xa = 0;
	private int ya = -1;
	private int ticks = 0;
	private Player player;
	public Monster(int x, int y, Player player){
		this.x = x << 4;
		this.y = y << 4;
		this.player = player;
		sprite = Sprite.monsterLeft1Sprite;
	}
	public void update(){
			if(player.x - x > 0)
				xa = 1;
			if(player.x - x < 0)
				xa = -1;
			if(player.y - y > 0)
				ya = 1;
			if(player.y - y < 0)
				ya = -1;
			move(xa, ya);
			xa = 0;
			ya = 0;
			
	}
	public synchronized void move(int xa, int ya){
		if(distance() > 48){
		if(xa != 0 && ya != 0){
			move(xa,0);
			move(0, ya);
			return;
		}
		if( xa > 0) dir = 1;
		if( xa < 0) dir = 3;
		if( ya > 0) dir = 2;
		if( ya < 0) dir = 0;
		if(!collision(x + xa, y + ya)){
			x += xa;
			y += ya;
			} 
		}
		++ticks;
}
	private boolean collision(int xa, int ya){
		boolean solid = false;
		for( int c = 0; c < 4; c++){
			int xt = (xa + c % 2 * 32 )/16;
			int yt = (ya + c / 2 * 32)/16;
			if(level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	public boolean solid(){
		return true;
	}
	public void render(Screen screen){
		if(dir == 0) { 
				if(ticks  % 30 < 15)
					sprite = Sprite.monsterFrontSprite;
				else
					sprite = Sprite.monsterFront1Sprite;
		}
		if(dir == 1) {
				if(ticks % 30 < 15)
					sprite = Sprite.monsterRightSprite;
				else
					sprite = Sprite.monsterRight1Sprite;
		}
		if(dir == 2) {
		
				if(ticks % 30 < 15)
					sprite = Sprite.monsterBackSprite;
				else
					sprite = Sprite.monsterBack1Sprite;
		}
		if(dir == 3) {
				if(ticks % 30 < 15)
					sprite = Sprite.monsterLeftSprite;
				else
					sprite = Sprite.monsterLeft1Sprite;
			
		}
		screen.renderMob(x, y, sprite);
	}
	public double distance(){
		
		double distance = Math.sqrt((player.x - x ) * (player.x - x  ) + (player.y - y ) * (player.y - y ) );
		return distance;
	}
}
