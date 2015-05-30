package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Earth3 extends Spell {
	public static Earth3 earth3 = new Earth3();
	public static final String filename = "earth3.png";
	
	public static Image sprite;
	
	public Earth3(){
		super(0,0,0,"name");
	}
	
	public static Earth3 get() {
		return earth3;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
