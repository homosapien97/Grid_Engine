package ai;

import entity.Entity;
import entity.Mobile;
import entity.Pathing;
import entity.Sighted;
import general.Tools;


public class Path <T extends Entity & Mobile & Pathing & Sighted> {
	public char[][] maze;
	
	public static char TRIED = 'T';
	public static char PATH = 'P';
	public static char VISIBLE = 'V';
	public static char INVISIBLE = 'I';
	
	public T actor;
	
	public Path(T actor) {
		this.actor = actor;
		boolean[][] temp = actor.vsquare().mask;
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[0].length; j++) {
				maze[i][j] = (temp[i][j]) ? VISIBLE : INVISIBLE;
			}
		}
	}
	
	/**
	 * Paths a Mobile, Pathing entity to a given point
	 * @param actor
	 * @param absoluteX
	 * @param absoluteY
	 * @return
	 */
	public boolean pathTo(int absoluteX, int absoluteY) {
		actor.vsquare().trace();
		if (actor.vsquare().canSee(absoluteX, absoluteY)) {
			return traverse(actor.vsquare().RADIUS, actor.vsquare().RADIUS, actor.vsquare().RADIUS + absoluteX - actor.getAbsoluteX(), actor.vsquare().RADIUS + absoluteY - actor.getAbsoluteY());
		}
		return false;
	}
	
	/**
	 * I am copying this straight from the book, page 462
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean traverse(int row, int col, int destrow, int destcol) {
		boolean done = false;
		if(valid(row, col)) {
			maze[row][col] = TRIED;
			if(row == destrow && col == destcol) {
				done = true;
			}
			else {
				int[][] offsets = Tools.misc.offsets(new int[]{
						Tools.nav.orthoDistance(row + 1, col, destrow, destcol), Tools.nav.orthoDistance(row, col - 1, destrow, destcol),
						Tools.nav.orthoDistance(row - 1, col, destrow, destcol), Tools.nav.orthoDistance(row, col + 1, destrow, destcol)});
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
