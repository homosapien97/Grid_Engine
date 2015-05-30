package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Earth2 extends Spell{
	public static Earth2 earth2 = new Earth2();
	public static final String filename = "earth2.png";
	
	public static Image sprite;
	
	public Earth2(){
		super(0,0,0,"name");
	}
	
	public static Earth2 get(){
		return earth2;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
