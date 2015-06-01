package terrain;

import general.Tools;

import java.awt.Image;

public class Pit extends Terrain {
	private static final Pit pit = new Pit();
	private static final String filename = "pit.png";
	
	public static Image sprite;
	
	private Pit() {
		super();
	}

	public static Pit get() {
		return pit;
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
		return "pit";
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