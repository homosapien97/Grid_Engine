package terrain;

import general.Tools;
import java.awt.Image;

public class Pit1 extends Terrain {
	private static final Pit1 pit1 = new Pit1();
	private static final String filename = "pit1.png";
	
	public static Image sprite;
	
	private Pit1() {
		super("pit1", filename);
	}

	public static Pit1 get() {
		return pit1;
	}
	
	public String toString() {
		//Decide a letter
		return null;
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
}