package Entity;

import javax.swing.Timer;

import End.Keyboard;
import End.Mouse;
import End.Screen;
import End.Sprite;
import projectile.Projectile;
import projectile.myProjectile;

public class Player extends Mob{
	
	private Keyboard input;
	private Timer time = new Timer(4, null);
	private Sprite sprite;
	private int ticks = 0;
	private boolean walking = false;
	private int fire_rate = 0;
	public Player(Keyboard input){
		this.input = input;
		time.start();
	}
	public Player(int x, int y, Keyboard input){
		this.x = x;
		this.y = y;
		this.input = input;
		time.start();
		fire_rate = myProjectile.FIRE_RATE;
	}
	public void update(){
		++ticks;
		if(fire_rate > 0) --fire_rate;
		int xa = 0, ya = 0;
		if(input.isUp()) ya--;
		if(input.isDown()) ya++;
		if(input.isLeft()) xa--;
		if(input.isRight()) xa++;
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else{
			walking = false;
		}
		updateShooting();
		clear();
	}
	private void clear() {
		//System.out.println(projectiles.size());
		for(int i = 0; i < level.getProjectiles().size(); i++){
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()) level.getProjectiles().remove(i);
		}
		
	}
	private void updateShooting() {
		if(Mouse.getMouseB() == 1 && fire_rate == 0){
			double dx = Mouse.getX() - 450;
			double dy = Mouse.getY() - 450 / 16 * 9;
			double d = Math.atan2(dy, dx);
			shoot(x, y, d);
			fire_rate = myProjectile.FIRE_RATE;
		}
		
	}
	public void render(Screen screen){
		if(dir == 0) {
			sprite = Sprite.playerFrontSprite; 
			if(walking){
				if(ticks % 30 < 15)
					sprite = Sprite.playerFront1Sprite;
				else
					sprite = Sprite.playerFront2Sprite;
			}
		}
		if(dir == 1) {
			sprite = Sprite.playerRightSprite; 
			if(walking){
				if(ticks % 30 < 15)
					sprite = Sprite.playerRight1Sprite;
				else
					sprite = Sprite.playerRight2Sprite;
			}
		}
		if(dir == 2) {
			sprite = Sprite.playerBackSprite; 
			if(walking){
				if(ticks % 30 < 15)
					sprite = Sprite.playerBack1Sprite;
				else
					sprite = Sprite.playerBack2Sprite;
			}
		}
		if(dir == 3) {
			sprite = Sprite.playerLeftSprite; 
			if(walking){
				if(ticks % 30 < 15)
					sprite = Sprite.playerLeft1Sprite;
				else
					sprite = Sprite.playerLeft2Sprite;
			}
		}
		screen.renderMob(x, y, sprite);
	}
	
}
