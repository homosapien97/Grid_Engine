package spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Fire4 extends Spell{
	public static Fire4 fire4 = new Fire4();
	public static final String filename = "fire4.png";
	
	public static Image sprite;

	public Fire4(){
		super(0,0,0,"name");
	}
	
	public static Fire4 get(){
		return fire4;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
