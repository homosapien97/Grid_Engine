package Spells;
import magic.Spell;

public class Earth1 extends Spell{
	public static Earth1 earth1 = new Earth1();
	public Earth1(){
		super(0,0,0,"name");
	}
	public static Earth1 get(){
		return earth1;
	}

}
