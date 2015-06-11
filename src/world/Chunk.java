package world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
//import java.util.Vector;
import java.util.Iterator;

import entity.Entity;
import geometry.Point;
import terrain.Terrain;
import world.generation.Generator;

public class Chunk {
	//grid dimensions (square)
	public static final int DIM = 16;
	
	//stores all chunks
	public static final HashMap<Point, Chunk> chunks = new HashMap<Point, Chunk>(961);
	
	//chunk instance positions
	public final Point pos;
	
	//all entities
	public List<Entity> entities;
	
	//variables to store data per chunk
	public Chunk[][] neighbors;
	public Terrain[][] terrain;
	
	//height should pretty much range between -1 and 1, 0 being ground, -1 being pit/fluid/void, and 0 being ground.
	public int[][] heightmap;
	
	/**
	 * Creates the origin chunk at position (0, 0).
	 */
	@SuppressWarnings("unused")
	private Chunk() {
		pos = new Point(0,0);
		
//		entities = new Vector<Entity>();
		entities = Collections.synchronizedList(new ArrayList<Entity>());
		chunks.put(pos, this);
		//neighbors
		neighbors = new Chunk[3][3];
		neighbors[1][1] = this;
		updateNeighbors();
		
		//terrain and height
		terrain = new Terrain[DIM][DIM];
		heightmap = new int[DIM][DIM];

	}
	
	/**
	 * Creates an empty chunk at position xPos, yPos in the chunk grid.
	 * @param xPos the x position in the chunks where the new chunk is located
	 * @param yPos the y position in the chunks where the new chunk is located
	 * @param checkOverlap whether to check for any existing chunks with the same coordinates before adding the new chunk to chunks
	 */
	public Chunk(int xPos, int yPos, boolean checkOverlap) {
		pos = new Point(xPos, yPos);
		
//		entities = new Vector<Entity>();
		entities = Collections.synchronizedList(new ArrayList<Entity>());
		
		if(checkOverlap && chunks.get(pos) == null) {
			chunks.put(pos, this);
			//neighbors
			neighbors = new Chunk[3][3];
			neighbors[1][1] = this;
			updateNeighbors();
		}
		
		//terrain and height
		terrain = new Terrain[DIM][DIM];
		heightmap = new int[DIM][DIM];

	}
	
	/**
	 * Creates an empty chunk at position pos in the chunk grid.
	 * @param xPos the x position in the chunks where the new chunk is located
	 * @param yPos the y position in the chunks where the new chunk is located
	 * @param checkOverlap whether to check for any existing chunks with the same coordinates before adding the new chunk to chunks
	 */
	public Chunk(Point pos, boolean checkOverlap) {
		this.pos = pos;
		
//		entities = new Vector<Entity>();
		entities = Collections.synchronizedList(new ArrayList<Entity>());
		
		if(checkOverlap && chunks.get(pos) == null) {
			chunks.put(pos, this);
			//neighbors
			neighbors = new Chunk[3][3];
			neighbors[1][1] = this;
			updateNeighbors();
		}
		
		//terrain and height
		terrain = new Terrain[DIM][DIM];
		heightmap = new int[DIM][DIM];

	}
	
	/**
	 * Creates an empty chunk at position xPos, yPos in the chunk grid.
	 * @param xPos the x position in the chunks where the new chunk is located
	 * @param yPos the y position in the chunks where the new chunk is located
	 * @param checkOverlap whether to check for any existing chunks with the same coordinates before adding the new chunk to chunks
	 * @param terrain the terrain to set to this new chunk
	 */
	public Chunk(int xPos, int yPos, boolean checkOverlap, Terrain terrain) {
		pos = new Point(xPos, yPos);
		
//		entities = new Vector<Entity>();
		entities = Collections.synchronizedList(new ArrayList<Entity>());
		
		if(checkOverlap && chunks.get(pos) == null) {
			chunks.put(pos, this);
			//neighbors
			neighbors = new Chunk[3][3];
			neighbors[1][1] = this;
			updateNeighbors();
		}
		
		//terrain and height
		this.terrain = new Terrain[DIM][DIM];
		heightmap = new int[DIM][DIM];
		for(int i = 0; i < DIM; i++) {
			for(int j = 0; j < DIM; j++) {
				this.terrain[i][j] = terrain;
				heightmap[i][j] = 0;
			}
		}
		
	}
	
