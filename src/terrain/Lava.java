package terrain;

import general.Tools;
import java.awt.Image;

public class Lava extends Terrain {
	private static final Lava lava1 = new Lava();
	private static final String filename = "lava1.png";
	
	public static Image sprite;
	
	private Lava() {
		super("lava1", filename);
	}

	public static Lava get() {
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