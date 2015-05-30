package Spells;
import magic.Spell;

public class Earth2 extends Spell{
	public static Earth2 earth2 = new Earth2();
	public Earth2(){
		super(0,0,0,"name");
	}
	public static Earth2 get(){
		return earth2;
	}

}
