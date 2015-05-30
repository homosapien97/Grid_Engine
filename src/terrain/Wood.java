package terrain;

import general.Tools;
import java.awt.Image;

public class Wood extends Terrain {
	private static final Wood wood1 = new Wood();
	private static final String filename = "wood1.png";
	
	public static Image sprite;
	
	private Wood() {
		super("wood1", filename);
	}

	public static Wood get() {
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