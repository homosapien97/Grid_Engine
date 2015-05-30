package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Earth1 extends Spell {
	public static Earth1 earth1 = new Earth1();
	public static final String filename = "earth1.png";
	
	public static Image sprite;

	public Earth1(){
		super(0,0,0,"name");
	}
	
	public static Earth1 get() {
		return earth1;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
