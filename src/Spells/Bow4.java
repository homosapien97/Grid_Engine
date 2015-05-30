package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Bow4 extends Spell{
	public static Bow4 bow4 = new Bow4();
	public static final String filename = "Bow4.png";
	
	public static Image sprite;

	public Bow4(){
		super(0,0,0,"name");
	}
	
	public static Bow4 get() {
		return bow4;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
