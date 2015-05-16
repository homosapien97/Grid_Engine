package geometry;

public class Circle {
	public final int x;
	public final int y;
	public final int r;
	public boolean[][] mask;
	
	public Circle(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public boolean inside(int x, int y) {
		return ((x - this.x)*(x - this.x) + (y - this.y)*(y - this.y) <= r * r);
	}
	public boolean[][] generateMask() {
		if(mask == null) {
			mask = new boolean[r * 2 + 1][r * 2 + 1];
			for(int i = 0; i < r * 2 + 1; i++) {
				for(int j = 0; j < r * 2 + 1; j++) {
					mask[i][j] = ((i- r) * (i - r) + (j - r) * (j - r) <= r * r);
				}
			}
		}
		return mask;
	}
}
