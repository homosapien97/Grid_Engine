package terrain;

import general.Tools;

import java.awt.Image;

public class Quicksand extends Terrain {
	private static final Quicksand quicksand = new Quicksand();
	//private static final String spriteFilepath = "S";
	private static final String filename = "quicksand.png";
	
	public static final Image sprite = Tools.img.loadTerrainSprite(filename);
	
	private Quicksand() {
		super("quicksand",filename);
	}

	public static Quicksand get() {
//		spriteFilepath = (Math.random() > .5) ? "~" : "S";
		return quicksand;
	}
}
