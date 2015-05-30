package Spells;
import magic.Spell;

public class Water3 extends Spell{
	public static Water3 water3 = new Water3();
	public Water3(){
		super(0,0,0,"name");
	}
	public static Water3 get(){
		return water3;
	}

}
