package world;

import java.util.Vector;

import entity.Entity;

public class LoadedChunks {
	public static Chunk[][] chunks;
	public static final int RADIUS = 15;
	public static Chunk center;
	
	static {
		chunks = new Chunk[RADIUS * 2 + 1][RADIUS * 2 + 1];
		center = null;
	}
	
//	public static void setCenter(int x, int y) {
//		
//	}
	
//	public LoadedChunks(Chunk center) {
//		LoadedChunks.center = center;
//		chunks[RADIUS][RADIUS] = center;
//		reload();
//	}
	
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
		}
	}
	
	public static void shiftRight() {
		
	}
	
	public static void shiftUp() {
		
	}
	
	public static void shiftDown() {
		
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
	
	public static int getTopLeftX() {
		return chunks[0][0].xPos;
	}
	public static int getTopLeftY() {
		return chunks[0][0].yPos;
	}
	
	public static String spriteAt(int absoluteX, int absoluteY) {
//		System.out.println(absoluteX/Chunk.GRID_DIM + " - " + chunks[0][0].xPos);
//		System.out.println(((absoluteX%Chunk.GRID_DIM + Chunk.GRID_DIM) % Chunk.GRID_DIM) + " " + ((absoluteY%Chunk.GRID_DIM + Chunk.GRID_DIM) % Chunk.GRID_DIM));
		return chunks[absoluteX/Chunk.GRID_DIM - chunks[0][0].xPos][absoluteY/Chunk.GRID_DIM - chunks[0][0].yPos].
				terrainAt((absoluteX%Chunk.GRID_DIM + Chunk.GRID_DIM) % Chunk.GRID_DIM, (absoluteY%Chunk.GRID_DIM + Chunk.GRID_DIM) % Chunk.GRID_DIM).toString();/*spriteFilepath;*/
	}
	
	public static int heightAt(int absoluteX, int absoluteY) {
		return chunks[chunks[0][0].xPos + absoluteX/Chunk.GRID_DIM][chunks[0][0].yPos + absoluteY/Chunk.GRID_DIM].heightAt(absoluteX%Chunk.GRID_DIM, absoluteY%Chunk.GRID_DIM);
	}
	
	public static Vector<Entity> entitiesIn(int x1, int y1, int x2, int y2) { //bounds inclusive
		int x1c = x1/Chunk.GRID_DIM - chunks[0][0].xPos;
		int y1c = y1/Chunk.GRID_DIM - chunks[0][0].yPos;
		int x2c = x2/Chunk.GRID_DIM - chunks[0][0].xPos;
		int y2c = y2/Chunk.GRID_DIM - chunks[0][0].yPos;
		Vector<Entity> ret = new Vector<Entity>();
		for(int i = x1c; i < x2c + 1; i++) {
			for(int j = y1c; i < y2c + 1; i++) {
				for(Entity e : chunks[i][j].entities) {
					if(e.getAbsoluteX() >= x1 && e.getAbsoluteX() <= x2 && e.getAbsoluteY() >= y1 && e.getAbsoluteY() <= y2) ret.add(e);
				}
			}
		}
		return ret;
	}
}
