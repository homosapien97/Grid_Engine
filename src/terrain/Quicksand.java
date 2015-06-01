package terrain;

import general.Tools;

import java.awt.Image;

public class Quicksand extends Terrain {
	private static final Quicksand quicksand = new Quicksand();
	private static final String filename = "quicksand.png";
	
	public static Image sprite;
	
	private Quicksand() {
		super();
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

	@Override
	public String name() {
		return "quicksand";
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
