package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Fire6 extends Spell{
	public static Fire6 fire6 = new Fire6();
	public static final String filename = "fire6.png";
	
	public static Image sprite;
	
	public Fire6(){
		super(0,0,0,"name");
	}
	
	public static Fire6 get(){
		return fire6;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
