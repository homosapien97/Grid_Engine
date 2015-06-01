package spells;
import magic.Spell;
import general.Tools;

import java.awt.Image;

public class ShieldSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static int[] casting = {0,0,0,0,0,0,0};
	public static int[] channel = {0,0,0,0,0,0,0};
	public static int[] trueDamage = {0,0,0,0,0,0,0};
	public static int[] damage = {0,0,0,0,0,0,0};
	public static String[] name = {"Shield level 0", "Shield level 1", "Shield level 2", "Shield level 3", "Shield level 4", "Shield level 5", "Shield level 6",};
	public static final String[] filename = {"Shield1.png","Shield2.png","Shield3.png","Shield4.png","Shield5.png","Shield6.png","Shield7.png"};
	public static Image[] sprite = new Image[7];
	public static ShieldSpell[] spells = {new ShieldSpell(0), new ShieldSpell(1), new ShieldSpell(2), new ShieldSpell(3), new ShieldSpell(4), new ShieldSpell(5), new ShieldSpell(6)};
	
	protected ShieldSpell(int level){
//		super(0,0,0,"Shield lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static ShieldSpell get(int n) {
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
