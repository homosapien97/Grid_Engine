package entity.interfaces;

import geometry.Point;

import java.util.Vector;

import world.Chunk;

public interface Large {
	public Vector<Chunk> getChunks();
	public boolean contains(Point p);
	public boolean contains(int x, int y);
}
