package terrain;

import java.awt.Image;

import tools.Tools;

public class Empty extends Terrain {
	private static final Empty empty = new Empty();
	private static final String filename = "empty.png";

	public static Image sprite;
	
	private Empty() {
		super();
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

	@Override
	public String name() {
		return "empty";
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
