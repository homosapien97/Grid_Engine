package Spells;
import magic.Spell;

public class Water5 extends Spell{
	public static Water5 water5 = new Water5();
	public Water5(){
		super(0,0,0,"name");
	}
	public static Water5 get(){
		return water5;
	}

}
