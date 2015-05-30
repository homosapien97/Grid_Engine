package Spells;
import magic.Spell;

public class Earth5 extends Spell{
	public static Earth5 earth5 = new Earth5();
	public Earth5(){
		super(0,0,0,"name");
	}
	public static Earth5 get(){
		return earth5;
	}

}
