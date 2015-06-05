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
	protected int absoluteX;
	protected int absoluteY;
	public Chunk chunk;
	public String filename;
//	public int maxActions;
//	public List<Action> actions;
	
	public static Image sprite;
	
	public Entity(int absoluteX, int absoluteY, Chunk chunk, String sprite, int maxActions) {
		this.absoluteX = absoluteX;
		this.absoluteY = absoluteY;
		this.chunk = chunk;
		chunk.addEntity(this);
		this.filename = sprite;
//		this.maxActions = maxActions;
//		actions = Collections.synchronizedList(new ArrayList<Action>(maxActions));
	}
	
	//relative position in chunk
	
	public int getMinorX() {
		return Tools.nav.absCoordToMinorCoord(absoluteX);
	}
	public int getMinorY() {
		return Tools.nav.absCoordToMinorCoord(absoluteY);
	}
	
	//position in relation to all chunks
	
	public int REFACTORSTUFFgetAbsoluteX() {
//		return chunk.pos.x * Chunk.DIM + absoluteX;
		return absoluteX;
	}
	public int REFACTORSTUFFgetAbsoluteY() {
//		return chunk.pos.y * Chunk.DIM + absoluteY;
		return absoluteY;
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
//	protected boolean addAction(Action a) {
//		synchronized(actions) {
//			for(Action b : actions) {
//				if(b.done()) actions.remove(b);
//			}
//			if(actions.size() < maxActions) {
//				actions.add(a);
//				return true;
//			}
//		}
//		return false;
//	}
	public Image sprite() {
		return sprite;
	}
//	public abstract void load();
}
