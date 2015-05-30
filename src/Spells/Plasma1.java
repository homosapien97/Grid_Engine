package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Plasma1 extends Spell{
	public static Plasma1 plasma1 = new Plasma1();
	public static final String filename = "plasma1";
	
	public static Image sprite;
	
	public Plasma1(){
		super(0,0,0,filename,"name");
	}
	
	public static Plasma1 get(){
		return plasma1;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
