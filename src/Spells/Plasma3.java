package Spells;
import magic.Spell;
import general.Tools;
import java.awt.Image;

public class Plasma3 extends Spell{
	public static Plasma3 plasma3 = new Plasma3();
public static final String filename = "plasma3";
	
	public static Image sprite;
	
	public Plasma3(){
		super(0,0,0,"name");
	}
	
	public static Plasma3 get(){
		return plasma3;
	}
	
	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
