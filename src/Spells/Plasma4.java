package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Plasma4 extends Spell{
	public static Plasma4 plasma4 = new Plasma4();
public static final String filename = "plasma4";
	
	public static Image sprite;
	
	public Plasma4(){
		super(0,0,0,"name");
	}
	
	public static Plasma4 get(){
		return plasma4;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
