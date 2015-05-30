package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Water5 extends Spell{
	public static Water5 water5 = new Water5();
	public static final String filename = "water5";
	
	public static Image sprite;
	
	public Water5(){
		super(0,0,0,filename,"name");
	}
	
	public static Water5 get(){
		return water5;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
