package generation;

import geometry.Point;
import terrain.Quicksand;
import tools.Tools;
import world.Chunk;

public class Generator {
	public static int seed = (Math.random() > .5) ? (int)(Math.random() * Integer.MAX_VALUE) : (int)(Math.random() * Integer.MIN_VALUE);
	public static Chunk generateChunk(int x, int y) {
//		return (Math.random() > .5) ? (new Chunk(x, y, true, Stone.get())) : (new Chunk(x, y, true, Quicksand.get()));
		return new Chunk(x, y, true, Quicksand.get());
		//TODO finish this.
	}
	public static Chunk generateChunk(Point p) {
		return new Chunk(p, true, Quicksand.get());
	}
	
	public static int getGenValue(int x, int y) {
		return Tools.transform.msqrt(seed + Tools.transform.msqrt(x) + Tools.transform.msqrt(y));
	}
}
