package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Sword4 extends Spell{
	public static Sword4 sword4 = new Sword4();
	public static final String filename = "sword4.png";
	
	public static Image sprite;

	public Sword4(){
		super(0,0,0,"name");
	}
	
	public static Sword4 get() {
		return sword4;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
