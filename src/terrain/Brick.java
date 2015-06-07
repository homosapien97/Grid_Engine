package terrain;

import java.awt.Image;

import tools.Tools;

public class Brick extends Terrain {
	private static final Brick brick = new Brick();
	private static final String filename = "brick.png";
	
	public static Image sprite;
	
	private Brick() {
		super();
	}
	
	public static Brick get() {
		return brick;
	}
	
	public String toString() {
		//Decide a letter
		return null;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

	@Override
	public String name() {
		return "brick";
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
