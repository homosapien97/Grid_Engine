package geometry;

public class Point {
	//Inefficient for use in most coordinates, but efficient for storing points in vectors;
	public final int x;
	public final int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(Point p, int dx, int dy) {
		x = p.x + dx;
		y = p.y + dy;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			return (x == ((Point) obj).x) && (y == ((Point) obj).y);
		}
		return false;
	}
	@Override
	public int hashCode() {
		return x ;
	}
}
