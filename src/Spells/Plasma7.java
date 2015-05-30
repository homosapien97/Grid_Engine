package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Plasma7 extends Spell{
	public static Plasma7 plasma7 = new Plasma7();
public static final String filename = "plasma7";
	
	public static Image sprite;
	
	public Plasma7(){
		super(0,0,0,"name");
	}
	
	public static Plasma7 get(){
		return plasma7;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
