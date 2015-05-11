package terrain;

public class Void extends Terrain{
	private static final Void void_ = new Void();
	private static final String spriteFilepath = "placeholder";
	private Void() {
		super("void",spriteFilepath);
	}
	
	public static Void get() {
		return void_;
	}
}
