package terrain;

public class Terrain {
	private static Terrain generic_terrain;
	public final String name;
	public final String spriteFilepath;

	
	static {
		generic_terrain = new Terrain();
	}
	
	protected Terrain() {
		name = "";
		spriteFilepath = "";

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
	
}
