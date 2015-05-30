package Spells;
import magic.Spell;

public class Fire1 extends Spell{
	public static Fire1 fire1 = new Fire1();
	public Fire1() {
		super(0,0,0,"name");
	}
	public static Fire1 get(){
		return fire1;
	}
}
