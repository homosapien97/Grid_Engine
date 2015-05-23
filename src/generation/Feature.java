package generation;

import geometry.Point;

public abstract class Feature {
	public int x;
	public int y;
	
	protected Feature(int x, int y) {
		this.x = x;
		this.y = y;
	}
	protected Feature(Point p) {
		x = p.x;
		y = p.y;
	}
	
	public abstract boolean generate();
	
	public abstract boolean contains(int x, int y);
	public abstract boolean contains(Point p);
}
