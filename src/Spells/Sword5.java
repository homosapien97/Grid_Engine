package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Sword5 extends Spell{
	public static Sword5 sword5 = new Sword5();
	public static final String filename = "sword5.png";
	
	public static Image sprite;

	public Sword5(){
		super(0,0,0,filename,"name");
	}
	
	public static Sword5 get() {
		return sword5;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
