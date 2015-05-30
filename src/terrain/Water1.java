package terrain;

import general.Tools;
import java.awt.Image;

public class Water1 extends Terrain {
	private static final Water1 water1 = new Water1();
	private static final String filename = "water1.png";
	
	public static Image sprite;
	
	private Water1() {
		super("water1", filename);
	}

	public static Water1 get() {
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