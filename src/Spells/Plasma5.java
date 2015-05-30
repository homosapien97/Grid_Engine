package Spells;
import magic.Spell;

public class Plasma5 extends Spell{
	public static Plasma5 plasma5 = new Plasma5();
	public Plasma5(){
		super(0,0,0,"name");
	}
	public static Plasma5 get(){
		return plasma5;
	}

}
