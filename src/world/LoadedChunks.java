package world;

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
	
	public LoadedChunks(Chunk center) {
		this.center = center;
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
			chunks[i][0] = chunks[i -1][0].getNeighbor(0, 1);
			if(chunks[i][0] == null) chunks[i][0] = Generator.generateChunk(center.xPos - RADIUS + i, center.yPos - RADIUS);
		}
		for(int i = 0; i < 2 * RADIUS + 1; i++) {
			for(int j = 1; j < 2 * RADIUS + 1; j++) {
				chunks[i][j] = chunks[i][j-1].getNeighbor(1, 0);
				if(chunks[i][j] == null) chunks[i][0] = Generator.generateChunk(center.xPos - RADIUS + i, center.yPos - RADIUS + j);
			}
		}
	}
}
