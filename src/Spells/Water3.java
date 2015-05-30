package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Water3 extends Spell{
	public static Water3 water3 = new Water3();
	public static final String filename = "water3";
	
	public static Image sprite;
	
	public Water3(){
		super(0,0,0,"name");
	}
	
	public static Water3 get(){
		return water3;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
