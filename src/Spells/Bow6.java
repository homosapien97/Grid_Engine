package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Bow6 extends Spell{
	public static Bow6 bow6 = new Bow6();
	public static final String filename = "Bow6.png";
	
	public static Image sprite;

	public Bow6(){
		super(0,0,0,"name");
	}
	
	public static Bow6 get() {
		return bow6;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
