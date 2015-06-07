package terrain;

import java.awt.Image;

import tools.Tools;

public class StoneBrick extends Terrain {
	private static final StoneBrick stoneBrick = new StoneBrick();
	private static final String filename = "stoneBrick.png";
	
	public static Image sprite;
	
	private StoneBrick() {
		super();
	}

	public static StoneBrick get() {
		return stoneBrick;
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
		return "stone brick";
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