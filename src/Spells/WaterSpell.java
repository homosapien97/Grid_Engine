package spells;
import magic.Spell;
import general.Tools;

import java.awt.Image;

public class WaterSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static int[] casting = {0,0,0,0,0,0,0};
	public static int[] channel = {0,0,0,0,0,0,0};
	public static int[] trueDamage = {0,0,0,0,0,0,0};
	public static int[] damage = {0,0,0,0,0,0,0};
	public static String[] name = {"Water level 0", "Water level 1", "Water level 2", "Water level 3", "Water level 4", "Water level 5", "Water level 6",};
	public static final String[] filename = {"Water1.png","Water2.png","Water3.png","Water4.png","Water5.png","Water6.png","Water7.png"};
	public static Image[] sprite = new Image[7];
	public static WaterSpell[] spells = {new WaterSpell(0), new WaterSpell(1), new WaterSpell(2), new WaterSpell(3), new WaterSpell(4), new WaterSpell(5), new WaterSpell(6)};
	
	protected WaterSpell(int level){
//		super(0,0,0,"Water lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static WaterSpell get(int n) {
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
