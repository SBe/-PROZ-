package Entity;

import End.Screen;
import End.Sprite;

public class Monster extends Mob {
	
	private int xa = 0;
	private int ya = 0;
	private int ticks = 0;
	private Player player;
	public Monster(int x, int y, Player player){
		this.x = x << 4;
		this.y = y << 4;
		this.player = player;
		sprite = Sprite.monsterLeft1Sprite;
	}
	public void update(){
			if(distance() > 48){
				if(!collision(xa + x, ya + y)){
					mobMove();
				}
				else if(!collision(x + xa, y)){
						ya *= -1;
					//	xa *= -1;
						y += ya;
				}
				else if(!collision(x , y + ya) ){
						xa *= -1;
				//		ya = 0;
						x += xa;
				}
				if( xa > 0) dir = 1;
				if( xa < 0) dir = 3;
				if( ya > 0) dir = 2;
				if( ya < 0) dir = 0;
							
			}
		++ticks;
	}
	private void mobMove(){
		int tmpX = player.x;
		int tmpY = player.y; 
		//if(!collision(xa + x, ya + y)){
		if(tmpX - x > 0)
			xa = 1;
		else if(tmpX - x == 0)
			xa = 0;
		else
			xa = -1;
		if(tmpY - y > 0)
			ya = 1;
		else if( tmpY == y)
			ya = 0;
		else
			ya = -1;
		x += xa;
		y += ya;
		//}
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
