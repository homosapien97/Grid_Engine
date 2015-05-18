package terrain;

public class Quicksand extends Terrain {
	private static final Quicksand quicksand = new Quicksand();
	//private static final String spriteFilepath = "S";
	private static final String spriteFilepath = "quicksand.png";
	
	private Quicksand() {
		super("quicksand",spriteFilepath);
	}

	public static Quicksand get() {
//		spriteFilepath = (Math.random() > .5) ? "~" : "S";
		return quicksand;
	}
}
