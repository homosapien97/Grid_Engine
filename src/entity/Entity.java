package entity;

import general.Tools;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.util.Vector;

import action.Action;
import world.Chunk;

public abstract class Entity {
	public int x;
	public int y;
	public Chunk chunk;
	public String filename;
	public int maxActions;
	public List<Action> actions;
	
	public static Image sprite;
	
	public Entity(int x, int y, Chunk chunk, String sprite, int maxActions) {
		this.x = x;
		this.y = y;
		this.chunk = chunk;
		chunk.addEntity(this);
		this.filename = sprite;
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
		return filename;
	}
	public void delete() {
		chunk.removeEntity(this);
	}
	public boolean deleteSafe() {
		return true;
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
	public Image sprite() {
		return sprite;
	}
//	public abstract void load();
}
