package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Fire3 extends Spell{
	public static Fire3 fire3 = new Fire3();
	public static final String filename = "fire3.png";
	
	public static Image sprite;
	
	public Fire3(){
		super(0,0,0,"name");
	}
	
	public static Fire3 get(){
		return fire3;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
