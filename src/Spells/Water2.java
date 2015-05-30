package Spells;
import magic.Spell;

public class Water2 extends Spell{
	public static Water2 water2 = new Water2();
	public Water2(){
		super(0,0,0,"name");
	}
	public static Water2 get(){
		return water2;
	}

}
