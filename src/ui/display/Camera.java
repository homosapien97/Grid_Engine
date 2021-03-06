package ui.display;

import java.awt.Image;
import java.util.List;
//import java.util.Vector;











import core.Clock;
import action.Action;
import action.MoveAction;
import action.SpellAction;
import entity.Player;
import entity.Entity;
import geometry.Point;
import geometry.PointCollection;
import world.LoadedChunks;
import terrain.Empty;
import ui.key_actions.PreviewCommand;

public class Camera {
	public static final char PLAYER_PATH = 'r';	//redHighlight
	public static final char MOB_PATH = 'c';	//cyanHighlight
	public static final char PLAYER_SPELL = 'p';//purpleHighlight
	public static final char MOB_SPELL = 'b';	//blueHighlight
	public static final char PREVIEW = 'g';		//greenHighlight
	public static final char NONE = 'n';		//no highlight
	
//	public static Player player;
	
	//public static String[][] snapshot;
	public static String[][] terrainSnapshot;
	public static String[][] entitySnapshot;
	public static Image[][] terrainImageSnapshot;
	public static Image[][] entityImageSnapshot;
	public static char[][] highlightSnapshot;
	
	public static List<Entity> entities;
	public static List<Action> actions;
	
	public static boolean initialized;
	
	static {
		//snapshot = new String[Display.WIDTH][Display.HEIGHT];
		entitySnapshot = new String[Display.WIDTH][Display.HEIGHT];
		terrainSnapshot = new String[Display.WIDTH][Display.HEIGHT];
		terrainImageSnapshot = new Image[Display.WIDTH][Display.HEIGHT];
		entityImageSnapshot = new Image[Display.WIDTH][Display.HEIGHT];
		highlightSnapshot = new char[Display.WIDTH][Display.HEIGHT];
	}
	
//	public static boolean init(Player p) {
//		if(initialized) return false;
//		initialized = true;
//		Player.player = p;
//		return true;
//	}
	
	private static int pAbsX;
	private static int pAbsY;
	
	public static String[][] terrainSpriteFilenameSnapshot() {
		pAbsX = Player.player.getAbsoluteX();
		pAbsY = Player.player.getAbsoluteY();
		
		for(int j = 0; j < Display.HEIGHT; j++) {
			for(int i = 0; i < Display.WIDTH; i++) {
				if(Player.player.visionSquare.canSee(pAbsX - Display.WIDTH / 2 + i, pAbsY - Display.HEIGHT / 2 + j)) {
					terrainSnapshot[i][j] = LoadedChunks.terrainSpriteFilenameAt(pAbsX - Display.WIDTH / 2 + i, pAbsY - Display.HEIGHT / 2 + j);
				} else terrainSnapshot[i][j] = Empty.get().toString();
			}
		}
		
		return terrainSnapshot;
	}
	
	public static Image[][] terrainImageSnapshot() {
		pAbsX = Player.player.getAbsoluteX();
		pAbsY = Player.player.getAbsoluteY();
		synchronized(Player.player.visionSquare) {
			Player.player.visionSquare.trace(Player.player.getAbsoluteX(), Player.player.getAbsoluteY());
			for(int j = 0; j < Display.HEIGHT; j++) {
				for(int i = 0; i < Display.WIDTH; i++) {
					if(Player.player.visionSquare.canSee(pAbsX - Display.WIDTH / 2 + i, pAbsY - Display.HEIGHT / 2 + j)) {
						terrainImageSnapshot[i][j] = LoadedChunks.terrainSpriteAt(pAbsX - Display.WIDTH / 2 + i, pAbsY - Display.HEIGHT / 2 + j);
					} else {
						terrainImageSnapshot[i][j] = Empty.sprite;
					}
				}
			}
		}
		return terrainImageSnapshot;
	}
	
