package geometry;

public class Point {
	//Inefficient for use in most coordinates, but efficient for storing points in vectors;
	public final int x;
	public final int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
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