	/**
	 * Creates an empty chunk at position pos in the chunk grid.
	 * @param xPos the x position in the chunks where the new chunk is located
	 * @param yPos the y position in the chunks where the new chunk is located
	 * @param checkOverlap whether to check for any existing chunks with the same coordinates before adding the new chunk to chunks
	 * @param terrain the terrain to set to this new chunk
	 */
	public Chunk(Point pos, boolean checkOverlap, Terrain terrain) {
		this.pos = pos;
		
//		entities = new Vector<Entity>();
		entities = Collections.synchronizedList(new ArrayList<Entity>());
		
		if(checkOverlap && chunks.get(pos) == null) {
			chunks.put(pos, this);
			//neighbors
			neighbors = new Chunk[3][3];
			neighbors[1][1] = this;
			updateNeighbors();
		}
		
		//terrain and height
		this.terrain = new Terrain[DIM][DIM];
		heightmap = new int[DIM][DIM];
		for(int i = 0; i < DIM; i++) {
			for(int j = 0; j < DIM; j++) {
				this.terrain[i][j] = terrain;
				heightmap[i][j] = 0;
			}
		}
		
	}
	
	/**
	 * Creates a chunk at position pos in the chunk grid.
	 * @param xPos the x position in the chunks where the new chunk is located
	 * @param yPos the y position in the chunks where the new chunk is located
	 * @param checkOverlap whether to check for any existing chunks with the same coordinates before adding the new chunk to chunks
	 * @param entities entities to create in the new chunk.
	 * @param terrain terrain to create in the new chunk.
	 * @param heightmap heightmap to create in the new chunk.
	 */
	public Chunk(int xPos, int yPos, boolean checkOverlap, List<Entity> entities, Terrain[][] terrain, int[][] heightmap) {
		pos = new Point(xPos, yPos);
		
//		entities = new Vector<Entity>();
		this.entities = entities;
		
		if(checkOverlap && chunks.get(pos) == null) {
			chunks.put(pos, this);
			//neighbors
			neighbors = new Chunk[3][3];
			neighbors[1][1] = this;
			updateNeighbors();
		}
		
		//terrain and height
		this.terrain = terrain;
		this.heightmap = heightmap;
	}
	
	public Chunk(Point pos, boolean checkOverlap, List<Entity> entities, Terrain[][] terrain, int[][] heightmap) {
		this.pos = pos;
		
//		entities = new Vector<Entity>();
		this.entities = entities;
		
		if(checkOverlap && chunks.get(pos) == null) {
			chunks.put(pos, this);
			//neighbors
			neighbors = new Chunk[3][3];
			neighbors[1][1] = this;
			updateNeighbors();
		}
		
		//terrain and height
		this.terrain = terrain;
		this.heightmap = heightmap;
	}
	
	/**Creates a copy of chunk without entities at position xPos, yPos in the chunk grid.
	 * @param xPos the x position in the chunks where the new chunk is located
	 * @param yPos the y position in the chunks where the new chunk is located
	 * @param checkOverlap whether to check for any existing chunks with the same coordinates before adding the new chunk to chunks
	 * @param chunk chunk to copy
	 */
	public Chunk(int xPos, int yPos, boolean checkOverlap, Chunk chunk) {
		pos = new Point(xPos, yPos);
		
//		entities = new Vector<Entity>();
		entities = Collections.synchronizedList(new ArrayList<Entity>());
		
		if(checkOverlap && chunks.get(pos) == null) {
			chunks.put(pos, this);
			//neighbors
			neighbors = new Chunk[3][3];
			neighbors[1][1] = this;
			updateNeighbors();
		}
		
		//terrain and height
		terrain = new Terrain[DIM][DIM];
		heightmap = new int[DIM][DIM];
		
		//copying
		for(int i = 0; i < DIM; i++) {
			for(int j = 0; j < DIM; j++) {
				terrain[i][j] = chunk.terrain[i][j];
				heightmap[i][j] = chunk.heightmap[i][j];
			}
		}
		
	}
	
