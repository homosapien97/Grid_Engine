package Spells;
import magic.Spell;

public class Earth3 extends Spell{
	public static Earth3 earth3 = new Earth3();
	public Earth3(){
		super(0,0,0,"name");
	}
	public static Earth3 get(){
		return earth3;
	}

}
