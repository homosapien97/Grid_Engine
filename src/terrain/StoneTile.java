package terrain;

import java.awt.Image;

import tools.Tools;

public class StoneTile extends Terrain {
	private static final StoneTile stoneTile = new StoneTile();
	private static final String filename = "stoneTile.png";
	
	public static Image sprite;
	
	private StoneTile() {
		super();
	}

	public static StoneTile get() {
		return stoneTile;
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
		return "stone tile";
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