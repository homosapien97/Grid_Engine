package terrain;

import java.awt.Image;

import tools.Tools;

public class Stone extends Terrain{
	private static final Stone stone = new Stone();
	private static final String filename = "stone.png";
	
	public static Image sprite;
	
	private Stone() {
		super();
	}

	public static Stone get() {
		return stone;
	}
	
	public String toString() {
		return "X";
	}

	public static void load() {
		sprite = Tools.img.loadTerrainSprite(filename);
	}
	
	@Override
	public Image sprite() {
		return sprite;
	}

	@Override
	public String name() {
		return "stone";
	}

	@Override
	public String getSpriteFilename() {
		return filename;
	}

	@Override
	public boolean clear() {
		// TODO Auto-generated method stub
		return false;
	}
}
