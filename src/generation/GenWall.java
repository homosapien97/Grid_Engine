package generation;

import terrain.Terrain;
import world.Chunk;
import general.Tools;
import geometry.Point;
import geometry.Rectangle;

public class GenWall extends Feature{
	public final Rectangle box;
	public final Terrain material;
	public boolean generated = false;
	Chunk[][] chunks = null;
	public GenWall(Point a, Point b, Terrain terrain) {
		super(a);
		box = new Rectangle(a, b);
		material = terrain;
	}

	@Override
	public boolean generate() {
		if(generated) return true;
		if(chunks == null) {
			chunks = Chunk.loadChunks(Tools.nav.absPointToChunkPoint(box.a), Tools.nav.absPointToChunkPoint(box.b));
		}
		int tlx = Tools.nav.absCoordToChunkCoord(box.a.x);
		int tly = Tools.nav.absCoordToChunkCoord(box.a.y);
		for(int i = box.a.x; i < box.b.x; i++) {
			for(int j = box.a.y; j < box.b.y; j++) {
				chunks[Tools.nav.absCoordToChunkCoord(i) - tlx][Tools.nav.absCoordToChunkCoord(j) - tly].terrain[Tools.nav.absCoordToMinorCoord(i)][Tools.nav.absCoordToMinorCoord(j)] = material;
				chunks[Tools.nav.absCoordToChunkCoord(i) - tlx][Tools.nav.absCoordToChunkCoord(j) - tly].heightmap[Tools.nav.absCoordToMinorCoord(i)][Tools.nav.absCoordToMinorCoord(j)] = 1;
			}
		}
		chunks = null; //allow for garbage collection. Generator Features should be created once and thrown away.
		generated = true;
		return generated;
	}

	@Override
	public boolean contains(int x, int y) {
		return box.contains(x, y);
	}

	@Override
	public boolean contains(Point p) {
		return box.contains(p);
	}
	
}
