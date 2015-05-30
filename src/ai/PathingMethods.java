package ai;

import world.LoadedChunks;
import entity.Entity;
import entity.Mobile;
import entity.Pathing;
import geometry.Ray;
import geometry.Point;

public class PathingMethods {
	public static BranchingPath path;
	
	/**
	 * Paths a Mobile, Pathing entity to a given point
	 * @param actor
	 * @param absoluteX
	 * @param absoluteY
	 * @return
	 */
	public static <T extends Entity & Mobile & Pathing> boolean pathTo(T actor, int absoluteX, int absoluteY) {
		//note to self : actor.obstacles().contains(Class whatever)
		//note to self : z.isAssignableFrom(c)) (both classes)
//		BranchingPath path = new BranchingPath(actor.getAbsoluteX(), actor.getAbsoluteY());
//		Point end = new Point(absoluteX, absoluteY);
//		Point endPTemp = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE); //I know this is bad practice, but entities should never try to path to this point ever.
//		Ray straight = new Ray(actor.getAbsoluteX(), actor.getAbsoluteY(), absoluteX, absoluteY);
//		while(endPTemp != null) {
//			for(int i = 1; i < straight.points.length; i++) {
//				if(actor.terrainWhitelist()) {
//					
//				} else {
//					
//				}
//				for(Entity e : LoadedChunks.entitiesAt(straight.points[i].x, straight.points[i].y)) {
//					if(actor.obstacles().contains(e.getClass())) {
//						endPTemp = straight.points[i - 1];
//					}
//				}
//			}
//		}
		
		
		
		
		return false;
	}
}
