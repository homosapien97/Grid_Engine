package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Bow1 extends Spell{
	public static Bow1 bow1 = new Bow1();
	public static final String filename = "Bow1.png";
	
	public static Image sprite;

	public Bow1(){
		super(0,0,0,"name");
	}
	
	public static Bow1 get() {
		return bow1;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
