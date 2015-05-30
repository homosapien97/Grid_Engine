package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Bow5 extends Spell{
	public static Bow5 bow5 = new Bow5();
	public static final String filename = "Bow5.png";
	
	public static Image sprite;

	public Bow5(){
		super(0,0,0,filename,"name");
	}
	
	public static Bow5 get() {
		return bow5;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
