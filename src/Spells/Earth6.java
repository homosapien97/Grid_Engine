package Spells;
import magic.Spell;

public class Earth6 extends Spell{
	public static Earth6 earth6 = new Earth6();
	public Earth6(){
		super(0,0,0,"name");
	}
	public static Earth6 get(){
		return earth6;
	}

}
