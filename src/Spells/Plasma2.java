package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Plasma2 extends Spell{
	public static Plasma2 plasma2 = new Plasma2();
	public static final String filename = "plasma2";
	
	public static Image sprite;
	
	public Plasma2(){
		super(0,0,0,"name");
	}
	
	public static Plasma2 get(){
		return plasma2;
	}

	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}
}
