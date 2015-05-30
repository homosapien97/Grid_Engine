package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Shield2 extends Spell{
	public static Shield2 shield2 = new Shield2();
	public static final String filename = "shield2.png";
	
	public static Image sprite;

	public Shield2(){
		super(0,0,0,filename,"name");
	}
	
	public static Shield2 get() {
		return shield2;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}