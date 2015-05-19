package terrain;

import java.awt.Image;

public class Terrain {
	private static Terrain generic_terrain;
//	public final String name;
	public final String name;
	public final String filename;
	public final Image sprite = null;
	public static String test = "A";

	
	static {
		generic_terrain = new Terrain();
	}
	
	protected Terrain() {
		name = "";
		filename = "";
		test = String.valueOf('A' + test.charAt(0)%26 + 1 );
	}
	
	protected Terrain(String name, String filename) {
		this.name = name;
		this.filename = filename;
	}
	
	/**
	 * To be overridden by subclasses, which should return a static instance of their own type.
	 * @return
	 */
	public static Terrain get() {
		return generic_terrain;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSpriteFilename() {
		return filename;
	}
	
	public String toString() {
		return filename;
//		test = String.valueOf((int) (Math.random() * 10));
//		return test;
	}
	
}
