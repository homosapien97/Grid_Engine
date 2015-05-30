package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Shield6 extends Spell{
	public static Shield6 shield6 = new Shield6();
	public static final String filename = "shield6.png";
	
	public static Image sprite;

	public Shield6(){
		super(0,0,0,filename,"name");
	}
	
	public static Shield6 get() {
		return shield6;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
