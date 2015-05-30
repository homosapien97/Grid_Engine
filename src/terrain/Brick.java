package terrain;

import general.Tools;
import java.awt.Image;

public class Brick extends Terrain {
	private static final Brick brick1 = new Brick();
	private static final String filename = "brick1.png";
	
	public static Image sprite;
	
	private Brick() {
		super("brick1", filename);
	}
	
	public static Brick get() {
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
