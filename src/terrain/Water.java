package terrain;

import general.Tools;
import java.awt.Image;

public class Water extends Terrain {
	private static final Water water1 = new Water();
	private static final String filename = "water1.png";
	
	public static Image sprite;
	
	private Water() {
		super("water1", filename);
	}

	public static Water get() {
		return water1;
	}
	
	public String toString() {
		//Decide a letter
		return null;
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
}