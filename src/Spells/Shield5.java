package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Shield5 extends Spell{
	public static Shield5 shield5 = new Shield5();
	public static final String filename = "shield5.png";
	
	public static Image sprite;

	public Shield5(){
		super(0,0,0,"name");
	}
	
	public static Shield5 get() {
		return shield5;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
