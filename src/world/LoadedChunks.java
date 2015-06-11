package world;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.util.Vector;






import terrain.Terrain;
import tools.Tools;
import world.generation.Generator;
import entity.Entity;
import entity.Player;
import entity.creature.Skeleton;
import entity.interfaces.Intelligent;
//import entity.Player;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.PointCollection;
import geometry.Ray;
import geometry.Rectangle;

public class LoadedChunks {
	public static Chunk[][] chunks;
	public static final int RADIUS = 15;
	public static final int UPDATE_RADIUS = 4;
	public static Chunk center;
	
	static {
		chunks = new Chunk[RADIUS * 2 + 1][RADIUS * 2 + 1];
		center = null;
//		Player temp = Player.player;
//		init(Player.player.chunk);
	}
	
	public static void init(Chunk center) {
		LoadedChunks.center = center;
		chunks[RADIUS][RADIUS] = center;
		reloadCurrent();
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
			if(chunks[2*RADIUS][j] == null) chunks[2*RADIUS][j] = Generator.generateChunk(2*RADIUS + chunks[0][0].pos.x, j + chunks[0][0].pos.y);
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
	
	public static void reloadCurrent() {
		chunks = Chunk.loadChunks(center.pos.x - RADIUS, center.pos.y - RADIUS, 2 * RADIUS + 1, 2 * RADIUS + 1);
		
	}
	public static void reload(Chunk center) {
		LoadedChunks.center = center;
		reloadCurrent();
	}
	public static void reload() {
		//TODO If edge of UPDATE_RADIUS around player reaches within one chunk of the edge of loadedChunks, recenter.
		if((Player.player.chunk.pos.x + UPDATE_RADIUS >= chunks[2*RADIUS][0].pos.x) || (Player.player.chunk.pos.x - UPDATE_RADIUS <= chunks[0][0].pos.x) ||
				(Player.player.chunk.pos.y + UPDATE_RADIUS >= chunks[0][2*RADIUS].pos.y) || Player.player.chunk.pos.y - UPDATE_RADIUS <= chunks[0][0].pos.y)
		{
			reload(Player.player.chunk);
		}
	}
	
	public static int getTopLeftX() {
		return chunks[0][0].pos.x;
	}
	public static int getTopLeftY() {
		return chunks[0][0].pos.y;
	}
	/**
	 * Returns whether a specific absolute point is within LoadedChunks
	 * @param absoluteX absolute x coordinate
	 * @param absoluteY absolute y coordinate
	 * @return is x,y in LoadedChunks?
	 */
	public static boolean isLoaded(int absoluteX, int absoluteY) {
		absoluteX = Tools.nav.absCoordToChunkCoord(absoluteX);
		absoluteY = Tools.nav.absCoordToChunkCoord(absoluteY);
		return absoluteX >= chunks[0][0].pos.x && absoluteX <= chunks[2*RADIUS][2*RADIUS].pos.x && absoluteY >= chunks[0][0].pos.y && absoluteY <= chunks[2*RADIUS][2*RADIUS].pos.y;
	}
	public static boolean isLoaded(Circle c) {
		return isLoaded(c.x, c.y + c.r) && isLoaded(c.x, c.y - c.r) && isLoaded(c.x + c.r, c.y) && isLoaded(c.x - c.r, c.y);  
	}
	public static boolean isLoaded(Line l) {
		return isLoaded(l.a.x, l.a.y) && isLoaded(l.b.x, l.b.y); 
	}
	public static boolean isLoaded(Ray r) {
		return isLoaded(r.a.x, r.a.y) && isLoaded(r.b.x, r.b.y); 
	}
	public static boolean isLoaded(Point p) {
		return isLoaded(p.x, p.y);
	}
	public static boolean isLoaded(Rectangle r) {
		return isLoaded(r.a.x, r.a.y) && isLoaded(r.b.x, r.b.y);
	}
	public static boolean isLoaded(PointCollection p) {
		for(Point t : p) {
			if(!isLoaded(t.x, t.y)) return false;
		}
		return true;
	}
	
	/**
	 * Returns the sprite from terrain at the indicated absolute coordinates
	 * @param absoluteX absolute x coordinate
	 * @param absoluteY absolute y coordinate
	 * @return the filename of the sprite at x,y
	 */
	public static String terrainSpriteFilenameAt(int absoluteX, int absoluteY) {
//		System.out.println(absoluteX/Chunk.GRID_DIM + " - " + chunks[0][0].xPos);
//		System.out.println(((absoluteX%Chunk.GRID_DIM + Chunk.GRID_DIM) % Chunk.GRID_DIM) + " " + ((absoluteY%Chunk.GRID_DIM + Chunk.GRID_DIM) % Chunk.GRID_DIM));
		return chunks[Tools.nav.absCoordToChunkCoord(absoluteX) - chunks[0][0].pos.x][Tools.nav.absCoordToChunkCoord(absoluteY) - chunks[0][0].pos.y].
				terrainAt(Tools.nav.absCoordToMinorCoord(absoluteX), Tools.nav.absCoordToMinorCoord(absoluteY)).toString();/*spriteFilepath;*/
	}
	
	/**
	 * Returns the sprite from terrain at the indicated absolute coordinates
	 * @param absoluteX absolute x coordinate
	 * @param absoluteY absolute y coordinate
	 * @return the image of the sprite at x,y
	 */
	public static Image terrainSpriteAt(int absoluteX, int absoluteY) {
		Image ret = chunks[Tools.nav.absCoordToChunkCoord(absoluteX) - chunks[0][0].pos.x][Tools.nav.absCoordToChunkCoord(absoluteY) - chunks[0][0].pos.y].
				terrainAt(Tools.nav.absCoordToMinorCoord(absoluteX), Tools.nav.absCoordToMinorCoord(absoluteY)).sprite();
		
		return ret;
	}
	public static Terrain terrainAt(int absoluteX, int absoluteY) {
		return chunks[Tools.nav.absCoordToChunkCoord(absoluteX) - chunks[0][0].pos.x][Tools.nav.absCoordToChunkCoord(absoluteY) - chunks[0][0].pos.y].
				terrainAt(Tools.nav.absCoordToMinorCoord(absoluteX), Tools.nav.absCoordToMinorCoord(absoluteY));
	}
	public static void setTerrainAt(int absoluteX, int absoluteY, Terrain t) {
		chunks[Tools.nav.absCoordToChunkCoord(absoluteX) - chunks[0][0].pos.x][Tools.nav.absCoordToChunkCoord(absoluteY) - chunks[0][0].pos.y].
				terrain[Tools.nav.absCoordToMinorCoord(absoluteX)][Tools.nav.absCoordToMinorCoord(absoluteY)] = t;
	}
	
	/**
	 * Returns the height at the indicated absolute coordinates
	 * @param absoluteX absolute x coordinate
	 * @param absoluteY absolute y coordinate
	 * @return height at x, y
	 */
	public static int heightAt(int absoluteX, int absoluteY) {
		return chunks[Tools.nav.absCoordToChunkCoord(absoluteX) - chunks[0][0].pos.x][Tools.nav.absCoordToChunkCoord(absoluteY) - chunks[0][0].pos.y].
				heightAt(Tools.nav.absCoordToMinorCoord(absoluteX), Tools.nav.absCoordToMinorCoord(absoluteY));
	}
	public static void setHeightAt(int absoluteX, int absoluteY, int h) {
		chunks[Tools.nav.absCoordToChunkCoord(absoluteX) - chunks[0][0].pos.x][Tools.nav.absCoordToChunkCoord(absoluteY) - chunks[0][0].pos.y].
				heightmap[Tools.nav.absCoordToMinorCoord(absoluteX)][Tools.nav.absCoordToMinorCoord(absoluteY)] = h;
	}
	/**
	 * Returns a vector containing all entities within the given range, inclusive.
	 * @param x1 x coordinate of the top left corner of the range
	 * @param y1 y coordinate of the top left corner of the range
	 * @param x2 x coordinate of the bottom right corner of the range
	 * @param y2 y coordinate of the bottom right corner of the range
	 * @return vector of entities within (x1,y1)->(x2,y2)
	 */
	public static List<Entity> entitiesIn(int x1, int y1, int x2, int y2) { //bounds inclusive
		int x1c = Tools.nav.absCoordToChunkCoord(x1) - chunks[0][0].pos.x;
		int y1c = Tools.nav.absCoordToChunkCoord(y1) - chunks[0][0].pos.y;
		int x2c = Tools.nav.absCoordToChunkCoord(x2) - chunks[0][0].pos.x;
		int y2c = Tools.nav.absCoordToChunkCoord(y2) - chunks[0][0].pos.y;
		List<Entity> ret = Collections.synchronizedList(new ArrayList<Entity>());
		for(int i = x1c; i < x2c + 1; i++) {
			for(int j = y1c; j < y2c + 1; j++) {
//				synchronized(chunks[i][j].entities) {
				synchronized(ret) {
					for(Entity e : chunks[i][j].entities) {
						if(e.getAbsoluteX() >= x1 && e.getAbsoluteX() <= x2 && e.getAbsoluteY() >= y1 && e.getAbsoluteY() <= y2) ret.add(e);
					}
				}
//				}
			}
		}
		return ret;
	}
	public static List<Entity> entitiesIn(Circle c) {
		List<Entity> ret = Collections.synchronizedList(new ArrayList<Entity>());
		synchronized(ret) {
			for(int i = c.x - c.r; i < c.x + c.r + 1; i++) {
				for(int j = c.y - c.r; j < c.y + c.r + 1; j++) {
					if(c.inside(i, j)) {
						ret.addAll(entitiesAt(i, j));
					}
				}
			}
		}
		return ret;
	}
	public static List<Entity> entitiesIn(Line l) {
		List<Entity> ret = Collections.synchronizedList(new ArrayList<Entity>());
		synchronized(ret) {
			for(int i = 0; i < l.points.length; i++) {
				ret.addAll(entitiesAt(l.points[i].x, l.points[i].y));
			}
		}
		return ret;
	}
	public static List<Entity> entitiesIn(Ray r) {
		List<Entity> ret = Collections.synchronizedList(new ArrayList<Entity>());
		synchronized(ret) {
			for(int i = 0; i < r.points.length; i++) {
				ret.addAll(entitiesAt(r.points[i].x, r.points[i].y));
			}
		}
		return ret;
	}
	public static List<Entity> entitiesIn(Rectangle r) {
		return entitiesIn(r.a.x, r.a.y, r.b.x, r.b.y);
	}
	public static List<Entity> entitiesIn(PointCollection p) {
		List<Entity> ret = Collections.synchronizedList(new ArrayList<Entity>());
		for(Point t : p) {
			ret.addAll(entitiesAt(t.x, t.y));
		}
		return ret;
	}
	public static List<Entity> entitiesAt(int x, int y) {
		return chunks[Tools.nav.absCoordToChunkCoord(x) - chunks[0][0].pos.x][Tools.nav.absCoordToChunkCoord(y) - chunks[0][0].pos.y].
				REFACTORSTUFFentitiesAt(x, y);
	}
	
	public static void updateAI() {
//		System.out.println(Player.player.chunk.pos.x - center.pos.x + RADIUS - UPDATE_RADIUS);
//		System.out.println(Player.player.chunk.pos.x - center.pos.x + RADIUS + UPDATE_RADIUS);
		for(int i = Player.player.chunk.pos.x - center.pos.x + RADIUS - UPDATE_RADIUS; i < Player.player.chunk.pos.x - center.pos.x + RADIUS + UPDATE_RADIUS; i++) {
			for(int j = Player.player.chunk.pos.y - center.pos.y + RADIUS - UPDATE_RADIUS; j < Player.player.chunk.pos.y - center.pos.y + RADIUS + UPDATE_RADIUS; j++) {
//				System.out.println(chunks[i][j].pos);
				synchronized(chunks[i][j].entities) {
					for(Entity e : chunks[i][j].entities) {
						if(e instanceof Intelligent) {
							((Intelligent) e).tick();
						}
					}
				}
			}
		}
	}
	
	public static void spawnMobs() {
		//TODO when there are more enemies this will have to be revamped but it's good for now
		int numSkellys;
		int xPos;
		int yPos;
		for(int i = Player.player.chunk.pos.x - center.pos.x + RADIUS - UPDATE_RADIUS; i < Player.player.chunk.pos.x - center.pos.x + RADIUS + UPDATE_RADIUS; i++) {
			for(int j = Player.player.chunk.pos.y - center.pos.y + RADIUS - UPDATE_RADIUS; j < Player.player.chunk.pos.y - center.pos.y + RADIUS + UPDATE_RADIUS; j++) {
				numSkellys = ((int) (Math.random() * 5));
				synchronized(chunks[i][j].entities) {
					for(int k = 0; k < numSkellys; k++) {
						xPos = (int)(Math.random() * Chunk.DIM);
						yPos = (int)(Math.random() * Chunk.DIM);
						if(chunks[i][j].heightmap[xPos][yPos] == 0) new Skeleton(chunks[i][j].pos.x * Chunk.DIM + xPos, chunks[i][j].pos.y * Chunk.DIM + yPos, chunks[i][j]);
					}
				}
			}
		}
	}
}
