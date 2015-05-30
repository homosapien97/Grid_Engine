package terrain;

import general.Tools;
import java.awt.Image;

public class Lava1 extends Terrain {
	private static final Lava1 lava1 = new Lava1();
	private static final String filename = "lava1.png";
	
	public static Image sprite;
	
	private Lava1() {
		super("lava1", filename);
	}

	public static Lava1 get() {
		return lava1;
	}
	
	public String toString() {
		//Decide a letter
		return null;
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
}