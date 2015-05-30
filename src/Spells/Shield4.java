package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Shield4 extends Spell{
	public static Shield4 shield4 = new Shield4();
	public static final String filename = "shield4.png";
	
	public static Image sprite;

	public Shield4(){
		super(0,0,0,"name");
	}
	
	public static Shield4 get() {
		return shield4;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
