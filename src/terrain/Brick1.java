package terrain;

import general.Tools;
import java.awt.Image;

public class Brick1 extends Terrain {
	private static final Brick1 brick1 = new Brick1();
	private static final String filename = "brick1.png";
	
	public static Image sprite;
	
	private Brick1() {
		super("brick1", filename);
	}
	
	public static Brick1 get() {
		return brick1;
	}
	
	public String toString() {
		//Decide a letter
		return null;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
