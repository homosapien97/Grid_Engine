package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Earth6 extends Spell{
	public static Earth6 earth6 = new Earth6();
	public static final String filename = "earth61.png";
	
	public static Image sprite;

	public Earth6(){
		super(0,0,0,"name");
	}
	
	public static Earth6 get(){
		return earth6;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
