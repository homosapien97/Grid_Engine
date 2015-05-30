package terrain;

import general.Tools;
import java.awt.Image;

public class StoneTile1 extends Terrain {
	private static final StoneTile1 stoneTile1 = new StoneTile1();
	private static final String filename = "stoneTile1.png";
	
	public static Image sprite;
	
	private StoneTile1() {
		super("stoneTile1", filename);
	}

	public static StoneTile1 get() {
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