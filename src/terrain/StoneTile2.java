package terrain;

import general.Tools;
import java.awt.Image;

public class StoneTile2 extends Terrain {
	private static final StoneTile2 stoneTile2 = new StoneTile2();
	private static final String filename = "stoneTile2.png";
	
	public static Image sprite;
	
	private StoneTile2() {
		super("stoneTile2", filename);
	}

	public static StoneTile2 get() {
		return stoneTile2;
	}
	
	public String toString() {
		//Decide a letter
		return null;
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
}