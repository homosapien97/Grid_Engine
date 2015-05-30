package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Bow3 extends Spell{
	public static Bow3 bow3 = new Bow3();
	public static final String filename = "Bow3.png";
	
	public static Image sprite;

	public Bow3(){
		super(0,0,0,"name");
	}
	
	public static Bow3 get() {
		return bow3;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
