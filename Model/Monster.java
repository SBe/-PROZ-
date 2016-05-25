package Entity;

import java.util.List;

import End.Screen;
import End.Sprite;

public class Monster extends Mob {

	private int ticks = 0;
	private int hp = 100;
	public Monster(int x, int y){
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.monsterLeft1Sprite;
	}
	public void update(){
		if(Player.alive && level.getFirstPlayer().getWin() == false){
			if(x >= level.getFirstPlayer().getX() - 16 && x <= level.getFirstPlayer().getX() + 16 && y > level.getFirstPlayer().getY()- 32 && y < level.getFirstPlayer().getY() + 32){
				System.out.println("Collision" + level.getFirstPlayer().getX() + " " + x);
				double val = Math.atan2(level.getFirstPlayer().getY() - y,level.getFirstPlayer().getY() - y );
		//		val /=3;
			//	System.out.println(val);
				//x = (int)(val * x);
			//	y = (int)(val * y);
				//]	y -= 32;
			}
			++ticks;
			move();		
			}
	}
	
	public synchronized void move(){
		int xa = 0, ya = 0;
		List<Player> players = level.getPlayers(this, 160);
		if(players.size() > 0){
			Player player = level.getFirstPlayer();
		if(player.x - x > 0){
			xa += 2;
			dir = 1;
			
		} else if (player.x - x < 0){
			xa -= 2;
			dir = 3;
		}
		if(player.y - y > 0){
			ya += 2;
			dir = 2;
			
		}else if(player.y - y < 0){
			ya -= 2;
			dir = 0;
		}
		if(xa != 0 || ya != 0){
			move(xa,ya);
			return;
		}
		}
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
		screen.renderMob((int)x, (int)y , sprite);
	}
	public double distance(Player player){
		double distance = Math.sqrt((player.x - x ) * (player.x - x  ) + (player.y - y ) * (player.y - y ) );
		return distance;
	}
}
