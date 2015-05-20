package entity;

import general.Tools;
import geometry.Point;
import geometry.Rectangle;

import java.util.Vector;

import world.Chunk;
import world.Generator;

public class Wall extends Entity implements Armored, Health, Large{
	public final Chunk[][] chunks;
	public final Rectangle box;
	public Wall(int x, int y, Chunk chunk, String sprite, Rectangle box) {
		super(x, y, chunk, sprite);
		this.box = box;
		chunks = new Chunk[Tools.nav.absCoordToChunkCoord(box.b.x) - Tools.nav.absCoordToChunkCoord(box.a.x) + 1][Tools.nav.absCoordToChunkCoord(box.b.y) - Tools.nav.absCoordToChunkCoord(box.a.y) + 1];
		updateChunks();
	}
	public Wall(int x, int y, Chunk chunk, String sprite, int x1, int y1, int x2, int y2) {
		super(x, y, chunk, sprite);
		this.box = new Rectangle(x1, y1, x2, y2);
		chunks = new Chunk[Tools.nav.absCoordToChunkCoord(box.b.x) - Tools.nav.absCoordToChunkCoord(box.a.x) + 1][Tools.nav.absCoordToChunkCoord(box.b.y) - Tools.nav.absCoordToChunkCoord(box.a.y) + 1];
		updateChunks();
	}
	
	private void updateChunks() {
		Point tlc = Tools.nav.absPointToChunkPoint(box.a);
//		Point brc = Tools.nav.absPointToChunkPoint(box.b);
		chunks[0][0] = chunk;
		for(int i = 1; i < chunks.length; i++) {
			chunks[i][0] = chunks[i - 1][0].neighbors[2][1];
			if(chunks[i][0] == null) chunks[i][0] = Generator.generateChunk(tlc.x + i, tlc.y);
		}
		for(int j = 1; j < chunks[0].length; j++) {
			chunks[0][j] = chunks[0][j-1].neighbors[1][2];
			if(chunks[0][j] == null) chunks[0][j] = Generator.generateChunk(tlc.x, tlc.y + j);
			for(int i = 1; i < chunks.length; i++) {
				chunks[i][j] = chunks[i - 1][j].neighbors[2][1];
				if(chunks[i][j] == null) chunks[i][j] = Generator.generateChunk(tlc.x + i, tlc.x + j);
			}
		}
	}
	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hurt(int damage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int heal(int healing) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getFlatArmor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getProportionalArmor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<Chunk> getChunks() {
		if(chunks[0][0] == null) updateChunks();
		Vector<Chunk> ret = new Vector<Chunk>(chunks.length * chunks[0].length);
		for(int j = 0; j < chunks[0].length; j++) {
			for(int i = 0; i < chunks.length; i++) {
				ret.add(chunks[i][j]);
			}
		}
		return ret;
	}

	@Override
	public boolean contains(Point p) {
		return box.contains(p);
	}

	@Override
	public boolean contains(int x, int y) {
		return box.contains(x, y);
	}
}
