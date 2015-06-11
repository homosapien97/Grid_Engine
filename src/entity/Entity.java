package entity;

import java.awt.Image;

import action.Action;
import spells.Spell;
import tools.Tools;
import world.Chunk;

public abstract class Entity {
	protected int absoluteX;
	protected int absoluteY;
	public Chunk chunk;
	public String filename;
//	public int maxActions;
//	public List<Action> actions;
	
	public static Image sprite;
	
	public boolean remove = false;
	
	public Entity(int absoluteX, int absoluteY, Chunk chunk, String filename) {
		this.absoluteX = absoluteX;
		this.absoluteY = absoluteY;
		this.chunk = chunk;
		chunk.addEntity(this);
		this.filename = filename;
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
	
	public int getAbsoluteX() {
//		return chunk.pos.x * Chunk.DIM + absoluteX;
		return absoluteX;
	}
	public int getAbsoluteY() {
//		return chunk.pos.y * Chunk.DIM + absoluteY;
		return absoluteY;
	}
	
	public String getSpriteFilepath() {
		return filename;
	}
	public void delete() {
		remove = true;
		chunk.removeEntity(this);
		synchronized (Action.queue) {
			Action.queue.remove(this);
		}
//		chunk = null; //this causes a nasty nullpointerexception that I can't seem to find and fix in move actions over chunk boarders
	}
	public void flagForRemoval() {
		remove = true;
	}
//	public boolean deleteSafe() {
//		delete();
//		return true;
//	}
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
	public abstract Image sprite();
	
	public abstract boolean cast(Spell s);
//	public abstract void load();
}