	/**Creates a copy of chunk without entities at position pos in the chunk grid.
	 * @param xPos the x position in the chunks where the new chunk is located
	 * @param yPos the y position in the chunks where the new chunk is located
	 * @param checkOverlap whether to check for any existing chunks with the same coordinates before adding the new chunk to chunks
	 * @param chunk chunk to copy
	 */
	public Chunk(Point pos, boolean checkOverlap, Chunk chunk) {
		this.pos = pos;
		
		entities = Collections.synchronizedList(new ArrayList<Entity>());
		
		if(checkOverlap && chunks.get(pos) == null) {
			chunks.put(pos, this);
			//neighbors
			neighbors = new Chunk[3][3];
			neighbors[1][1] = this;
			updateNeighbors();
		}
		
		//terrain and height
		terrain = new Terrain[DIM][DIM];
		heightmap = new int[DIM][DIM];
		
		//copying
		for(int i = 0; i < DIM; i++) {
			for(int j = 0; j < DIM; j++) {
				terrain[i][j] = chunk.terrain[i][j];
				heightmap[i][j] = chunk.heightmap[i][j];
			}
		}
		
	}
	
	public static Chunk[][] loadChunks(int x, int y, int width, int height) {
		Chunk[][] ret = new Chunk[width][height];
		ret[0][0] = chunks.get(new Point(x, y));
		if(ret[0][0] == null) ret[0][0] = Generator.generateChunk(x, y);
		for(int i = 1; i < width; i++) {
			ret[i][0] = ret[i - 1][0].neighbors[2][1];
			if(ret[i][0] == null) ret[i][0] = Generator.generateChunk(x + i, y);
		}
		for(int j = 1; j < height; j++) {
			ret[0][j] = ret[0][j - 1].neighbors[1][2];
			if(ret[0][j] == null) ret[0][j] = Generator.generateChunk(x, y + j);
			for(int i = 1; i < width; i++) {
				ret[i][j] = ret[i - 1][j].neighbors[2][1];
				if(ret[i][j] == null) ret[i][j] = Generator.generateChunk(x + i, y + j);
			}
		}
		return ret;
	}
	public static Chunk[][] loadChunks(Point pos, int width, int height) {
		Chunk[][] ret = new Chunk[width][height];
		ret[0][0] = chunks.get(pos);
		if(ret[0][0] == null) ret[0][0] = Generator.generateChunk(pos);
		for(int i = 1; i < width; i++) {
			ret[i][0] = ret[i - 1][0].neighbors[2][1];
			if(ret[i][0] == null) ret[i][0] = Generator.generateChunk(pos.x + i, pos.y);
		}
		for(int j = 1; j < height; j++) {
			ret[0][j] = ret[0][j - 1].neighbors[1][2];
			if(ret[0][j] == null) ret[0][j] = Generator.generateChunk(pos.x, pos.y + j);
			for(int i = 1; i < width; i++) {
				ret[i][j] = ret[i - 1][j].neighbors[2][1];
				if(ret[i][j] == null) ret[i][j] = Generator.generateChunk(pos.x + i, pos.y + j);
			}
		}
		return ret;
	}
	public static Chunk[][] loadChunks(Point a, Point b) {
		Chunk[][] ret = new Chunk[b.x - a.x + 1][b.y - a.y + 1];
		ret[0][0] = chunks.get(a);
		if(ret[0][0] == null) ret[0][0] = Generator.generateChunk(a);
		for(int i = 1; i < ret.length; i++) {
			ret[i][0] = ret[i - 1][0].neighbors[2][1];
			if(ret[i][0] == null) ret[i][0] = Generator.generateChunk(a.x + i, a.y);
		}
		for(int j = 1; j < ret[0].length; j++) {
			ret[0][j] = ret[0][j - 1].neighbors[1][2];
			if(ret[0][j] == null) ret[0][j] = Generator.generateChunk(a.x, a.y + j);
			for(int i = 1; i < ret.length; i++) {
				ret[i][j] = ret[i - 1][j].neighbors[2][1];
				if(ret[i][j] == null) ret[i][j] = Generator.generateChunk(a.x + i, a.y + j);
			}
		}
		return ret;
	}
	public static Chunk[][] loadChunks(Chunk c, int width, int height) {
		Chunk[][] ret = new Chunk[width][height];
		ret[0][0] = c;
		if(ret[0][0] == null) return null;
		for(int i = 1; i < width; i++) {
			ret[i][0] = ret[i - 1][0].neighbors[2][1];
			if(ret[i][0] == null) ret[i][0] = Generator.generateChunk(c.pos.x + i, c.pos.y);
		}
		for(int j = 1; j < height; j++) {
			ret[0][j] = ret[0][j - 1].neighbors[1][2];
			if(ret[0][j] == null) ret[0][j] = Generator.generateChunk(c.pos.x, c.pos.y + j);
			for(int i = 1; i < width; i++) {
				ret[i][j] = ret[i - 1][j].neighbors[2][1];
				if(ret[i][j] == null) ret[i][j] = Generator.generateChunk(c.pos.x + i, c.pos.y + j);
			}
		}
		return ret;
	}
	
