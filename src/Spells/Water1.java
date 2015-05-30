package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Water1 extends Spell{
	public static Water1 water1 = new Water1();
	public static final String filename = "water1";
	
	public static Image sprite;
	
	public Water1(){
		super(0,0,0,filename,"name");
	}
	
	public static Water1 get(){
		return water1;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
