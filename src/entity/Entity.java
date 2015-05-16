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
		this.spriteFilepath = sprite;
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public int getAbsoluteX() {
		return chunk.xPos * Chunk.DIM + x;
	}
	public int getAbsoluteY() {
		return chunk.yPos * Chunk.DIM + y;
	}
	
	public String getSpriteFilepath() {
		return spriteFilepath;
	}
}
