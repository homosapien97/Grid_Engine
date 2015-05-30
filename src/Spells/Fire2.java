package Spells;
import magic.Spell;

public class Fire2 extends Spell{
	public static Fire2 fire2 = new Fire2();
	public Fire2() {
		super(0,0,0,"name");

	}
	public static Fire2 get() {
		return fire2;
	}
}
