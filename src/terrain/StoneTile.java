package terrain;

import general.Tools;
import java.awt.Image;

public class StoneTile extends Terrain {
	private static final StoneTile stoneTile1 = new StoneTile();
	private static final String filename = "stoneTile1.png";
	
	public static Image sprite;
	
	private StoneTile() {
		super("stoneTile1", filename);
	}

	public static StoneTile get() {
		return stoneTile1;
	}
	
	public String toString() {
		//Decide a letter
		return null;
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
}