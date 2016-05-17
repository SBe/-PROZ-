package Entity;

import java.util.ArrayList;
import java.util.List;

import Level.Level;

public class Spawner extends Entity{

	public enum Type{
		MOB, PARTICLE;
	}
	protected Type type;
	public Spawner(int x, int y, Type t, int amount, Level level){
		init(level);
		this.x = x;
		this.y = y;
		this.type = t;
	
	}
}
