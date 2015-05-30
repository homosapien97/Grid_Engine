package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Fire1 extends Spell{
	public static Fire1 fire1 = new Fire1();
	public static final String filename = "fire1.png";
	
	public static Image sprite;

	public Fire1() {
		super(0,0,0,filename,"name");
	}
	
	public static Fire1 get(){
		return fire1;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}
}
