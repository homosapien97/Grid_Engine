package terrain;

import general.Tools;

import java.awt.Image;

public class Void extends Terrain {
	private static final Void void_ = new Void();
	private static final String filename = "void.png";

	public static Image sprite;
	
	private Void() {
		super("void", filename, true);
	}
	
	public static Void get() {
		return void_;
	}
	
	public String toString() {
		return "V";
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
}
