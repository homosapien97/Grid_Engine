package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Water2 extends Spell{
	public static Water2 water2 = new Water2();
	public static final String filename = "water2";
	
	public static Image sprite;
	
	public Water2(){
		super(0,0,0,"name");
	}
	
	public static Water2 get(){
		return water2;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
