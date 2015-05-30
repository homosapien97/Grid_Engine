package Spells;
import magic.Spell;

public class Water7 extends Spell{
	public static Water7 water7 = new Water7();
	public Water7(){
		super(0,0,0,"name");
	}
	public static Water7 get(){
		return water7;
	}

}
