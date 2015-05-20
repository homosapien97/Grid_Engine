package world;

import java.util.HashMap;
import java.util.Vector;

import entity.Entity;
import geometry.Point;
import terrain.Terrain;

public class Chunk {
	//grid dimensions (square)
	public static final int DIM = 16;
	
	//stores all chunks
	public static final HashMap<Point, Chunk> chunks = new HashMap<Point, Chunk>(961);
	
	//chunk instance positions
	public final Point pos;
	
	//all entities
	public Vector<Entity> entities;
	
	//variables to store data per chunk
	public Chunk[][] neighbors;
	public Terrain[][] terrain;
	
	//height should pretty much range between -1 and 1, 0 being ground, -1 being pit/fluid/void, and 0 being ground.
	private int[][] heightmap;
	
	/**
	 * Creates the origin chunk at position (0, 0).
	 */
	private Chunk() {
		pos = new Point(0,0);
		
		entities = new Vector<Entity>();
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
		
		entities = new Vector<Entity>();
		
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
		
		entities = new Vector<Entity>();
		
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
	 * Creates a chunk at position xPos, yPos in the chunk grid.
	 * @param xPos the x position in the chunks where the new chunk is located
	 * @param yPos the y position in the chunks where the new chunk is located
	 * @param checkOverlap whether to check for any existing chunks with the same coordinates before adding the new chunk to chunks
	 * @param entities entities to create in the new chunk.
	 * @param terrain terrain to create in the new chunk.
	 * @param heightmap heightmap to create in the new chunk.
	 */
	public Chunk(int xPos, int yPos, boolean checkOverlap, Vector<Entity> entities, Terrain[][] terrain, int[][] heightmap) {
		pos = new Point(xPos, yPos);
		
		entities = new Vector<Entity>();
		
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
		
		entities = new Vector<Entity>();
		
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
	
	/**
	 * Checks for Chunks from the chunks vector to fill any null positions in nearestNeighbors.
	 */
	public void updateNeighbors() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				neighbors[i][j] = chunks.get(new Point(pos, i - 1, j - 1));
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
		for(Entity e : entities) {
			e.delete();
		}
		
		chunks.remove(this.pos);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	/**
	 * Checks a location for an entity, and gets that entity if it is present.
	 * @param x the x of the coordinate to check for an entity
	 * @param y the y of the coordinate to check for an entity
	 * @return returns the entity at position (x, y). If no entity is there, returns null.
	 */
	public Entity entityAt(int x, int y) {
		for(Entity e : entities) {
			if(e.getX() == x && e.getY() == y){
				return e;
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
	public Vector<Entity> entitiesAt(int x, int y) {
		Vector<Entity> ret = new Vector<Entity>();
		
		for(Entity e: entities) {
			if(e.getX() == x && e.getY() == y) 
				ret.add(e);
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
