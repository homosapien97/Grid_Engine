package Spells;
import magic.Spell;

public class Plasma3 extends Spell{
	public static Plasma3 plasma3 = new Plasma3();
	public Plasma3(){
		super(0,0,0,"name");
	}
	public static Plasma3 get(){
		return plasma3;
	}

}
