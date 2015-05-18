package geometry;

public class Line {
	public final int x1;
	public final int y1;
	public final int x2;
	public final int y2;
	public boolean[][] mask;
	private boolean x1lx2;
	private boolean y1ly2;
	private double temp1;
	private int temp2;
	
	public Line(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		x1lx2 = x1 < x2;
		y1ly2 = y1 < y2;
	}
	
	public boolean vertical() {
		return (x1 == x2);
	}
	public boolean horizontal() {
		return (y1 == y2);
	}
	
	public boolean inside(int x, int y) {
		if(x1 == x2) {
			return (x == x1 && ((y >= y1 && y <= y2) || (y >= y2 && x <= y1)));
		} else {
			temp1 = (double)(y1-y2)/(x1-x2);
			temp2 = (int)(temp1*(x-x1) + y1);
			return((y == temp2 || (y <= temp2 + temp1 && y >= temp2 - temp1))
					&& ((x >= x1 && x <= x2) || (x >= x2 && x <= x1)) && ((y >= y1 && y <= y2) || (y >= y2 && x <= y1)));
		}
	}
	
	public boolean[][] generateMask() {
		if(mask == null) {
			mask = new boolean[(x1lx2) ? (x2 - x1 + 1) : (x1 - x2 + 1)][(y1ly2) ? (y2 - y1 + 1) : (y1 - y2 + 1)];
			int blx = x1lx2 ? x1 : x2;
			int bly = y1ly2 ? y1 : y2;
			for(int i = 0; i < mask.length; i++) {
				for(int j = 0; j < mask[0].length; j++) {
					mask[i][j] = inside(blx + i, bly + j);
				}
			}
		}
		return mask;
	}
}
