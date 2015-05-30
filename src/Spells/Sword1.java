package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Sword1 extends Spell{
	public static Sword1 sword1 = new Sword1();
	public static final String filename = "shield1.png";
	
	public static Image sprite;

	public Sword1(){
		super(0,0,0,"name");
	}
	
	public static Sword1 get() {
		return sword1;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
