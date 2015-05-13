package entity;

import world.Chunk;

public class Entity {
	private int x;
	private int y;
	Chunk chunk;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getAbsoluteX() {
		return chunk.xPos * Chunk.GRID_DIM + x;
	}
	public int getAbsoluteY() {
		return chunk.yPos * Chunk.GRID_DIM + y;
	}
}
