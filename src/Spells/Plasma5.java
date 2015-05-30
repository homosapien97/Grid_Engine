package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Plasma5 extends Spell{
	public static Plasma5 plasma5 = new Plasma5();
public static final String filename = "plasma5";
	
	public static Image sprite;
	
	public Plasma5(){
		super(0,0,0,"name");
	}
	
	public static Plasma5 get(){
		return plasma5;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
