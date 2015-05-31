package terrain;

import java.awt.Image;
import general.Tools;

public class Stone extends Terrain{
	private static final Stone stone = new Stone();
	private static final String filename = "stone.png";
	
	public static Image sprite;
	
	private Stone() {
		super("stone", filename);
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
}
