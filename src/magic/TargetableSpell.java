package magic;

import geometry.Point;

public interface TargetableSpell {
	public Point[] getTargetPoints();
	//Multiple points can be in a targetable spell--if it's a line from the player or a circle with variable radius from the player, only one is needed.
	//If it's a line not from the player, a circle with variable radius not centered on the player, or a rectangle with variable a&b points, more are needed.
}
