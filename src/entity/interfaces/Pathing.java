package entity.interfaces;

import entity.Entity;
import entity.Player;
import action.MoveAction;
import ai.Path;

public interface Pathing<T extends Entity & Mobile & Sighted & Pathing<T>> {
//	public Vector<Entity> obstacles();
//	public Vector<Class <? extends Entity> > obstacles();
//	public Vector<Terrain> terrainList();
//	public boolean terrainWhitelist();
	public Path<T> getPath();
	public boolean pathTo(int x, int y);
	public MoveAction<T> pathToPreview(int x, int y);
}

//TODO: Shortest distance to line algorithm, construct path from two points
