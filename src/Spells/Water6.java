package Spells;
import magic.Spell;

public class Water6 extends Spell{
	public static Water6 water6 = new Water6();
	public Water6(){
		super(0,0,0,"name");
	}
	public static Water6 get(){
		return water6;
	}

}
