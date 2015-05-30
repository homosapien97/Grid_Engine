package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Water6 extends Spell{
	public static Water6 water6 = new Water6();
	public static final String filename = "water6";
	
	public static Image sprite;
	
	public Water6(){
		super(0,0,0,"name");
	}
	
	public static Water6 get(){
		return water6;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