	public static String[][] entitySnapshot() {
		//TODO rewrite this to use images, once we have them, and update GameDisplay entity handling. 
		synchronized(Player.player.visionSquare) {
			Player.player.visionSquare.trace(Player.player.getAbsoluteX(), Player.player.getAbsoluteY());
			entities = LoadedChunks.entitiesIn(pAbsX - Display.WIDTH/2, pAbsY - Display.HEIGHT/2, pAbsX + Display.WIDTH/2, pAbsY + Display.HEIGHT/2);
			
			for(Entity e : entities) {
				if(Player.player.visionSquare.canSee(e.getAbsoluteX(), e.getAbsoluteY())) {
					entitySnapshot[Display.WIDTH/2 + e.getAbsoluteX() - pAbsX][Display.HEIGHT/2 + e.getAbsoluteY() - pAbsY] = e.filename;
				}
			}
		}
		return entitySnapshot;
	}
	public static Image[][] entityImageSnapshot() {
		//TODO rewrite this to use images, once we have them, and update GameDisplay entity handling.
		pAbsX = Player.player.getAbsoluteX();
		pAbsY = Player.player.getAbsoluteY();
		synchronized(Player.player.visionSquare) {
			Player.player.visionSquare.trace(pAbsX, pAbsY);
			entities = LoadedChunks.entitiesIn(pAbsX - Display.WIDTH/2, pAbsY - Display.HEIGHT/2, pAbsX + Display.WIDTH/2, pAbsY + Display.HEIGHT/2);
			for(int i = 0; i < entityImageSnapshot.length; i++) {
				for(int j = 0; j < entityImageSnapshot[0].length; j++) {
					entityImageSnapshot[i][j] = null;
				}
			}
			for(Entity e : entities) {
				if(Player.player.visionSquare.canSee(e.getAbsoluteX(), e.getAbsoluteY())) {
					entityImageSnapshot[Display.WIDTH/2 + e.getAbsoluteX() - pAbsX][Display.HEIGHT/2 + e.getAbsoluteY() - pAbsY] = e.sprite();
				}
			}
		}
		return entityImageSnapshot;
	}
	private static char tempChar;
	private static PointCollection tempPC;
	public static char[][] highlightSnapshot() {
		pAbsX = Player.player.getAbsoluteX();
		pAbsY = Player.player.getAbsoluteY();
		actions = Action.toHighlight();
		for(int i = 0; i < highlightSnapshot.length; i++) {
			for(int j = 0; j < highlightSnapshot[0].length; j++) {
				highlightSnapshot[i][j] = NONE;
			}
		}
		synchronized(actions) {
			for(Action a : actions) {
				if(Clock.ticks >= a.startTime) {
					if(a instanceof MoveAction) {
						if(a.actor instanceof Player) {
							tempChar = PLAYER_PATH;
						} else {
							tempChar = MOB_PATH;
						}
					} else if(a instanceof SpellAction) {
						if(a.actor instanceof Player) {
							tempChar = PLAYER_SPELL;
						} else {
							tempChar = MOB_SPELL;
						}
					} else {
						if(a.actor instanceof Player) {
							tempChar = PREVIEW;
						}
					}
					tempPC = a.pointsToHighlight();
					if(tempPC != null) {
						for(Point p : a.pointsToHighlight()) {
							if(Display.WIDTH / 2 + p.x - pAbsX >= 0 && Display.WIDTH / 2 + p.x - pAbsX < highlightSnapshot.length
									&& Display.HEIGHT / 2 + p.y - pAbsY >= 0 && Display.HEIGHT / 2 + p.y - pAbsY < highlightSnapshot[0].length)
							{
								highlightSnapshot[Display.WIDTH/2 + p.x - pAbsX][Display.HEIGHT/2 + p.y - pAbsY] = tempChar;
							}
						}
					}
				}
			}
		}
		if(PreviewCommand.current != null) {
			for(Point p : PreviewCommand.current) {
				highlightSnapshot[Display.WIDTH/2 + p.x - pAbsX][Display.HEIGHT/2 + p.y - pAbsY] = PREVIEW;
			}
		}
		return highlightSnapshot;
	}
}
