package ai;

import entity.Entity;
import entity.Mobile;
import entity.Sighted;
import general.Tools;
import geometry.Point;


public class Path <T extends Entity & Mobile & Sighted> {
	public char[][] maze;
	public Point tl;
	public int targetX;
	public int targetY;
	
	public static char TRIED = 'T';
	public static char PATH = 'P';
	public static char VISIBLE = 'V';
	public static char INVISIBLE = 'I';
	public static char DONE = 'D';
	
	public T actor;
	
	public Path(T actor) {
		this.actor = actor;
//		boolean[][] temp = actor.vsquare().mask;
//		for(int i = 0; i < temp.length; i++) {
//			for(int j = 0; j < temp[0].length; j++) {
//				maze[i][j] = (temp[i][j]) ? VISIBLE : INVISIBLE;
//			}
//		}
		maze = new char[actor.vsquare().RADIUS * 2 + 1][actor.vsquare().RADIUS * 2 + 1];
		tl = new Point(actor.getAbsoluteX() - actor.vsquare().RADIUS, actor.getAbsoluteY() - actor.vsquare().RADIUS);
		targetX = actor.getAbsoluteX();
		targetY = actor.getAbsoluteY();
	}
	
	public int length() {
//		maze[actor.getAbsoluteX() - tl.x][actor.getAbsoluteY() - tl.y] = DONE;
		int ret = 0;
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[0].length; j++) {
				if(maze[i][j] == PATH) ret++;
			}
		}
		return ret;
		//This can be done by following the path 
	}
	
	public boolean goTo(int x, int y) {
		if(targetX == x && targetY == y) {
			return go();
		} else {
			targetX = x;
			targetY = y;
			constructPathTo(targetX, targetY);
			return go();
		}
	}
	
	public boolean go() {
		maze[actor.getAbsoluteX() - tl.x][actor.getAbsoluteY() - tl.y] = DONE;
		if(maze[actor.getAbsoluteX() - tl.x][actor.getAbsoluteY() - tl.y + 1] == PATH) {
			actor.goToRelative(0, 1);
			return true;
		} else if(maze[actor.getAbsoluteX() - tl.x][actor.getAbsoluteY() - tl.y - 1] == PATH) {
			actor.goToRelative(0, -1);
			return true;
		} else if(maze[actor.getAbsoluteX() - tl.x + 1][actor.getAbsoluteY() - tl.y] == PATH) {
			actor.goToRelative(1, 0);
			return true;
		} else if(maze[actor.getAbsoluteX() - tl.x - 1][actor.getAbsoluteY() - tl.y] == PATH) {
			actor.goToRelative(-1, 0);
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Constructs a path from the actor to any point in their vision square. Only call this explicitly if you need the path to be constructed but you don't want to actually move. Expensive.
	 * @param actor
	 * @param absoluteX
	 * @param absoluteY
	 * @return
	 */
	public boolean constructPathTo(int absoluteX, int absoluteY) {
		actor.vsquare().trace();
		tl = new Point(actor.getAbsoluteX() - actor.vsquare().RADIUS, actor.getAbsoluteY() - actor.vsquare().RADIUS);
		if(!actor.vsquare().canSee(absoluteX, absoluteY)) {
//			System.out.println("Cannot go to" + absoluteX + ", " + absoluteY + " because I can't see it");
			return false;
		}
		boolean[][] temp = actor.vsquare().mask;
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[0].length; j++) {
				maze[i][j] = (temp[i][j]) ? VISIBLE : INVISIBLE;
			}
		}
		return traverse(actor.vsquare().RADIUS, actor.vsquare().RADIUS, actor.vsquare().RADIUS + absoluteX - actor.getAbsoluteX(), actor.vsquare().RADIUS + absoluteY - actor.getAbsoluteY());
	}
	
	/**
	 * I am copying this straight from the book, page 462 . Acutally, I had to edit a bit, but w/e
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean traverse(int row, int col, int destrow, int destcol) {
		boolean done = false;
		if(valid(row, col)) {
			maze[row][col] = TRIED;
			if(row == destrow && col == destcol) {
				done = true;
			}
			else {
				int[][] offsets = Tools.misc.offsets(new double[]{
						Tools.nav.distance(row + 1, col, destrow, destcol), Tools.nav.distance(row, col - 1, destrow, destcol),
						Tools.nav.distance(row - 1, col, destrow, destcol), Tools.nav.distance(row, col + 1, destrow, destcol)});
				done = traverse(row + offsets[0][0], col + offsets[0][1], destrow, destcol);
				if(!done) {
					done = traverse(row + offsets[1][0], col + offsets[1][1], destrow, destcol);
				}
				if(!done) {
					done = traverse(row + offsets[2][0], col + offsets[2][1], destrow, destcol);
				}
				if(!done) {
					done = traverse(row + offsets[3][0], col + offsets[3][1], destrow, destcol);
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
