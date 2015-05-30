package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Fire5 extends Spell{
	public static Fire5 fire5 = new Fire5();
	public static final String filename = "fire5.png";
	
	public static Image sprite;
	
	public Fire5(){
		super(0,0,0,"name");
	}
	
	public static Fire5 get(){
		return fire5;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
