package Spells;
import magic.Spell;

public class Plasma2 extends Spell{
	public static Plasma2 plasma2 = new Plasma2();
	public Plasma2(){
		super(0,0,0,"name");
	}
	public static Plasma2 get(){
		return plasma2;
	}

}
