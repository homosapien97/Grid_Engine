package Spells;
import magic.Spell;

public class Fire4 extends Spell{
	public static Fire4 fire4 = new Fire4();
	public Fire4(){
		super(0,0,0,"name");
	}
	public static Fire4 get(){
		return fire4;
	}

}
