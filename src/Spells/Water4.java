package Spells;
import magic.Spell;

public class Water4 extends Spell{
	public static Water4 water4 = new Water4();
	public Water4(){
		super(0,0,0,"name");
	}
	public static Water4 get(){
		return water4;
	}

}
