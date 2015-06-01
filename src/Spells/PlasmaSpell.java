package spells;
import magic.Spell;
import general.Tools;

import java.awt.Image;

public class PlasmaSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static int[] casting = {0,0,0,0,0,0,0};
	public static int[] channel = {0,0,0,0,0,0,0};
	public static int[] trueDamage = {0,0,0,0,0,0,0};
	public static int[] damage = {0,0,0,0,0,0,0};
	public static String[] name = {"Plasma level 0", "Plasma level 1", "Plasma level 2", "Plasma level 3", "Plasma level 4", "Plasma level 5", "Plasma level 6",};
	public static final String[] filename = {"Plasma1.png","Plasma2.png","Plasma3.png","Plasma4.png","Plasma5.png","Plasma6.png","Plasma7.png"};
	public static Image[] sprite = new Image[7];
	public static PlasmaSpell[] spells = {new PlasmaSpell(0), new PlasmaSpell(1), new PlasmaSpell(2), new PlasmaSpell(3), new PlasmaSpell(4), new PlasmaSpell(5), new PlasmaSpell(6)};
	
	protected PlasmaSpell(int level){
//		super(0,0,0,"Plasma lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static PlasmaSpell get(int n) {
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
