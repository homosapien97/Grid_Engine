package generation;

import terrain.Terrain;
import geometry.Point;
import geometry.Rectangle;

public class GenWall {
	public final Rectangle box;
	public final Terrain material;
	public GenWall(Point a, Point b, Terrain terrain) {
		box = new Rectangle(a, b);
		material = terrain;
	}
	
	public void place() {
		
	}
}
