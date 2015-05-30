package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Earth4 extends Spell{
	public static Earth4 earth4 = new Earth4();	
	public static final String filename = "earth4.png";
	
	public static Image sprite;

	public Earth4(){
		super(0,0,0,"name");
	}
	
	public static Earth4 get(){
		return earth4;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
