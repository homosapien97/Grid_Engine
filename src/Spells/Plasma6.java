package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Plasma6 extends Spell{
	public static Plasma6 plasma6 = new Plasma6();
public static final String filename = "plasma6";
	
	public static Image sprite;
	
	public Plasma6(){
		super(0,0,0,"name");
	}
	
	public static Plasma6 get(){
		return plasma6;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
