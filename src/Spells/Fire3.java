package Spells;
import magic.Spell;

public class Fire3 extends Spell{
	public static Fire3 fire3 = new Fire3();
	public Fire3(){
		super(0,0,0,"name");
	}
	public static Fire3 get(){
		return fire3;
	}

}
