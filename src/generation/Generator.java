package generation;

import entity.creature.Skeleton;
import geometry.Point;
import terrain.Quicksand;
import terrain.Stone;
import tools.Tools;
import world.Chunk;

public class Generator {
	public static final char FLOOR = 'o';
	public static final char WALL = 'x';
	
	
	public static int seed = (Math.random() > .5) ? (int)(Math.random() * Integer.MAX_VALUE) : (int)(Math.random() * Integer.MIN_VALUE);
	public static Chunk generateChunk(int x, int y) {
//		return (Math.random() > .5) ? (new Chunk(x, y, true, Stone.get())) : (new Chunk(x, y, true, Quicksand.get()));
		return new Chunk(x, y, true, Quicksand.get());
		//TODO finish this.
	}
	public static Chunk generateChunk(Point p) {
		return new Chunk(p, true, Quicksand.get());
	}
	
	private static int tempx;
	private static int tempy;
	public static Chunk generateFromLevel(Point p, char[][] chars) {
		Chunk ret = new Chunk(p, true, Stone.get());
		tempx = chars.length / 2 + Chunk.DIM * p.x;
		tempy = chars[0].length / 2 + Chunk.DIM * p.y;
		for(int i = 0; i < Chunk.DIM; i++) {
			for(int j = 0; j < Chunk.DIM; j++) {
				switch(chars[tempx + i][tempy + j]) {
				case FLOOR:
					ret.heightmap[i][j] = 0;
					break;
				case WALL:
					ret.heightmap[i][j] = 1;
					break;
				default:
					break;
				}
			}
		}
		int numSkellys = ((int) (Math.random() * 5));
		for(int i = 0; i < numSkellys; i++) {
			new Skeleton(p.x * Chunk.DIM + (int)(Math.random() * Chunk.DIM), p.y * Chunk.DIM + (int)(Math.random() * Chunk.DIM), ret);
		}
		return ret;
	}
	
	public static int getGenValue(int x, int y) {
		return Tools.transform.msqrt(seed + Tools.transform.msqrt(x) + Tools.transform.msqrt(y));
	}
}
