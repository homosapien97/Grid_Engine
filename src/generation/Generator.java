package generation;

import geometry.Point;
import terrain.Quicksand;
import terrain.Stone;
import terrain.Terrain;
import world.Chunk;

public class Generator {
	public static Chunk generateChunk(int x, int y) {
//		return (Math.random() > .5) ? (new Chunk(x, y, true, Stone.get())) : (new Chunk(x, y, true, Quicksand.get()));
		return new Chunk(x, y, true, Quicksand.get());
		//TODO finish this.
	}
	public static Chunk generateChunk(Point p) {
		return new Chunk(p, true, Quicksand.get());
	}
}
