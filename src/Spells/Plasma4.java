package Spells;
import magic.Spell;

public class Plasma4 extends Spell{
	public static Plasma4 plasma4 = new Plasma4();
	public Plasma4(){
		super(0,0,0,"name");
	}
	public static Plasma4 get(){
		return plasma4;
	}

}