	/**
	 * Checks for Chunks from the chunks vector to fill any null positions in nearestNeighbors.
	 */
	public void updateNeighbors() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(neighbors[i][j] == null) {
					neighbors[i][j] = chunks.get(new Point(pos, i - 1, j - 1));
					if(neighbors[i][j] != null) {
						neighbors[i][j].updateNeighbors();
					}
				}
			}
		}
	}
	
	/**
	 * Sets nearestNeighbors at x, y to null. If x or y is outside the range [0,2], all nearest neighbors except this are cleared. If x
	 * and y are both one, this chunk is deleted.
	 * @param x the x position in the array nearestNeighbors to clear
	 * @param y the y position in the array nearestNeighbors to clear
	 */
	public void clearNeighbor(int x, int y) {
		if(x < 0 || y < 0 || x > 2 || y > 2) {
			//if x or y are out of bounds for a neighbor, clear all neighbors
			neighbors[0][0] = null; neighbors[0][1] = null; neighbors[0][2] = null;
			neighbors[1][0] = null; neighbors[1][1] = null; neighbors[1][2] = null;
			neighbors[2][0] = null; neighbors[2][1] = null; neighbors[2][2] = null;
		} else {
			neighbors[x][y] = null;
		}
		
		if(x == 1 && y == 1){
			delete();
		}
	}
	
	/**
	 * Deletes this chunk from the global chunks and from any of its nearest neighbors' nearestNeighbors arrays.
	 */
	public void delete() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(neighbors[i][j] != null && !(i == 1 && j == 1)) {
					neighbors[i][j].clearNeighbor(2 - i, 2 - j);
				}
			}
		}
		synchronized(entities) {
//			for(Entity e : entities) {
//				e.delete();
//			}
//			entities.removeIf(r -> r.deleteSafe());
			entities.clear();
		}
		
		
		chunks.remove(this.pos);
	}
	
	public void addEntity(Entity e) {
		synchronized(entities) {
			entities.add(e);
		}
	}
	public void removeEntity(Entity e) {
		synchronized(entities) {
			entities.remove(e);
		}
	}
	public void removeFlaggedEntities() {
		Entity e;
		for(Iterator<Entity> it = entities.iterator(); it.hasNext();) {
			e = it.next();
			if(e.remove) it.remove();
		}
	}
	public void removeEntity(Iterator<Entity> i) {
		i.remove();
	}
	
	/**
	 * Checks a location for an entity, and gets that entity if it is present.
	 * @param x the x of the coordinate to check for an entity
	 * @param y the y of the coordinate to check for an entity
	 * @return returns the entity at position (x, y). If no entity is there, returns null.
	 */
	public Entity entityAt(int absoluteX, int absoluteY) {
		synchronized(entities) {
			for(Entity e : entities) {
				if(e.getAbsoluteX() == absoluteX && e.getAbsoluteY() == absoluteY){
					return e;
				}
			}
		}
		return null;
	}
	
	/**
	 * Checks a location for entities, and gets them if they are present.
	 * @param x the x of the coordinate to check for entities
	 * @param y the y of the coordinate to check for entities
	 * @return returns the entities at position (x, y). If no entities are there, returns null.
	 */
	public List<Entity> entitiesAt(int absoluteX, int absoluteY) {
		List<Entity> ret = Collections.synchronizedList(new ArrayList<Entity>());
		synchronized(entities) {
			synchronized(ret) {
				for(Entity e: entities) {
					if(e.getAbsoluteX() == absoluteX && e.getAbsoluteY() == absoluteY) 
						ret.add(e);
				}
			}
		}
		
		return ret;
	}
	
	//simple get functions
	
	public Terrain terrainAt(int x, int y) {
		return terrain[x][y];
	}
	
	public int heightAt(int x, int y) {
		return heightmap[x][y];
	}
	
	public Chunk getNeighbor(int x, int y) {
		return neighbors[x][y];
	}
	
	//basic class functions
	
	public boolean equals(Chunk c) {
		return (pos.equals(c.pos));
	}
	
	public String toString() {
		String ret = "";
		for(int j = 0; j < DIM; j++) {
			for(int i = 0; i < DIM; i++) {
				ret += terrain[i][j];
			}
			ret += "\n";
		}
		return ret;
	}
}
