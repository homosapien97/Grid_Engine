package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Shield3 extends Spell{
	public static Shield3 shield3 = new Shield3();
	public static final String filename = "shield3.png";
	
	public static Image sprite;

	public Shield3(){
		super(0,0,0,"name");
	}
	
	public static Shield3 get() {
		return shield3;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
