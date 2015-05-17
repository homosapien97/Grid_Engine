package world;

import java.util.Vector;

import entity.Entity;
import general.Methods;

public class LoadedChunks {
	public static Chunk[][] chunks;
	public static final int RADIUS = 15;
	public static Chunk center;
	
	static {
		chunks = new Chunk[RADIUS * 2 + 1][RADIUS * 2 + 1];
		center = null;
	}
	
	public static void init(Chunk center) {
		LoadedChunks.center = center;
		chunks[RADIUS][RADIUS] = center;
		reload();
	}
	
	public static void setCenter(Chunk center) {
		LoadedChunks.center = center;
	}
	
	public static void shiftLeft() {
		for(int i = 0; i < 2 * RADIUS; i++) {
			for(int j = 0; j < 2 * RADIUS + 1; j++) {
				chunks[i][j] = chunks[i+1][j];
			}
		}
		for(int j = 0; j < 2 * RADIUS + 1; j++) {
			chunks[2 * RADIUS][j] = chunks[ 2 * RADIUS - 1][j].getNeighbor(2, 1);
			if(chunks[2*RADIUS][j] == null) chunks[2*RADIUS][j] = Generator.generateChunk(2*RADIUS + chunks[0][0].xPos, j + chunks[0][0].yPos);
		}
	}
	
	public static void shiftRight() {
		//TODO
	}
	
	public static void shiftUp() {
		//TODO
	}
	
	public static void shiftDown() {
		//TODO
	}
	
	public static void reload() {
		for(int i = RADIUS - 1; i > -1; i--) {
			chunks[i][i] = chunks[i + 1][i + 1].getNeighbor(0, 0);
			if(chunks[i][i] == null) chunks[i][i] = Generator.generateChunk(center.xPos - RADIUS + i, center.yPos - RADIUS + i);
		}
		
		for(int i = 1; i < 2 * RADIUS + 1; i++) {
			chunks[i][0] = chunks[i -1][0].getNeighbor(2, 1);
			if(chunks[i][0] == null) chunks[i][0] = Generator.generateChunk(center.xPos - RADIUS + i, center.yPos - RADIUS);
		}
		
		for(int i = 0; i < 2 * RADIUS + 1; i++) {
			for(int j = 1; j < 2 * RADIUS + 1; j++) {
				chunks[i][j] = chunks[i][j-1].getNeighbor(1, 2);
				if(chunks[i][j] == null) chunks[i][j] = Generator.generateChunk(center.xPos - RADIUS + i, center.yPos - RADIUS + j);
			}
		}
	}
	public static void reload(Chunk center) {
		LoadedChunks.center = center;
		for(int i = RADIUS - 1; i > -1; i--) {
			chunks[i][i] = chunks[i + 1][i + 1].getNeighbor(0, 0);
			if(chunks[i][i] == null) chunks[i][i] = Generator.generateChunk(center.xPos - RADIUS + i, center.yPos - RADIUS + i);
		}
		
		for(int i = 1; i < 2 * RADIUS + 1; i++) {
			chunks[i][0] = chunks[i -1][0].getNeighbor(2, 1);
			if(chunks[i][0] == null) chunks[i][0] = Generator.generateChunk(center.xPos - RADIUS + i, center.yPos - RADIUS);
		}
		
		for(int i = 0; i < 2 * RADIUS + 1; i++) {
			for(int j = 1; j < 2 * RADIUS + 1; j++) {
				chunks[i][j] = chunks[i][j-1].getNeighbor(1, 2);
				if(chunks[i][j] == null) chunks[i][j] = Generator.generateChunk(center.xPos - RADIUS + i, center.yPos - RADIUS + j);
			}
		}
	}
	
	public static int getTopLeftX() {
		return chunks[0][0].xPos;
	}
	public static int getTopLeftY() {
		return chunks[0][0].yPos;
	}
	/**
	 * Returns whether a specific absolute point is within LoadedChunks
	 * @param absoluteX absolute x coordinate
	 * @param absoluteY absolute y coordinate
	 * @return is x,y in LoadedChunks?
	 */
	public static boolean isLoaded(int absoluteX, int absoluteY) {
		absoluteX = Methods.absCoordToChunkCoord(absoluteX);
		absoluteY = Methods.absCoordToChunkCoord(absoluteY);
		return absoluteX >= chunks[0][0].xPos && absoluteX <= chunks[2*RADIUS][2*RADIUS].xPos && absoluteY >= chunks[0][0].yPos && absoluteY <= chunks[2*RADIUS][2*RADIUS].yPos;
	}
	
	/**
	 * Returns the sprite from terrain at the indicated absolute coordinates
	 * @param absoluteX absolute x coordinate
	 * @param absoluteY absolute y coordinate
	 * @return the filepath of the sprite at x,y
	 */
	public static String terrainSpriteAt(int absoluteX, int absoluteY) {
//		System.out.println(absoluteX/Chunk.GRID_DIM + " - " + chunks[0][0].xPos);
//		System.out.println(((absoluteX%Chunk.GRID_DIM + Chunk.GRID_DIM) % Chunk.GRID_DIM) + " " + ((absoluteY%Chunk.GRID_DIM + Chunk.GRID_DIM) % Chunk.GRID_DIM));
		return chunks[Methods.absCoordToChunkCoord(absoluteX) - chunks[0][0].xPos][Methods.absCoordToChunkCoord(absoluteY) - chunks[0][0].yPos].
				terrainAt(Methods.absCoordToMinorCoord(absoluteX), Methods.absCoordToMinorCoord(absoluteY)).toString();/*spriteFilepath;*/
	}
	/**
	 * Returns the height at the indicated absolute coordinates
	 * @param absoluteX absolute x coordinate
	 * @param absoluteY absolute y coordinate
	 * @return height at x, y
	 */
	public static int heightAt(int absoluteX, int absoluteY) {
		return chunks[chunks[0][0].xPos + Methods.absCoordToChunkCoord(absoluteX)][chunks[0][0].yPos + Methods.absCoordToChunkCoord(absoluteY)].
				heightAt(Methods.absCoordToMinorCoord(absoluteX), Methods.absCoordToMinorCoord(absoluteY));
	}
	/**
	 * Returns a vector containing all entities within the given range, inclusive.
	 * @param x1 x coordinate of the top left corner of the range
	 * @param y1 y coordinate of the top left corner of the range
	 * @param x2 x coordinate of the bottom right corner of the range
	 * @param y2 y coordinate of the bottom right corner of the range
	 * @return vector of entities within (x1,y1)->(x2,y2)
	 */
	public static Vector<Entity> entitiesIn(int x1, int y1, int x2, int y2) { //bounds inclusive
		int x1c = Methods.absCoordToChunkCoord(x1) - chunks[0][0].xPos;
		int y1c = Methods.absCoordToChunkCoord(y1) - chunks[0][0].yPos;
		int x2c = Methods.absCoordToChunkCoord(x2) - chunks[0][0].xPos;
		int y2c = Methods.absCoordToChunkCoord(y2) - chunks[0][0].yPos;
		Vector<Entity> ret = new Vector<Entity>();
		for(int i = x1c; i < x2c + 1; i++) {
			for(int j = y1c; j < y2c + 1; j++) {
				for(Entity e : chunks[i][j].entities) {
					if(e.getAbsoluteX() >= x1 && e.getAbsoluteX() <= x2 && e.getAbsoluteY() >= y1 && e.getAbsoluteY() <= y2) ret.add(e);
				}
			}
		}
		return ret;
	}
}
