package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Earth7 extends Spell{
	public static Earth7 earth7 = new Earth7();
	public static final String filename = "earth7.png";
	
	public static Image sprite;

	public Earth7(){
		super(0,0,0,"name");
	}
	
	public static Earth7 get(){
		return earth7;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
