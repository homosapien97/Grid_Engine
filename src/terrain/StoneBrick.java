package terrain;

import general.Tools;
import java.awt.Image;

public class StoneBrick extends Terrain {
	private static final StoneBrick stoneTile2 = new StoneBrick();
	private static final String filename = "stoneTile2.png";
	
	public static Image sprite;
	
	private StoneBrick() {
		super("stoneTile2", filename);
	}

	public static StoneBrick get() {
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