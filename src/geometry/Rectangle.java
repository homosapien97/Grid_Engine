package geometry;

public class Rectangle {
	public final Point a;
	public final Point b;
	
	public Rectangle(Point a, Point b) {
		this.a = new Point((a.x < b.x) ? a.x : b.x, (a.y < b.y) ? a.y : b.y);
		this.b = new Point((a.x > b.x) ? a.x : b.x, (a.y > b.y) ? a.y : b.y);
	}
	
	public Rectangle(int x1, int y1, int x2, int y2) {
		this.a = new Point((x1 < x2) ? x1 : x2, (y1 < y2) ? y1 : y2);
		this.b = new Point((x1 > x2) ? x1 : x2, (y1 > y2) ? y1 : y2);
	}
	
	public boolean contains(Point p) {
		return (p.x >= a.x && p.x <= b.x && p.y >= a.y && p.y <= b.y);
	}
	public boolean contains(int x, int y) {
		return (x >= a.x && x <= b.x && y >= a.y && y <= b.y);
	}
	
	public int area() {
		return (a.x - b.x + 1) * (a.y - b.y + 1);
	}
}
