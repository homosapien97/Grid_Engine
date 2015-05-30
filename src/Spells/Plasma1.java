package Spells;
import magic.Spell;

public class Plasma1 extends Spell{
	public static Plasma1 plasma1 = new Plasma1();
	public Plasma1(){
		super(0,0,0,"name");
	}
	public static Plasma1 get(){
		return plasma1;
	}

}
