package world;

import terrain.Quicksand;
import terrain.Stone;
import terrain.Terrain;

public class Generator {
	public static Chunk generateChunk(int x, int y) {
//		return (Math.random() > .5) ? (new Chunk(x, y, true, Stone.get())) : (new Chunk(x, y, true, Quicksand.get()));
		return new Chunk(x, y, true, Quicksand.get());
		//TODO finish this.
	}
}
