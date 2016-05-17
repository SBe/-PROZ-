package Entity;

import End.Screen;
import End.Sprite;

public class Particle extends Entity {
	
	protected int life;
	private Sprite sprite;
	private int time = 0;
	protected double xx, yy, xa, ya, zz, za;
	
	public Particle(int x, int y, int life){
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.sprite = Sprite.particle1;
		this.life = life + (random.nextInt(50) - 25);;
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2;
	}
	public void update(){
		++time;
		za -= 0.1;
		if(zz < 0){
			zz = 0;
			za *= -0.5;
			xa *= 0.05;
			ya *= 0.05;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
		if(time > life)
			remove();
	}
	public void render(Screen screen){
	//	screen.renderSprite(x, x, sprite, false);
		screen.renderSprite((int) xx + 16, (int)yy + 16 - (int ) zz, sprite, true);
	}

}

