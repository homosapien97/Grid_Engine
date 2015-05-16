package display;

import entity.Player;
import world.Chunk;
import world.LoadedChunks;

public class Camera {
	public static Player player;
	
	public static String[][] snapshot;
	
	public static boolean initialized;
	
	static {
		snapshot = new String[Display.WIDTH][Display.HEIGHT];
	}
	
	public static boolean init(Player p) {
		if(initialized) return false;
		initialized = true;
		player = p;
		return true;
	}
	
	public static String[][] snapshot() {
		for(int j = 0; j < Display.HEIGHT; j++) {
			for(int i = 0; i < Display.WIDTH; i++) {
				snapshot[i][j] = LoadedChunks.spriteAt(player.getAbsoluteX() - Display.WIDTH / 2 + i, player.getAbsoluteY() - Display.HEIGHT / 2 + j);
//				System.out.print(snapshot[i][j]);
//				System.out.print(player.getAbsoluteX() - Display.WIDTH / 2 + i + " ");
//				System.out.print(LoadedChunks.spriteAt(player.getAbsoluteX() - Display.WIDTH /2 + i, player.getAbsoluteY() - Display.HEIGHT/2 + j));
			}
//			System.out.println();
		}
//		snapshot[Display.WIDTH/2][Display.HEIGHT/2] = "0";
//		snapshot[Display.WIDTH/2][Display.HEIGHT/2 + Chunk.DIM] = "1";
//		snapshot[Display.WIDTH/2 + Chunk.DIM][Display.HEIGHT/2] = "*";
		return snapshot;
	}
}
