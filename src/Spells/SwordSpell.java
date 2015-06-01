package spells;
import magic.Spell;
import general.Tools;

import java.awt.Image;

public class SwordSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static int[] casting = {0,0,0,0,0,0,0};
	public static int[] channel = {0,0,0,0,0,0,0};
	public static int[] trueDamage = {0,0,0,0,0,0,0};
	public static int[] damage = {0,0,0,0,0,0,0};
	public static String[] name = {"Sword level 0", "Sword level 1", "Sword level 2", "Sword level 3", "Sword level 4", "Sword level 5", "Sword level 6",};
	public static final String[] filename = {"Sword1.png","Sword2.png","Sword3.png","Sword4.png","Sword5.png","Sword6.png","Sword7.png"};
	public static Image[] sprite = new Image[7];
	public static SwordSpell[] spells = {new SwordSpell(0), new SwordSpell(1), new SwordSpell(2), new SwordSpell(3), new SwordSpell(4), new SwordSpell(5), new SwordSpell(6)};
	
	protected SwordSpell(int level){
//		super(0,0,0,"Sword lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static SwordSpell get(int n) {
		return spells[n];
	}
	
	public static void load() {
//		sprite = Tools.img.loadEntitySprite(filename);
		for(int i = 0; i < NUM_LEVELS; i++) {
			sprite[i] = Tools.img.loadEntitySprite(filename[i]);
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
