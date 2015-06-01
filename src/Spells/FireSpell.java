package spells;
import magic.Spell;
import general.Tools;

import java.awt.Image;

public class FireSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static int[] casting = {0,0,0,0,0,0,0};
	public static int[] channel = {0,0,0,0,0,0,0};
	public static int[] trueDamage = {0,0,0,0,0,0,0};
	public static int[] damage = {0,0,0,0,0,0,0};
	public static String[] name = {"Fire level 0", "Fire level 1", "Fire level 2", "Fire level 3", "Fire level 4", "Fire level 5", "Fire level 6",};
	public static final String[] filename = {"Fire1.png","Fire2.png","Fire3.png","Fire4.png","Fire5.png","Fire6.png","Fire7.png"};
	public static Image[] sprite = new Image[7];
	public static FireSpell[] spells = {new FireSpell(0), new FireSpell(1), new FireSpell(2), new FireSpell(3), new FireSpell(4), new FireSpell(5), new FireSpell(6)};
	
	protected FireSpell(int level){
//		super(0,0,0,"Fire lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static FireSpell get(int n) {
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
