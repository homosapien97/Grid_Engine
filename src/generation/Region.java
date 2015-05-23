package generation;

import java.util.HashMap;

import terrain.Terrain;

public abstract class Region {
	public final String name;
	
	protected Region(String name) {
		this.name = name;
	}
	protected Region(String name, HashMap<Feature, Double> featureFreq) {
		this.name = name;
	}
	public abstract double getFreq(Feature f);
	public abstract Terrain getDefaultTerrain();
	public abstract Region get();
}
