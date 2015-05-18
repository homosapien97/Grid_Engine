package display;

import java.util.Vector;

import entity.Player;
import entity.Entity;
import world.LoadedChunks;

public class Camera {
	public static Player player;
	
	//public static String[][] snapshot;
	public static String[][] terrainSnapshot;
	public static String[][] entitySnapshot;
	
	public static Vector<Entity> entities;
	
	public static boolean initialized;
	
	static {
		//snapshot = new String[Display.WIDTH][Display.HEIGHT];
		entitySnapshot = new String[Display.WIDTH][Display.HEIGHT];
		terrainSnapshot = new String[Display.WIDTH][Display.HEIGHT];
	}
	
	public static boolean init(Player p) {
		if(initialized) return false;
		initialized = true;
		player = p;
		return true;
	}
	
	private static int pAbsX;
	private static int pAbsY;
	
	public static String[][] terrainSnapshot() {
		pAbsX = player.getAbsoluteX();
		pAbsY = player.getAbsoluteY();
		
		for(int j = 0; j < Display.HEIGHT; j++) {
			for(int i = 0; i < Display.WIDTH; i++) {
				terrainSnapshot[i][j] = LoadedChunks.terrainSpriteAt(pAbsX - Display.WIDTH / 2 + i, pAbsY - Display.HEIGHT / 2 + j);
			}
		}
		
		return terrainSnapshot;
	}
	
	public static String[][] entitySnapshot() {
		entities = LoadedChunks.entitiesIn(pAbsX - Display.WIDTH/2, pAbsY - Display.HEIGHT/2, pAbsX + Display.WIDTH/2, pAbsY + Display.HEIGHT/2);
		
		for(Entity e : entities) {
			entitySnapshot[Display.WIDTH/2 + e.getAbsoluteX() - pAbsX][Display.HEIGHT/2 + e.getAbsoluteY() - pAbsY] = e.spriteFilepath;
		}

		return entitySnapshot;
	}
}
