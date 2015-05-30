package Spells;
import magic.Spell;

public class Water1 extends Spell{
	public static Water1 water1 = new Water1();
	public Water1(){
		super(0,0,0,"name");
	}
	public static Water1 get(){
		return water1;
	}

}
