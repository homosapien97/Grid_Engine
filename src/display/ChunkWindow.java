package display;

import world.Chunk;

/*
 * 
 */
public class ChunkWindow {
	Chunk[][] chunks;
	int topLeftCornerX; //x position of the top left corner pixel in chunks[0][0];
	int topLeftCornerY;
	
	int botRightCornerX;
	int botRightCornerY;
	
	
	
	private int getLeftDistanceChunks(int xPosInChunk) {
		int left_sprites_past_center_chunk = (Display.WIDTH / 2 - Display.SPRITE_DIM / 2) / Display.SPRITE_DIM - xPosInChunk;
		int whole_left_chunks = left_sprites_past_center_chunk / Chunk.GRID_DIM;
		//I will get rid of these variables when I'm sure this works.
		if(left_sprites_past_center_chunk % Chunk.GRID_DIM == 0) {
			return whole_left_chunks;
		}
		return whole_left_chunks + 1;
	}
	private int getRightDistanceChunks(int xPosInChunk) {
		int right_sprites_past_center_chunk = (Display.WIDTH / 2 - Display.SPRITE_DIM / 2) / Display.SPRITE_DIM - (Chunk.GRID_DIM - xPosInChunk - 1);
		int whole_right_chunks = right_sprites_past_center_chunk / Chunk.GRID_DIM;
		//I will get rid of / efficientize variables when I'm sure this works...
		if(right_sprites_past_center_chunk % Chunk.GRID_DIM == 0) {
			return whole_right_chunks;
		}
		return whole_right_chunks;
	}
	private int getUpDistanceChunks(int yPosInChunk) {
		int up_sprites_past_center_chunk = (Display.HEIGHT / 2 - Display.SPRITE_DIM / 2) / Display.SPRITE_DIM - yPosInChunk;
		int whole_up_chunks = up_sprites_past_center_chunk / Chunk.GRID_DIM;
		//I will get rid of these variables when I'm sure this works.
		if(up_sprites_past_center_chunk % Chunk.GRID_DIM == 0) {
			return whole_up_chunks;
		}
		return whole_up_chunks + 1;
	}
	private int getDownDistanceChunks(int yPosInChunk) {
		int down_sprites_past_center_chunk = (Display.WIDTH / 2 - Display.SPRITE_DIM / 2) / Display.SPRITE_DIM - (Chunk.GRID_DIM - yPosInChunk - 1);
		int whole_down_chunks = down_sprites_past_center_chunk / Chunk.GRID_DIM;
		//I will get rid of / efficientize variables when I'm sure this works...
		if(down_sprites_past_center_chunk % Chunk.GRID_DIM == 0) {
			return whole_down_chunks;
		}
		return whole_down_chunks;
	}
	private void fillLeft() {
		for(int i = chunks.length / 2 - 1; i > -1; i--) {
			chunks[i][chunks[0].length / 2] = chunks[i + 1][chunks[0].length / 2].getNeighbor(0,1);
		}
	}
	public void update(Chunk center, int xPosInChunk, int yPosInChunk) {
		chunks = new Chunk[getLeftDistanceChunks(xPosInChunk) + getRightDistanceChunks(xPosInChunk) + 1][getUpDistanceChunks(yPosInChunk) + getDownDistanceChunks(yPosInChunk) + 1];
		chunks[chunks.length / 2][chunks[0].length / 2] = center;
	}
}
