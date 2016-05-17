package End;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sun.security.validator.KeyStores;

public class Keyboard implements KeyListener{

	private boolean[] keys = new boolean[120];
	private boolean up, down, left, right;
	
	public void update(){ 
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	public  void keyPressed(KeyEvent e) {
				keys[e.getKeyCode()] = true;
		
	}

	public  void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}
}
