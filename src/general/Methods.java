package general;

import world.Chunk;

public class Methods {
	public static int absCoordToChunkCoord(int abs) {
		return (abs > -1) ? (abs/Chunk.DIM) : ((abs - Chunk.DIM + 1) / (Chunk.DIM));
	}
	public static int absCoordToMinorCoord(int abs) {
		return (abs > -1) ? (abs % Chunk.DIM) : ((abs % Chunk.DIM + Chunk.DIM) % Chunk.DIM);
	}
}
