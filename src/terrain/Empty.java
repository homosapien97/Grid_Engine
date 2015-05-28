package terrain;

import general.Tools;

import java.awt.Image;

public class Empty extends Terrain {
	private static final Empty empty = new Empty();
	private static final String filename = "void.png";

	public static Image sprite;
	
	private Empty() {
		super("void", filename, true);
	}
	
	public static Empty get() {
		return empty;
	}
	
	public String toString() {
		return "V";
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
}
