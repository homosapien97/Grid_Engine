package terrain;

import general.Tools;

import java.awt.Image;

public class Quicksand extends Terrain {
	private static final Quicksand quicksand = new Quicksand();
	private static final String filename = "quicksand.png";
	
	public static Image sprite;
	
	private Quicksand() {
		super("quicksand", filename);
	}

	public static Quicksand get() {
		return quicksand;
	}
	
	public String toString() {
		return "Q";
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
}
