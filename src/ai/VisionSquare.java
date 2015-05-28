package ai;

import world.LoadedChunks;
import geometry.Line;
import geometry.Point;

public class VisionSquare {
	
	//Some public static final visionsquares for global use. Don't rely on their xy staying the same. Definitely will break w/multithreading
	//Only reason for this is that these are fairly intense objects. Why make more than you gotta? Most things will have a few set denominations
	//of sight range--all creatures with the same sight range can use the same global. Might add more later if it seems like we need them.
	public static final VisionSquare r1;
	public static final VisionSquare r7;
	public static final VisionSquare r15;
	public static final VisionSquare r31;
	static {
		r1 = new VisionSquare(0,0,1);
		r7 = new VisionSquare(0,0,7);
		r15 = new VisionSquare(0,0,15);
		r31 = new VisionSquare(0,0,31);
	}
	
	
	
	
	public final int RADIUS;
	public int x;
	public int y;
	public final boolean[][] mask; //you probs shouldn't access this directly if you're using a global--get it from trace and store a temp copy for your needs.
	public final Line[] rays;
	public VisionSquare(int x, int y, int r) {
		this.x = x;
		this.y = y;
		RADIUS = r;
		mask = new boolean[2 * RADIUS + 1][2 * RADIUS + 1];
		for(int i = 0; i < 2 * RADIUS + 1; i++) {
			for(int j = 0; j < 2 * RADIUS + 1; j++) {
				mask[i][j] = false;
			}
		}
		rays = new Line[8 * RADIUS];
		for(int i = 0; i < 2 * RADIUS; i++) {
			rays[i] = new Line(RADIUS, RADIUS, i, 0);
		}
		for(int i = 0; i < 2 * RADIUS; i++) {
			rays[i + 2 * RADIUS] = new Line(RADIUS, RADIUS, 2 * RADIUS, i);
		}
		for(int i = 0; i < 2 * RADIUS; i++) {
			rays[i + 4 * RADIUS] = new Line(RADIUS, RADIUS, 2 * RADIUS - i, 2 * RADIUS);
		}
		for(int i = 0; i < 2 * RADIUS; i++) {
			rays[i + 6 * RADIUS] = new Line(RADIUS, RADIUS, 0, 2 * RADIUS - i);
		}
		trace();
	}
	
	public boolean[][] trace() {
		Point temp;
		Point lastBlock = null;
		boolean blocked = false;
		int l;
		for(int i = 0; i < 8 * RADIUS; i++) {
			blocked = false;
			l = rays[i].points.length;
			if(rays[i].contains(lastBlock)) {
				for(int j = rays[i].points.length - 1; !rays[i].points[j + 1].equals(lastBlock); j --) {
					mask[rays[i].points[j].x][rays[i].points[j].y] = false;
					l--;
				}
				
			}
			for(int j = 0; j < l; j++) {
				temp = rays[i].points[j];
				if(blocked) {
					mask[temp.x][temp.y] = false; 
				} else if(LoadedChunks.heightAt(temp.x - RADIUS + x, temp.y - RADIUS + y) > 0 && !LoadedChunks.terrainAt(temp.x - RADIUS + x, temp.y - RADIUS + y).clear) {
					blocked = true;
					lastBlock = temp;
					mask[temp.x][temp.y] = false; 
				} else {
					mask[temp.x][temp.y] = true; 
				}
			}
		}
		return mask;
	}
	public boolean[][] trace(int x, int y) { //DO NOT CALL ON A POINT THAT IS LESS THAN RADIUS AWAY FROM THE EDGE OF LOADEDCHUNKS.
		this.x = x;
		this.y = y;
		return trace();
	}
//	public boolean[][] mask() {
//		return mask;
//	}
	public int r() {
		return RADIUS;
	}
	public boolean canSee(int x, int y) { //WARNING: When using a global, call trace immediately before checking this.
		return (x >= this.x - RADIUS && x <= this.x + RADIUS && y >= this.y - RADIUS && y <= this.y + RADIUS) && mask[x - this.x + RADIUS][y - this.y + RADIUS];
	}
}