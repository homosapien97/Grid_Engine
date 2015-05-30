package terrain;

import general.Tools;
import java.awt.Image;

public class Wood1 extends Terrain {
	private static final Wood1 wood1 = new Wood1();
	private static final String filename = "wood1.png";
	
	public static Image sprite;
	
	private Wood1() {
		super("wood1", filename);
	}

	public static Wood1 get() {
		return wood1;
	}
	
	public String toString() {
		//Decide a letter
		return null;
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
}