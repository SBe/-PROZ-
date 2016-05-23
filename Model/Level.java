package Level;

import java.util.ArrayList;
import java.util.List;

import End.Screen;
import Entity.Entity;
import Entity.Particle;
import Entity.Player;
import Tile.Tile;
import projectile.Projectile;

public class Level {
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<Player> players = new ArrayList<Player>();
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	public Level(String path){
		loadLevel(path);
		generateLevel();
		
	}
	protected void generateLevel(){
		
	}
	protected void loadLevel(String path){
		
	}
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
		}
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).update();
		}
		for(int i = 0; i < players.size(); i++){
			players.get(i).update();
		}
		remove();
	}
	private void remove(){
		
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).isRemoved())entities.remove(i);
		}
		for(int i = 0; i < projectiles.size(); i++){
			if(projectiles.get(i).isRemoved())projectiles.remove(i);
		}
		for(int i = 0; i < particles.size(); i++){
			if(particles.get(i).isRemoved()) particles.remove(i);
		}
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).isRemoved()) players.remove(i);
		}
	}
	public void add(Entity e){
		e.init(this);
		if( e instanceof Particle)
			particles.add((Particle) e);
		else if( e instanceof Projectile)
			projectiles.add((Projectile) e);
		else if( e instanceof Player)
			players.add((Player)e);
		else
			entities.add(e);	
	}
	
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.getWidth() + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.getHeight() + 16) >> 4;
		for(int y = y0; y < y1; y++){
			for(int x = x0; x < x1; x++){
				getTile(x, y).render(x, y, screen);
			}
		}
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(screen);
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).render(screen);
		}
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).render(screen);
		}
		for(int i = 0; i < players.size(); i++){
			players.get(i).render(screen);
		}
	}
	public Tile getTile(int x, int y){
		if(x < 0 || y  < 0 || x >= width || y >= height) return Tile.voidTile;
		if(tiles[x + y * width] == 0xFF00FF00) return Tile.grassTile;
		if(tiles[x + y * width] == 0xFF606060) return Tile.rockfloorTile;
		if(tiles[x + y * width] == 0xFF7F3300) return Tile.woodenfloorTile;
		if(tiles[x + y * width] == 0xFF9F9F9F) return Tile.wallTile;
		return Tile.voidTile;
	}
	private void time(){
	}
	public boolean tileCollision(int x, int y, int xoffset, int yoffset, int size){
		boolean solid = false;
		for( int c = 0; c < 4; c++){
			int xt = (x + c % 2 * size  + xoffset )/16;
			int yt = (y + c / 2 * size  + yoffset)/16;
			if(getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	public List<Projectile> getProjectiles(){
		return projectiles;
	}
	public Player getPlayer(){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i) instanceof Player)
				return (Player) entities.get(i);
		}
		return null;
	}
	public List<Player> getPlayers(){
		return players;
	}
	public Player getPlayerAt(int i){
		return players.get(i);
	}
	public Player getFirstPlayer(){
		return players.get(0);
	}
	public List<Entity> getEntities(Entity e,  int radius){
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int)e.getX();
		int ey = (int)e.getY();
		for(int i = 0 ; i < entities.size(); i++){
			Entity ent = entities.get(i);
			int x = (int)ent.getX();
			int y = (int)ent.getY();
			
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance <= radius)
				result.add(ent);
		}
		return result;
	}
	
	public List<Player> getPlayers(Entity e, int radius){
		List<Player> play = new ArrayList<Player>();
		int ex = (int)e.getX();
		int ey = (int)e.getY();
		for(int i = 0 ; i < players.size(); i++){
			Player ent = players.get(i);
			int x = (int)ent.getX();
			int y = (int)ent.getY();
			
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance <= radius)
				play.add(ent);
			}
		
		return play;
	}
}
