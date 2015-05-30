package Spells;
import magic.Spell;

public class Earth7 extends Spell{
	public static Earth7 earth7 = new Earth7();
	public Earth7(){
		super(0,0,0,"name");
	}
	public static Earth7 get(){
		return earth7;
	}

}
