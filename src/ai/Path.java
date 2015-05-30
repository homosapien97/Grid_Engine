package ai;

import entity.Entity;
import entity.Mobile;
import entity.Pathing;
import entity.Sighted;


public class Path <T extends Entity & Mobile & Pathing & Sighted> {
	public char[][] maze;
	
	public static char TRIED = 'T';
	public static char PATH = 'P';
	public static char VISIBLE = 'V';
	
	public T actor;
	/**
	 * Paths a Mobile, Pathing entity to a given point
	 * @param actor
	 * @param absoluteX
	 * @param absoluteY
	 * @return
	 */
	public boolean pathTo(T actor, int absoluteX, int absoluteY) {
		actor.vsquare().trace();
		int x = actor.getAbsoluteX();
		int y = actor.getAbsoluteY();
		while(x != absoluteX && y != absoluteY) {
			
		}
		
		return false;
	}
//	public static boolean completeMaze(int xc, int yc, int xt, int yt) {
//		// 1 
//		//2*0
//		// 3   note that down is positive y
//		int square = 0;
////		int horiz = xt - xc;
////		int vert = yt - yc;
////		if(horiz > 0) {
////			if(vert > 0) {
////				square = (horiz - vert > 0) ? (0) : (3);
////			} else if(vert < 0) {
////				square = (horiz + vert > 0) ? (0) : (1);
////			} else {
////				square = 0;
////			}
////		} else if (horiz < 0) {
////			if(vert > 0) {
////				square = (horiz + vert > 0) ? (3) : (2);
////			} else if(vert < 0) {
////				square = (horiz - vert > 0) ? (1) : (2);
////			} else {
////				square = 2;
////			}
////		} else {
////			if(vert > 0) {
////				square = 3;
////			} else if(vert < 0) {
////				square = 1;
////			} else {
////				return true;
////			}
////		}
//		int[] dims = new int[] {Tools.nav.orthoDistance(xc + 1, yc, xt, yt), Tools.nav.orthoDistance(xc + 1, yc, xt, yt), Tools.nav.orthoDistance(xc - 1, yc, xt, yt), Tools.nav.orthoDistance(xc, yc + 1, xt, yt)};
////		int d0 = Tools.nav.orthoDistance(xc + 1, yc, xt, yt);
////		int d1 = Tools.nav.orthoDistance(xc + 1, yc, xt, yt);
////		int d2 = Tools.nav.orthoDistance(xc - 1, yc, xt, yt);
////		int d3 = Tools.nav.orthoDistance(xc, yc + 1, xt, yt);
//		
//		
//		
//	}
	
	/**
	 * I am copying this straight from the book, page 462
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean traverse(int row, int col) {
		boolean done = false;
		if(valid(row, col)) {
			maze[row][col] = TRIED;
			if(row == maze.length - 1 && col == maze[0].length - 1) {
				done = true;
			}
			else {
				done = traverse(row + 1, col);
				if(!done) {
					done = traverse(row, col + 1);
				}
				if(!done) {
					done = traverse(row - 1, col);
				}
				if(!done) {
					done = traverse(row, col - 1);
				}
			}
			if(done) {
				maze[row][col] = PATH;
			}
		}
		return done;
	}
	
	private boolean valid(int row, int col) {
		return (row>= 0 && row < maze.length && col >= 0 && col < maze[row].length && maze[row][col] == VISIBLE);
	}
}
