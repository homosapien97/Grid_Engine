package display;

import java.awt.Image;
import java.util.Vector;

import entity.Player;
import entity.Entity;
import world.LoadedChunks;
import terrain.Void;

public class Camera {
	public static Player player;
	
	//public static String[][] snapshot;
	public static String[][] terrainSnapshot;
	public static String[][] entitySnapshot;
	public static Image[][] terrainImageSnapshot;
	
	public static Vector<Entity> entities;
	
	public static boolean initialized;
	
	static {
		//snapshot = new String[Display.WIDTH][Display.HEIGHT];
		entitySnapshot = new String[Display.WIDTH][Display.HEIGHT];
		terrainSnapshot = new String[Display.WIDTH][Display.HEIGHT];
		terrainImageSnapshot = new Image[Display.WIDTH][Display.HEIGHT];
	}
	
	public static boolean init(Player p) {
		if(initialized) return false;
		initialized = true;
		player = p;
		return true;
	}
	
	private static int pAbsX;
	private static int pAbsY;
	
	public static String[][] terrainSpriteFilenameSnapshot() {
		pAbsX = player.getAbsoluteX();
		pAbsY = player.getAbsoluteY();
		
		for(int j = 0; j < Display.HEIGHT; j++) {
			for(int i = 0; i < Display.WIDTH; i++) {
				if(player.visionSquare.canSee(pAbsX - Display.WIDTH / 2 + i, pAbsY - Display.HEIGHT / 2 + j)) {
					terrainSnapshot[i][j] = LoadedChunks.terrainSpriteFilenameAt(pAbsX - Display.WIDTH / 2 + i, pAbsY - Display.HEIGHT / 2 + j);
				} else terrainSnapshot[i][j] = Void.get().toString();
			}
		}
		
		return terrainSnapshot;
	}
	
	public static Image[][] terrainImageSnapshot() {
		pAbsX = player.getAbsoluteX();
		pAbsY = player.getAbsoluteY();
		player.visionSquare.trace(player.getAbsoluteX(), player.getAbsoluteY());
		for(int j = 0; j < Display.HEIGHT; j++) {
			for(int i = 0; i < Display.WIDTH; i++) {
				if(player.visionSquare.canSee(pAbsX - Display.WIDTH / 2 + i, pAbsY - Display.HEIGHT / 2 + j)) {
					terrainImageSnapshot[i][j] = LoadedChunks.terrainSpriteAt(pAbsX - Display.WIDTH / 2 + i, pAbsY - Display.HEIGHT / 2 + j);
				} else {
					terrainImageSnapshot[i][j] = Void.sprite;
				}
			}
		}
		
		return terrainImageSnapshot;
	}
	
	public static String[][] entitySnapshot() {
		//TODO rewrite this to use images, once we have them, and update GameDisplay entity handling. 
		player.visionSquare.trace(player.getAbsoluteX(), player.getAbsoluteY());
		entities = LoadedChunks.entitiesIn(pAbsX - Display.WIDTH/2, pAbsY - Display.HEIGHT/2, pAbsX + Display.WIDTH/2, pAbsY + Display.HEIGHT/2);
		
		for(Entity e : entities) {
			if(player.visionSquare.canSee(e.getAbsoluteX(), e.getAbsoluteY())) {
				entitySnapshot[Display.WIDTH/2 + e.getAbsoluteX() - pAbsX][Display.HEIGHT/2 + e.getAbsoluteY() - pAbsY] = e.spriteFilepath;
			}
		}

		return entitySnapshot;
	}
}
