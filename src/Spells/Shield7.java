package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Shield7 extends Spell{
	public static Shield7 shield7 = new Shield7();
	public static final String filename = "shield7.png";
	
	public static Image sprite;

	public Shield7(){
		super(0,0,0,"name");
	}
	
	public static Shield7 get() {
		return shield7;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
