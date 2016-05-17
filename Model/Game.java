package End;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Entity.Monster;
import Entity.Player;
import Level.Level;
import Level.SpawnLevel;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = -2407860145562823606L;
	private static final int WIDTH = 300;
	private static final int HEIGHT = WIDTH / 16 * 9;
	private static final int SCALE = 3;
	private static String title = "Game | ";
	private Screen screen;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private JFrame myFrame;
	private boolean running = false;
	private Player player;
	private Keyboard keyboard;
	private Level level;
	public Thread thread;
	
	public void run(){
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			++frames;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				myFrame.setTitle(title + "FPS: " + frames + " | Updates: " + updates);
				updates = 0;
				frames = 0;
			}
		}
	}
	public synchronized void start(){
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		running = false;
		try{
			thread.join();
		}	catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public void update(){
		keyboard.update();
		player.update();
		level.update();
	}
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
	//	screen.clear();
		level.render(player.x - screen.getWidth()/2 + Sprite.playerBack1Sprite.SIZE/2, player.y - screen.getHeight()/2 + Sprite.playerBack1Sprite.SIZE / 2, screen);
		player.render(screen);
		
	//	screen.renderSprite(16 * 19, 16 * 55, new Sprite(16, 16, 0xFF123123), true);
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,SCALE * WIDTH, SCALE * HEIGHT, null );
		g.dispose();
		bs.show();
	}
	public Game(){
		Dimension size = new Dimension(SCALE * WIDTH, HEIGHT * SCALE);
		setPreferredSize(size);
		screen = new Screen(WIDTH, HEIGHT);
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		level = new SpawnLevel("/Level_Spawn.png");
		player = new Player(16 * 19, 16 * 55,keyboard);
		player.init(level);
		Mouse mouse = new Mouse();
		level.add(new Monster( 19,  55, player));
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	public static void main(String args[]){
		Game g = new Game();
		g.myFrame = new JFrame();
		g.myFrame.setResizable(false);
		g.myFrame.setVisible(true);
		g.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.myFrame.setTitle(title);
		g.myFrame.add(g);
		g.myFrame.pack();
		g.start();
	}
	public int getWidth(){
		return WIDTH * SCALE;
	}
	public int getHeight(){
		return HEIGHT * SCALE;
	}
}
