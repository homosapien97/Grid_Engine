package world.generation;

import geometry.Point;
import terrain.Brick;
import terrain.Empty;
import terrain.Lava;
import terrain.Quicksand;
import terrain.Stone;
import terrain.StoneBrick;
import terrain.StoneTile;
import terrain.Water;
import terrain.Wood;
import tools.Tools;
import world.Chunk;

public class Generator {
	public static final char FLOOR_STONE = 'o';
	public static final char FLOOR_QUICKSAND = 'q';
	public static final char WALL = 'x';
	public static final char FLOOR_BRICK = 'c';
	public static final char FLOOR_STONE_BRICK = 'b';
	public static final char BAD_EMPTY = 'd';
	public static final char BAD_LAVA = 'e';
	public static final char FLOOR_STONE_TILE = 'f';
	public static final char BAD_WATER = 'g';
	public static final char FLOOR_WOOD = 'h';
	
	
	public static int seed = (Math.random() > .5) ? (int)(Math.random() * Integer.MAX_VALUE) : (int)(Math.random() * Integer.MIN_VALUE);
	public static Chunk generateChunk(int x, int y) {
//		return (Math.random() > .5) ? (new Chunk(x, y, true, Stone.get())) : (new Chunk(x, y, true, Quicksand.get()));
//		return new Chunk(x, y, true, Quicksand.get());
		return generateFromLevel(new Point(x, y), Map_maker.map);
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
		if(tempx >= 0 && tempx < chars.length && tempy >= 0 && tempy < chars[0].length) {
			for(int i = 0; i < Chunk.DIM; i++) {
				for(int j = 0; j < Chunk.DIM; j++) {
					switch(chars[tempx + i][tempy + j]) {
					case FLOOR_STONE:
//						ret.heightmap[i][j] = 0;
						ret.terrain[i][j] = Stone.get();
						break;
					case FLOOR_QUICKSAND:
						ret.terrain[i][j] = Quicksand.get();
						break;
					case FLOOR_BRICK:
						ret.terrain[i][j] = Brick.get();
					case FLOOR_STONE_BRICK:
						ret.terrain[i][j] = StoneBrick.get();
						break;
					case BAD_EMPTY:
						ret.terrain[i][j] = Empty.get();
						break;
					case BAD_LAVA:
						ret.terrain[i][j] = Lava.get();
						break;
					case FLOOR_STONE_TILE:
						ret.terrain[i][j] = StoneTile.get();
						break;
					case BAD_WATER:
						ret.terrain[i][j] = Water.get();
						break;
					case FLOOR_WOOD:
						ret.terrain[i][j] = Wood.get();
						break;
					case WALL:
						ret.heightmap[i][j] = 1;
						break;
					default:
						break;
					}
				}
			}
		}
//		else {
//			
//		}
		return ret;
	}
	
	public static int getGenValue(int x, int y) {
		return Tools.transform.msqrt(seed + Tools.transform.msqrt(x) + Tools.transform.msqrt(y));
	}
}
