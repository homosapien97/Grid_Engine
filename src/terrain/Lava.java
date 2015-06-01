package terrain;

import general.Tools;

import java.awt.Image;

public class Lava extends Terrain {
	private static final Lava lava = new Lava();
	private static final String filename = "lava.png";
	
	public static Image sprite;
	
	private Lava() {
		super();
	}

	public static Lava get() {
		return lava;
	}
	
	public String toString() {
		//Decide a letter
		return null;
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}

	@Override
	public String name() {
		return "lava";
	}

	@Override
	public String getSpriteFilename() {
		return filename;
	}

	@Override
	public Image sprite() {
		return sprite;
	}

	@Override
	public boolean clear() {
		// TODO Auto-generated method stub
		return false;
	}
}