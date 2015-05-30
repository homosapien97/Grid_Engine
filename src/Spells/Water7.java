package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Water7 extends Spell{
	public static Water7 water7 = new Water7();
	public static final String filename = "water7";
	
	public static Image sprite;
	
	public Water7(){
		super(0,0,0,filename,"name");
	}
	
	public static Water7 get(){
		return water7;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}
}
