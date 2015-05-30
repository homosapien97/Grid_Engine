package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Bow7 extends Spell{
	public static Bow7 bow7 = new Bow7();
	public static final String filename = "Bow7.png";
	
	public static Image sprite;

	public Bow7(){
		super(0,0,0,"name");
	}
	
	public static Bow7 get() {
		return bow7;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
