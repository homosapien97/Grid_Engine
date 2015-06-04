package geometry;

import general.Tools;

public class Ray {
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
	
	public Ray(Point a, Point b) {
		this.a = a;
		this.b = b;
		axLbx = a.x < b.x;
		ayLby = a.y < b.y;
		tl = new Point((axLbx) ? a.x : b.x, (ayLby) ? a.y : b.y);
		xoffset = (axLbx) ? 1 : -1;
		yoffset = (ayLby) ? 1 : -1;
//		points = new Point[((axLbx) ? (b.x - a.x) : (a.x - b.x)) + ((ayLby) ? (b.y - a.y) : (a.y - b.y)) + 1];
		int w = (axLbx) ? (b.x - a.x) : (a.x - b.x);
		w = (ayLby) ? ((b.y - a.y > w) ? (b.y - a.y) : w) : ((a.y - b.y > w) ? (a.y - b.y) : w);
		points = new Point[w + 1];
		m = ((double) a.y - b.y) / (a.x - b.x);
		construct();
	}
	public Ray(int x1, int y1, int x2, int y2) {
		a = new Point(x1, y1);
		b = new Point(x2, y2);
		axLbx = a.x < b.x;
		ayLby = a.y < b.y;
		tl = new Point((axLbx) ? a.x : b.x, (ayLby) ? a.y : b.y);
		xoffset = (axLbx) ? 1 : -1;
		yoffset = (ayLby) ? 1 : -1;
//		points = new Point[((axLbx) ? (b.x - a.x) : (a.x - b.x)) + ((ayLby) ? (b.y - a.y) : (a.y - b.y)) + 1];
		int w = (axLbx) ? (b.x - a.x) : (a.x - b.x);
		w = (ayLby) ? ((b.y - a.y > w) ? (b.y - a.y) : w) : ((a.y - b.y > w) ? (a.y - b.y) : w);
		points = new Point[w + 1];
		m = ((double) a.y - b.y) / (a.x - b.x);
		construct();
	}
	/**
	 * Only cuts, doesnt' lengthen
	 * @param from
	 * @param length
	 */
	public Ray(Ray from, int length) {
		if(length < from.points.length && length > 0) {
			a = from.a;
			Point temp = null;
			for(int i = 0; i < length/*from.points.length && Tools.nav.distance(a, from.points[i]) <= length*/; i++) {
				temp = from.points[i];
			}
			b = temp;
			axLbx = a.x < b.x;
			ayLby = a.y < b.y;
			tl = new Point((axLbx) ? a.x : b.x, (ayLby) ? a.y : b.y);
			xoffset = (axLbx) ? 1 : -1;
			yoffset = (ayLby) ? 1 : -1;
			int w = (axLbx) ? (b.x - a.x) : (a.x - b.x);
			w = (ayLby) ? ((b.y - a.y > w) ? (b.y - a.y) : w) : ((a.y - b.y > w) ? (a.y - b.y) : w);
			points = new Point[w + 1];
			m = ((double) a.y - b.y) / (a.x - b.x);
			construct();
		} else {
			//TODO implement line lengthening
			System.out.println("Null Ray");
			a = from.a;
			b = from.b;
			axLbx = from.axLbx;
			ayLby = from.axLbx;
			tl = from.tl;
			xoffset = from.xoffset;
			yoffset = from.yoffset;
			points = from.points;
			m = from.m;
		}
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
		return (p != null && (p.x - a.x + p.y - a.y < points.length) && (p.x - a.x + p.y - a.y > -1)) ? (p.equals(points[p.x - a.x + p.y - a.y])) : (false);
	}
	
	private void construct() {
		points[0] = a;
		for(int i = 1; i < points.length; i++) {
			if(_contains(points[i - 1].x + xoffset, points[i -1].y + yoffset)) {
				points[i] = new Point(points[i - 1], xoffset, yoffset);
			} else if(_contains(points[i - 1].x + xoffset, points[i - 1].y)) {
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
