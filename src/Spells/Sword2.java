package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Sword2 extends Spell{
	public static Sword2 sword2 = new Sword2();
	public static final String filename = "sword2.png";
	
	public static Image sprite;

	public Sword2(){
		super(0,0,0,"name");
	}
	
	public static Sword2 get() {
		return sword2;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
