package ai;

import geometry.Point;

import java.util.Vector;

public class BranchingPath {
	public int xPos;
	public int yPos;
	public Vector<BranchingPath> paths;
	public BranchingPath parent;
	
	public BranchingPath(int x, int y) {
		xPos = x;
		yPos = y;
		paths = new Vector<BranchingPath>();
	}
	public BranchingPath(int x, int y, BranchingPath parent) {
		xPos = x;
		yPos = y;
		paths = new Vector<BranchingPath>();
		this.parent = parent;
	}
	
	public boolean isLeaf() {
		return paths.size() == 0;
	}
	public boolean isTrunk() {
		return parent == null;
	}
	public void add(int x, int y) {
		paths.add(new BranchingPath(x, y));
	}
	public Vector<Point> reversePath() {
		//The vector contains the path in reverse, because adding elements to the beginning of a vector is horribly inefficient.
		Vector<Point> ret = new Vector<Point>();
		ret.add(new Point(xPos, yPos));
		if(isTrunk()) return ret;
		BranchingPath next = parent;
		while(!next.isTrunk()) {
			ret.add(new Point(next.xPos, next.yPos));
			next = next.parent;
		}
		ret.add(new Point(next.xPos, next.yPos));
		return ret;
	}
}
