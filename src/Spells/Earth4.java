package Spells;
import magic.Spell;

public class Earth4 extends Spell{
	public static Earth4 earth4 = new Earth4();
	public Earth4(){
		super(0,0,0,"name");
	}
	public static Earth4 get(){
		return earth4;
	}

}
