package general;

import entity.Entity;
import entity.Player;
import world.Chunk;

public class Methods {
	public static int absCoordToChunkCoord(int abs) {
		return (abs > -1) ? (abs/Chunk.DIM) : ((abs - Chunk.DIM + 1) / (Chunk.DIM));
	}
	public static int absCoordToMinorCoord(int abs) {
		return (abs > -1) ? (abs % Chunk.DIM) : ((abs % Chunk.DIM + Chunk.DIM) % Chunk.DIM);
	}
	public static int orthoDistance(int x1, int y1, int x2, int y2) {
		return ((x1 > x2) ? (x1 - x2) : (x2 - x1)) + ((y1 > y2) ? (y1 - y2) : (y2 - y1));
	}
	
}
