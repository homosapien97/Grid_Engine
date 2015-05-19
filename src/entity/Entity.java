package entity;

import world.Chunk;

public class Entity {
	public int x;
	public int y;
	public Chunk chunk;
	public String spriteFilepath;
	
	
	public Entity(int x, int y, Chunk chunk, String sprite) {
		this.x = x;
		this.y = y;
		this.chunk = chunk;
		chunk.addEntity(this);
		this.spriteFilepath = sprite;
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
}
