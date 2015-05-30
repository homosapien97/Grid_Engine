package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Sword3 extends Spell{
	public static Sword3 sword3 = new Sword3();
	public static final String filename = "sword3.png";
	
	public static Image sprite;

	public Sword3(){
		super(0,0,0,filename,"name");
	}
	
	public static Sword3 get() {
		return sword3;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
