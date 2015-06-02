package display;

import java.awt.Image;
import java.util.List;
//import java.util.Vector;



import action.Action;
import entity.Player;
import entity.Entity;
import geometry.Point;
import world.LoadedChunks;
import terrain.Empty;

public class Camera {
	public static Player player;
	
	//public static String[][] snapshot;
	public static String[][] terrainSnapshot;
	public static String[][] entitySnapshot;
	public static Image[][] terrainImageSnapshot;
	public static Image[][] entityImageSnapshot;
	public static boolean[][] highlightSnapshot;
	
	public static List<Entity> entities;
	public static List<Action> actions;
	
	public static boolean initialized;
	
	static {
		//snapshot = new String[Display.WIDTH][Display.HEIGHT];
		entitySnapshot = new String[Display.WIDTH][Display.HEIGHT];
		terrainSnapshot = new String[Display.WIDTH][Display.HEIGHT];
		terrainImageSnapshot = new Image[Display.WIDTH][Display.HEIGHT];
		entityImageSnapshot = new Image[Display.WIDTH][Display.HEIGHT];
		highlightSnapshot = new boolean[Display.WIDTH][Display.HEIGHT];
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
				} else terrainSnapshot[i][j] = Empty.get().toString();
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
					terrainImageSnapshot[i][j] = Empty.sprite;
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
				entitySnapshot[Display.WIDTH/2 + e.getAbsoluteX() - pAbsX][Display.HEIGHT/2 + e.getAbsoluteY() - pAbsY] = e.filename;
			}
		}

		return entitySnapshot;
	}
	public static Image[][] entityImageSnapshot() {
		//TODO rewrite this to use images, once we have them, and update GameDisplay entity handling.
		pAbsX = player.getAbsoluteX();
		pAbsY = player.getAbsoluteY();
		player.visionSquare.trace(pAbsX, pAbsY);
		entities = LoadedChunks.entitiesIn(pAbsX - Display.WIDTH/2, pAbsY - Display.HEIGHT/2, pAbsX + Display.WIDTH/2, pAbsY + Display.HEIGHT/2);
		for(int i = 0; i < entityImageSnapshot.length; i++) {
			for(int j = 0; j < entityImageSnapshot[0].length; j++) {
				entityImageSnapshot[i][j] = null;
			}
		}
		for(Entity e : entities) {
			if(player.visionSquare.canSee(e.getAbsoluteX(), e.getAbsoluteY())) {
				entityImageSnapshot[Display.WIDTH/2 + e.getAbsoluteX() - pAbsX][Display.HEIGHT/2 + e.getAbsoluteY() - pAbsY] = e.sprite();
			}
		}

		return entityImageSnapshot;
	}
	public static boolean[][] highlightSnapshot() {
		pAbsX = player.getAbsoluteX();
		pAbsY = player.getAbsoluteY();
		actions = Action.toHighlight();
		for(int i = 0; i < highlightSnapshot.length; i++) {
			for(int j = 0; j < highlightSnapshot[0].length; j++) {
				highlightSnapshot[i][j] = false;
			}
		}
		for(Action a : actions) {
			for(Point p : a.pointsToHighlight()) {
				highlightSnapshot[Display.WIDTH/2 + p.x - pAbsX][Display.HEIGHT/2 + p.y - pAbsY] = true;
			}
		}
		return highlightSnapshot;
	}
}
