package terrain;

import java.awt.Image;

import tools.Tools;

public class Water extends Terrain {
	private static final Water water = new Water();
	private static final String filename = "water.png";
	
	public static Image sprite;
	
	private Water() {
		super();
	}

	public static Water get() {
		return water;
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
		return "water";
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
		return true;
	}
}