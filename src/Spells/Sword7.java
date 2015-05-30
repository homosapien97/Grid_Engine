package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Sword7 extends Spell{
	public static Sword7 sword7 = new Sword7();
	public static final String filename = "sword7.png";
	
	public static Image sprite;

	public Sword7(){
		super(0,0,0,"name");
	}
	
	public static Sword7 get() {
		return sword7;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
