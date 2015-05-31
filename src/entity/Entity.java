package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import action.Action;
import world.Chunk;

public class Entity {
	public int x;
	public int y;
	public Chunk chunk;
	public String spriteFilepath;
	public int maxActions;
	public List<Action> actions;
	
	
	public Entity(int x, int y, Chunk chunk, String sprite, int maxActions) {
		this.x = x;
		this.y = y;
		this.chunk = chunk;
		chunk.addEntity(this);
		this.spriteFilepath = sprite;
		this.maxActions = maxActions;
		actions = Collections.synchronizedList(new ArrayList<Action>(maxActions));
	}
	
	//relative position in chunk
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	//position in relation to all chunks
	
	public int getAbsoluteX() {
		return chunk.pos.x * Chunk.DIM + x;
	}
	public int getAbsoluteY() {
		return chunk.pos.y * Chunk.DIM + y;
	}
	
	public String getSpriteFilepath() {
		return spriteFilepath;
	}
	public void delete() {
		chunk.removeEntity(this);
	}
	protected boolean addAction(Action a) {
		synchronized(actions) {
			for(Action b : actions) {
				if(b.done()) actions.remove(b);
			}
			if(actions.size() < maxActions) {
				actions.add(a);
				return true;
			}
		}
		return false;
	}
}
