package entity.interfaces;

import entity.Entity;
import action.MoveAction;
import ai.Path;

public interface Pathing<T extends Entity & Mobile & Sighted & Pathing<T>> {
//	public Vector<Entity> obstacles();
//	public Vector<Class <? extends Entity> > obstacles();
//	public Vector<Terrain> terrainList();
//	public boolean terrainWhitelist();
	public Path<T> getPath();
	public MoveAction<T> pathTo(int x, int y);
	public MoveAction<T> pathToPreview(int x, int y);
}

//TODO: Shortest distance to line algorithm, construct path from two points
