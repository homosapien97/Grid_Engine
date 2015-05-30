package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Shield1 extends Spell{
	public static Shield1 shield1 = new Shield1();
	public static final String filename = "shield1.png";
	
	public static Image sprite;

	public Shield1(){
		super(0,0,0,filename,"name");
	}
	
	public static Shield1 get() {
		return shield1;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
