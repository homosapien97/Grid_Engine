package terrain;

import general.Tools;

import java.awt.Image;

public class Wood extends Terrain {
	private static final Wood wood = new Wood();
	private static final String filename = "wood.png";
	
	public static Image sprite;
	
	private Wood() {
		super();
	}

	public static Wood get() {
		return wood;
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
		return "wood";
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