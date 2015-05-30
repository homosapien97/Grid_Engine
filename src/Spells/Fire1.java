package Spells;
import magic.Spell;

public class Fire1 extends Spell{
//	public static Fire1 fire1 = new Fire1();
	public static Fire1 fire1;
	static {
		fire1 = new Fire1();
	}
	public Fire1() {
		super(0,0,0,"name");
		// TODO Auto-generated constructor stub
	}
	public static Fire1 get() {
		return fire1;
	}
}
