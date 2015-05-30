package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Sword6 extends Spell{
	public static Sword6 sword6 = new Sword6();
	public static final String filename = "sword6.png";
	
	public static Image sprite;

	public Sword6(){
		super(0,0,0,"name");
	}
	
	public static Sword6 get() {
		return sword6;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
