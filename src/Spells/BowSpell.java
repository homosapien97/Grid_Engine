package spells;
import magic.Spell;
import general.Tools;

import java.awt.Image;

public class BowSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static int[] casting = {0,0,0,0,0,0,0};
	public static int[] channel = {0,0,0,0,0,0,0};
	public static int[] trueDamage = {0,0,0,0,0,0,0};
	public static int[] damage = {0,0,0,0,0,0,0};
	public static String[] name = {"Bow level 0", "Bow level 1", "Bow level 2", "Bow level 3", "Bow level 4", "Bow level 5", "Bow level 6",};
	public static final String[] filename = {"Bow1.png","Bow2.png","Bow3.png","Bow4.png","Bow5.png","Bow6.png","Bow7.png"};
	public static Image[] sprite = new Image[7];
	public static EarthSpell[] spells = {new EarthSpell(0), new EarthSpell(1), new EarthSpell(2), new EarthSpell(3), new EarthSpell(4), new EarthSpell(5), new EarthSpell(6)};
	
	protected BowSpell(int level){
//		super(0,0,0,"Bow lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static EarthSpell get(int n) {
		return spells[n];
	}
	
	public static void load() {
//		sprite = Tools.img.loadEntitySprite(filename);
		for(int i = 0; i < NUM_LEVELS; i++) {
			sprite[i] = Tools.img.loadCard(filename[i]);
		}
	}



	@Override
	public int casting() {
		return casting[level];
	}


	@Override
	public int channel() {
		return channel[level];
	}


	@Override
	public int trueDamage() {
		return trueDamage[level];
	}


	@Override
	public int damage() {
		return damage[level];
	}


	@Override
	public String name() {
		return name[level];
	}


	@Override
	public String filename() {
		return filename[level];
	}


	@Override
	public Image sprite() {
		return sprite[level];
	}

}
