package terrain;

import java.awt.Image;

public abstract class Terrain {
//	private static Terrain generic_terrain;
//	public final String name;
//	public final String filename;
//	public static final Image sprite = null;
//	public final boolean clear;
//	public static String test = "A";

	
//	static {
//		generic_terrain = new Terrain();
//	}
	
	protected Terrain() {
//		name = "";
//		filename = "";
////		sprite = null;
//		clear = false;
////		test = String.valueOf('A' + test.charAt(0)%26 + 1 );
	}
	
//	protected Terrain(String name, String filename) {
//		this.name = name;
//		this.filename = filename;
//		clear = false;
////		this.sprite = Tools.img.loadTerrainSprite(filename);
//	}
//	protected Terrain(String name, String filename, boolean clear) {
//		this.name = name;
//		this.filename = filename;
//		this.clear = clear;
////		this.sprite = Tools.img.loadTerrainSprite(filename);
//	}
	
	/**
	 * To be overridden by subclasses, which should return a static instance of their own type.
	 * @return
	 */
	public static Terrain get() {
		return null;
	}
	
	public abstract String name();
	
	public abstract String getSpriteFilename();
	
	public abstract Image sprite();
	
	public abstract String toString();
	
	public abstract boolean clear();
	
}
