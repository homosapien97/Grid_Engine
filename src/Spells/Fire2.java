package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Fire2 extends Spell{
	public static Fire2 fire2 = new Fire2();
	public static final String filename = "fire2.png";
	
	public static Image sprite;
	
	public Fire2() {
		super(0,0,0,"name");
	}
	
	public static Fire2 get() {
		return fire2;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}
}
