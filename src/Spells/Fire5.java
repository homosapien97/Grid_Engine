package Spells;
import magic.Spell;

public class Fire5 extends Spell{
	public static Fire5 fire5 = new Fire5();
	public Fire5(){
		super(0,0,0,"name");
	}
	public static Fire5 get(){
		return fire5;
	}

}
