package terrain;

public class Terrain {
	private static Terrain generic_terrain;
//	public final String name;
	public final String name;
	public final String spriteFilepath;
	public static String test = "A";

	
	static {
		generic_terrain = new Terrain();
	}
	
	protected Terrain() {
		name = "";
		spriteFilepath = "";
		test = String.valueOf('A' + test.charAt(0)%26 + 1 );
	}
	
	protected Terrain(String name, String spriteFilepath) {
		this.name = name;
		this.spriteFilepath = spriteFilepath;
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
	
	public String getSpriteFilepath() {
		return spriteFilepath;
	}
	
	public String toString() {
//		return spriteFilepath;
		test = String.valueOf((int) (Math.random() * 10));
		return test;
	}
	
}
