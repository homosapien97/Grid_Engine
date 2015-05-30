package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Earth5 extends Spell{
	public static Earth5 earth5 = new Earth5();
	public static final String filename = "earth5.png";
	
	public static Image sprite;
	
	public Earth5(){
		super(0,0,0,filename,"name");
	}
	
	public static Earth5 get(){
		return earth5;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
