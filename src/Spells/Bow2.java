package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Bow2 extends Spell{
	public static Bow2 bow2 = new Bow2();
	public static final String filename = "Bow2.png";
	
	public static Image sprite;

	public Bow2(){
		super(0,0,0,filename,"name");
	}
	
	public static Bow2 get() {
		return bow2;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
