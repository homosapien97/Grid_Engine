package world.generation;

import terrain.Stone;
import terrain.Terrain;

public class Castle extends Region {
	private static Castle castle = new Castle();
	protected Castle() {
		super("Castle");
	}
	
	@Override
	public double getFreq(Feature f) {
		if(f.getClass().equals(GenWall.class)) return 3.0;
		else return 0.0;
	}
	
	@Override
	public Terrain getDefaultTerrain() {
		return Stone.get();
	}

	@Override
	public Region get() {
		return castle;
	}

}
