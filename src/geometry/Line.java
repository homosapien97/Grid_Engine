package geometry;

public class Line {
	public final Point a;
	public final Point b;
	private final Point tl;
	public final Point[] points;
	private final boolean axLbx;
	private final boolean ayLby;
	private final int xoffset;
	private final int yoffset;
	private final double m;
	private boolean[][] arr;
	
	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
		axLbx = a.x < b.x;
		ayLby = a.y < b.y;
		tl = new Point((axLbx) ? a.x : b.x, (ayLby) ? a.y : b.y);
		xoffset = (axLbx) ? 1 : -1;
		yoffset = (ayLby) ? 1 : -1;
		points = new Point[((axLbx) ? (b.x - a.x) : (a.x - b.x)) + ((ayLby) ? (b.y - a.y) : (a.y - b.y)) + 1];
		m = ((double) a.y - b.y) / (a.x - b.x);
		construct();
	}
	public Line(int x1, int y1, int x2, int y2) {
		a = new Point(x1, y1);
		b = new Point(x2, y2);
		axLbx = a.x < b.x;
		ayLby = a.y < b.y;
		tl = new Point((axLbx) ? a.x : b.x, (ayLby) ? a.y : b.y);
		xoffset = (axLbx) ? 1 : -1;
		yoffset = (ayLby) ? 1 : -1;
		points = new Point[((axLbx) ? (b.x - a.x) : (a.x - b.x)) + ((ayLby) ? (b.y - a.y) : (a.y - b.y)) + 1];
		m = ((double) a.y - b.y) / (a.x - b.x);
		construct();
	}
	
	@SuppressWarnings("unused")
	private boolean _contains(Point p) {
		if(a.x == b.x) {
			return (p.x == a.x && ((p.y >= a.y && p.y <= b.y) || (p.y >= b.y && p.y <= a.y)));
		} else {
			double temp2 = (int)(m*(p.x-a.x) + a.y);
			return((p.y == temp2 || (p.y <= temp2 + m && p.y >= temp2 - m))
					&& ((p.x >= a.x && p.x <= b.x) || (p.x >= b.x && p.x <= a.x)) && ((p.y >= a.y && p.y <= b.y) || (p.y >= b.y && a.y <= a.y)));
		}
	}
	
	private boolean _contains(int x, int y) {
		if(a.x == b.x) {
			return (x == a.x && ((y >= a.y && y <= b.y) || (y >= b.y && y <= a.y)));
		} else {
			double temp2 = (int)(m*(x-a.x) + a.y);
			return((y == temp2 || (y <= temp2 + m && y >= temp2 - m))
					&& ((x >= a.x && x <= b.x) || (x >= b.x && x <= a.x)) && ((y >= a.y && y <= b.y) || (y >= b.y && a.y <= a.y)));
		}
	}
	public boolean contains(Point p) {
		return (p == null) ? (false) : (p.equals(points[p.x - a.x + p.y - a.y]));
	}
	
	private void construct() {
		points[0] = a;
		for(int i = 1; i < points.length; i++) {
			if(_contains(points[i - 1].x + xoffset, points[i - 1].y)) {
				points[i] = new Point(points[i - 1], xoffset, 0);
			} else {
				points[i] = new Point(points[i - 1], 0, yoffset);
			}
		}
	}
	
	public boolean[][] arr() {
		if(arr == null) {
			arr = new boolean[1 + ((axLbx) ? (b.x - a.x) : (a.x - b.x))][1 + ((ayLby) ? (b.y - a.y) : (a.y - b.y))];
			for(int i = 0; i < arr.length; i++) {
				for(int j = 0; j < arr[0].length; j++) {
					arr[i][j] = false;
				}
			}
			for(int i = 0; i < points.length; i++) {
				arr[points[i].x - tl.x][points[i].y - tl.y] = true;
			}
		}
		return arr;
	}
	
	public String toString() {
		arr();
		String ret = "";
		for(int j = 0; j < arr[0].length; j++) {
			for(int i = 0; i < arr.length; i++) {
				ret += ((arr[i][j]) ? 1 : 0);
			}
			ret += "\n";
		}
		return ret;
	}
}
