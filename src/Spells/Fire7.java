package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Fire7 extends Spell{
	public static Fire7 fire7 = new Fire7();
	public static final String filename = "fire7.png";
	
	public static Image sprite;

	
	public Fire7(){
		super(0,0,0,"name");
	}
	
	public static Fire7 get(){
		return fire7;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
