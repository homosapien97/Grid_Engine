package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Water4 extends Spell{
	public static Water4 water4 = new Water4();
	public static final String filename = "water4";
	
	public static Image sprite;
	
	public Water4(){
		super(0,0,0,filename,"name");
	}
	
	public static Water4 get(){
		return water4;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
